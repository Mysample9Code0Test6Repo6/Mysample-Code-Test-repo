package com.incyyte.app.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddresses;
import com.incyyte.app.domain.UserPollPage;
import com.incyyte.app.domain.UserType;
import com.incyyte.app.service.LookupService;
import com.incyyte.app.service.PaymentService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.TaxDetailService;
import com.incyyte.app.service.UserPollService;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.IncyyteProperties;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.PaymentModel;

@Controller
public class PaymentController {

	@Autowired
	private AbstractDao abstractDao;
	@Autowired
	private UserPollService userPollSrv;
	@Autowired
	private PaymentService paymentSrv;
	@Autowired
	private QuickStartService quickStartSrv;
	@Autowired
	private LookupService lookupSrv;
	@Autowired
	private TaxDetailService taxDetailSrv;

	@RequestMapping(value = "/paymentInfo.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String paymentInfo(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, ModelMap model, BindingResult result, SessionStatus status, HttpSession session, HttpServletRequest request) throws Exception {
		Logger.debug("incyyteModel:" + incyyteModel);
		IncyyteModel modelUI = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);	
		Logger.debug("modelUI:incyyteModel:" + modelUI);
		PaymentModel paymentModel = new PaymentModel();
		model.put("incyyteModel", modelUI);
		model.put("PaymentForm", paymentModel);
		long pollCost = 0;
		//Poll charge value is coming in pence (for 0.05 we will have pollCharge as 5)
		if (modelUI.getPollRecipients() != null && modelUI.getPollRecipients() != 0) {
			pollCost = (modelUI.getPollRecipients() * modelUI.getPollCharge());
		}
		modelUI.setPollCost(pollCost);
		double taxRate = 0.0;
		if(StringUtils.equals(user.getCountryCode(), "GB") || StringUtils.equals(user.getCountryCode(), "UK")){
			taxRate=Double.parseDouble(taxDetailSrv.getTaxRate("STANDARD_RATE","UK"));
		} else {
			taxRate=Double.parseDouble(taxDetailSrv.getTaxRate("ZERO_RATE",null));
		}
		Logger.debug("taxvalue....dfgdfgdgffg.:"+taxRate);
		int amount =(int)(pollCost + (pollCost * taxRate));
		Logger.debug("valuewithvat..:"+amount);
		paymentModel.setAmountWOTax(String.valueOf(pollCost));
		paymentModel.setAmount(String.valueOf(amount));
		session.setAttribute(SessionKeys.INCYYTE_MODEL, modelUI);
		String usersDefaultEmail = paymentSrv.getUsersEmailInfo(user);

		Logger.debug("useremail::" + usersDefaultEmail);
		paymentModel.setEmail(usersDefaultEmail);

		//Here the Billing and user addresses are getting from Db
		UserPollPage useraddress = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.USER);
		Logger.debug("useraddress::"+useraddress);

		UserPollPage billingAddress = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.BILLING);
		Logger.debug("BillingAddress::"+billingAddress);

		paymentModel.setPaymentFirstName(StringUtils.defaultString(user.getFirstname()));
		paymentModel.setPaymentLastName(StringUtils.defaultString(user.getLastname()));
		paymentModel.setMobileNumber(StringUtils.defaultString(user.getMobile()));
		if(useraddress.getUserAddress() != null) {
			paymentModel.setAddressPayment1(StringUtils.defaultString(useraddress.getUserAddress().getAddress1()));
			paymentModel.setAddressPayment2(StringUtils.defaultString(useraddress.getUserAddress().getAddress2()));
			paymentModel.setPostcode(StringUtils.defaultString(useraddress.getUserAddress().getPostcode()));
			paymentModel.setCity(StringUtils.defaultString(useraddress.getUserAddress().getCity()));
		}
		if(billingAddress.getUserAddress() != null){
			paymentModel.setAddressPaymentBilling1(StringUtils.defaultString(billingAddress.getUserAddress().getAddress1()));
			paymentModel.setAddressPaymentBilling2(StringUtils.defaultString(billingAddress.getUserAddress().getAddress2()));
			paymentModel.setPostcodeBilling(StringUtils.defaultString(billingAddress.getUserAddress().getPostcode()));
			paymentModel.setCityBilling(StringUtils.defaultString(billingAddress.getUserAddress().getCity()));
			paymentModel.setCompanyName(StringUtils.defaultString(billingAddress.getUserAddress().getCompanyName()));
		}
		model.put("PaymentForm", paymentModel);
		model.put("section", Constants.POLL_SECTION);
		return "payment/PaymentInfo";
	}

	@RequestMapping(value = "/paymentInfoOverview.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String paymentInformationOverview(@ModelAttribute("PaymentForm") PaymentModel paymentModel, ModelMap modelMap, BindingResult result, SessionStatus status, HttpSession session, HttpServletRequest request) throws Exception {
		Logger.debug("paymentModel::" + paymentModel);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("user::" + user);
		IncyyteModel modelUI = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
		Logger.debug("modelUI:incyyteModel:2" + modelUI);
		String orderId;
		if(modelUI != null){
			Logger.debug("modelUI.getId()" + modelUI.getId());
			if(modelUI.getId() == null){
				long qId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("QUESTIONS_PK", true);
				modelUI.setId(new Long(qId));
			}
			session.setAttribute(SessionKeys.INCYYTE_MODEL, modelUI);
			request.getSession().setAttribute(SessionKeys.INCYYTE_MODEL, modelUI);
			request.getSession().setAttribute(SessionKeys.LOGIN_USER, user);
			orderId = String.valueOf(modelUI.getId());
	
			Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			String fDate = sdf.format(date);
			Logger.debug("Today's Date - " + fDate);
			orderId = orderId+fDate;
			Logger.debug("orderId= " + orderId.toString());
			
			modelMap.put("section", Constants.POLL_SECTION);
			Logger.debug("modelUI::" + modelUI);
			modelMap.put(SessionKeys.INCYYTE_MODEL, modelUI);
			modelMap.put("incyyteModel", modelUI);
		} else {
			Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			Logger.debug("sdf:::::" + sdf);
			String fDate = sdf.format(date);
			Logger.debug("fDate::::" + fDate);
			orderId = fDate;
			modelMap.put("section", Constants.BUSINESS_SECTION);
		}
		Logger.debug("order :: " + orderId);
		IncyyteProperties ip = new IncyyteProperties(null);
		ConfigManager icfg = ConfigManager.getInstance();
		icfg.setIncyyteProperties(ip);
		String shaIN = icfg.getProperty(ConfigProperties.SHA_IN_ID);
		String pspId = "epdq1056603";
		String currency = "GBP";
		String language = "en_US";
		String userEmail = user.getEmail();

		String bgColor = "#deddd9";
		String BtnBgColor = "#b5d915";
		String BtnTxtColor = "#ffffff";
		String tableBgColor = "#deddd9";
		String tableTxtColor = "#1a303e";
		String txtColor = "#1a303e";

		String shasign = "AMOUNT=" + paymentModel.getAmount() + shaIN + "BGCOLOR=" + bgColor + shaIN +"BUTTONBGCOLOR="+BtnBgColor+shaIN+"BUTTONTXTCOLOR="+ BtnTxtColor + shaIN + "COMPLUS="+paymentModel.getAmountWOTax() +shaIN+"CURRENCY=" + currency + shaIN + "EMAIL=" + userEmail + shaIN + "LANGUAGE=" + language + shaIN + "ORDERID=" + orderId + shaIN + "PSPID=" + pspId + shaIN+"TBLBGCOLOR="+tableBgColor+shaIN+"TBLTXTCOLOR="+tableTxtColor+shaIN+"TXTCOLOR="+txtColor+shaIN;	
		Logger.debug("shasign:string:2:" + shasign);
		paymentModel.setSHASign(sha1(shasign));
		paymentModel.setOrderID(orderId);
		paymentModel.setCurrency(currency);
		paymentModel.setLanguage(language);
		paymentModel.setPSPID(pspId);
		paymentModel.setCustomerEmail(userEmail);

		String paymentURL = icfg.getProperty(ConfigProperties.PAYMENT_GATEWAY_URL);
		modelMap.put("paymentURL", paymentURL);

		Logger.debug("paymentModel::ShaSign:" + paymentModel.getSHASign());
		modelMap.put("PaymentForm", paymentModel);
		Logger.debug("paymentModel:::" + paymentModel.toString());
		return "payment/PaymentInfoOverview";
	}

	static String sha1(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		Logger.debug("SHA-SIGN:::" + sb.toString());
		return sb.toString();
	}

	@RequestMapping(value = "/paymentConfirmation")
	public String paymentConfirmation(ModelMap modelMap, HttpSession session, HttpServletRequest request) throws Exception {
		HttpSession session1 = request.getSession(true);
		Logger.debug("session1::" + session1);
		IncyyteModel incyyteModel = (IncyyteModel) session1.getAttribute(SessionKeys.INCYYTE_MODEL);
		Logger.debug("sesion2IncyyteModel::" + incyyteModel);
		modelMap.put("incyyteModel", incyyteModel);

		InCyyte	incyyte = null;
		if(incyyteModel != null){
			incyyte = MapUserProperty.copyInCyyteDetails(incyyteModel);
			incyyte.setAgeRange(incyyteModel.getAgeMin()+" - "+incyyteModel.getAgeMax());
            incyyte.setTotalInCyyted(incyyteModel.getPollRecipients().intValue());
			Logger.debug("incyyte:incyyte 3:" + incyyte);
		}
		User user = (User) session1.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("user::" + user);

		ServletContext servletContext = request.getSession().getServletContext();

		String orderId = request.getParameter("orderID");
		String currency = request.getParameter("currency");
		String amount = request.getParameter("amount");
		Logger.debug("amount getting from barclay url:"+amount);
		//COMPLUS is just an attribute and the value will not change
		//Here the value is in pence
		String amountWOTax = request.getParameter("COMPLUS");
		Logger.debug("amountWOTax:"+amountWOTax);

		String PM = request.getParameter("PM");
		String ACCEPTANCE = request.getParameter("ACCEPTANCE");
		String STATUS = request.getParameter("STATUS");
		String ED = request.getParameter("ED");
		String TRXDATE = request.getParameter("TRXDATE");
		String PAYID = request.getParameter("PAYID");
		String NCERROR = request.getParameter("NCERROR");
		String BRAND = request.getParameter("BRAND");
		String IP = request.getParameter("IP");
		String SHASIGN = request.getParameter("SHASIGN");
		Logger.debug("orderId:currency:amount:PM:ACCEPTANCE:STATUS:ED:TRXDATE:PAYID:NCERROR:BRAND:IP:SHASIGN::" + orderId + ":" + currency + ":"+ amount+":" + PM + ":" + ACCEPTANCE + ":" + STATUS + ":" + ED + ":" + TRXDATE + ":" + PAYID + ":" + NCERROR + ":" + BRAND + ":" + IP + ":" + SHASIGN);

		PaymentModel paymentModel = new PaymentModel();

		UserPollPage useraddress = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.USER);
		Logger.debug("useraddress::"+useraddress);
		UserPollPage billingAddress = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.BILLING);
		Logger.debug("BillingAddress::"+billingAddress);

		paymentModel.setPaymentFirstName(StringUtils.defaultString(user.getFirstname()));
		paymentModel.setPaymentLastName(StringUtils.defaultString(user.getLastname()));
		paymentModel.setMobileNumber(StringUtils.defaultString(user.getMobile()));
		if(useraddress.getUserAddress() != null) {
			paymentModel.setAddressPayment1(StringUtils.defaultString(useraddress.getUserAddress().getAddress1()));
			paymentModel.setAddressPayment2(StringUtils.defaultString(useraddress.getUserAddress().getAddress2()));
			paymentModel.setPostcode(StringUtils.defaultString(useraddress.getUserAddress().getPostcode()));
			paymentModel.setCity(StringUtils.defaultString(useraddress.getUserAddress().getCity()));
            paymentModel.setCountry(StringUtils.defaultString(useraddress.getUserAddress().getCountry()));
		}
		if(billingAddress.getUserAddress() != null){
			paymentModel.setAddressPaymentBilling1(StringUtils.defaultString(billingAddress.getUserAddress().getAddress1()));
			paymentModel.setAddressPaymentBilling2(StringUtils.defaultString(billingAddress.getUserAddress().getAddress2()));
			paymentModel.setPostcodeBilling(StringUtils.defaultString(billingAddress.getUserAddress().getPostcode()));
			paymentModel.setCityBilling(StringUtils.defaultString(billingAddress.getUserAddress().getCity()));
            paymentModel.setCountryBilling(StringUtils.defaultString(billingAddress.getUserAddress().getCountry()));
			paymentModel.setCompanyName(StringUtils.defaultString(billingAddress.getUserAddress().getCompanyName()));
		}

		Logger.debug("paymentModel:" + paymentModel);
		paymentModel.setOrderID(orderId);
		paymentModel.setPaymentReference(PAYID);
		paymentModel.setAmountWOTax(amountWOTax);
		paymentModel.setAmount(amount);
		paymentModel.setTransactionDate(TRXDATE);
		if(StringUtils.equals(STATUS, "9") ){
			paymentModel.setPaymentStatus("Success");
			paymentModel.setErrorReason("");
		}else {
			paymentModel.setPaymentStatus(STATUS);
			paymentModel.setErrorReason("Error in the transaction");
		}
		try {
			if(incyyte == null) {
				Logger.debug("UserType.BUSINESS_SILVER::");
				modelMap.put("section", Constants.BUSINESS_SECTION);
				paymentSrv.updateUserType(user, UserType.BUSINESS_SILVER );
				user.setUserType(UserType.BUSINESS_SILVER.toString());
				session.setAttribute(SessionKeys.LOGIN_USER, user);
				paymentSrv.insertPaymentTableInfo(user, null , paymentModel);
                //Amount after processed from Barclays will get converted to pounds (5p will be 0.05)
                //Hence multiplying back with 100
                amount = String.valueOf((int) (Double.valueOf(amount) * 100));
                Logger.debug("after manipulation:"+amount);
                paymentModel.setAmount(amount);
                paymentSrv.sendMailToSender(paymentModel, user, null);
			} else {
				modelMap.put("section", Constants.POLL_SECTION);
				Logger.debug("user::"+user);
				paymentSrv.insertPaymentTableInfo(user, incyyteModel, paymentModel);
                //Amount after processed from Barclays will get converted to pounds (5p will be 0.05)
                //Hence multiplying back with 100
                amount = String.valueOf((int) (Double.valueOf(amount) * 100));
                Logger.debug("after manipulation:"+amount);
                paymentModel.setAmount(amount);
                paymentSrv.sendMailToSender(paymentModel, user, incyyte);
				Long incyyteId = quickStartSrv.processArea(user, incyyte, servletContext);
				request.getSession().setAttribute(SessionKeys.INCYYTE_ID, incyyteId);
				Logger.debug("incyyteId::" + incyyteId);
			}
		} catch (Exception e) {
			Logger.error("sending Poll to region is failed", e);
		}
        modelMap.put("PaymentForm", paymentModel);
        Logger.debug("paymentModel:after:" + paymentModel);
        request.getSession().setAttribute(SessionKeys.INCYYTE_MODEL, incyyteModel);
		request.getSession().setAttribute(SessionKeys.LOGIN_USER, user);
		return "payment/PaymentConfirmation";
	}

	@RequestMapping(value = "/paymentFailure.cyt", method = RequestMethod.GET)
	public String paymentFailure(@ModelAttribute("PaymentForm") PaymentModel paymentModel, ModelMap modelMap, BindingResult result, SessionStatus status, HttpSession session, HttpServletRequest request) throws Exception {
		Logger.debug("when it failed from payment overview.jsp");
		Logger.debug("paymentModel:::"+paymentModel);

		IncyyteModel incyyteModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
		Logger.debug("incyyteModel:::"+incyyteModel);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("user:::"+user);

		String orderId = request.getParameter("orderID");
		String currency = request.getParameter("currency");
		String amount = request.getParameter("amount");
		Logger.debug("amount getting from barclay url:"+amount);
		//COMPLUS is just an attribute and the value will not change
		//Here the value is in pence
		String amountWOTax = request.getParameter("COMPLUS");
		Logger.debug("amountWOTax:"+amountWOTax);

		String PM = request.getParameter("PM");
		String ACCEPTANCE = request.getParameter("ACCEPTANCE");
		String STATUS = request.getParameter("STATUS");
		String ED = request.getParameter("ED");
		String TRXDATE = request.getParameter("TRXDATE");
		String PAYID = request.getParameter("PAYID");
		String NCERROR = request.getParameter("NCERROR");
		String BRAND = request.getParameter("BRAND");
		String IP = request.getParameter("IP");
		String SHASIGN = request.getParameter("SHASIGN");
		String statusValue = request.getParameter("StatusFromUrl");
		Logger.debug("orderId:currency:amount:PM:ACCEPTANCE:STATUS:ED:TRXDATE:PAYID:NCERROR:BRAND:IP:SHASIGN::" + orderId + ":" + currency + ":"+ amount+":" + PM + ":" + ACCEPTANCE + ":" + STATUS + ":" + ED + ":" + TRXDATE + ":" + PAYID + ":" + NCERROR + ":" + BRAND + ":" + IP + ":" + SHASIGN);

		PaymentModel paymentModel2 = new PaymentModel();

		Logger.debug("statusValue::"+statusValue);
		if(StringUtils.equalsIgnoreCase(statusValue, "DECLINE")){
			paymentModel2.setErrorReason("DECLINE");
			paymentModel2.setPaymentStatus("DECLINE");
		}else if(StringUtils.equalsIgnoreCase(statusValue, "EXCEPTION")){
			paymentModel2.setErrorReason("EXCEPTION");
			paymentModel2.setPaymentStatus("EXCEPTION");
		}else if(StringUtils.equalsIgnoreCase(statusValue, "CANCEL")){
			paymentModel2.setErrorReason("CANCEL");
			paymentModel2.setPaymentStatus("CANCEL");
		}
		paymentModel2.setOrderID(orderId);
		paymentModel2.setPaymentReference(PAYID);
		paymentModel2.setAmount(amount);
		paymentModel2.setTransactionDate(TRXDATE);
		paymentModel2.setAmountWOTax(amountWOTax);
		modelMap.put("incyyteModel", incyyteModel);
		modelMap.put("PaymentForm", paymentModel2);
		Logger.debug("paymentModel:after:" + paymentModel2);
		try{
			if(incyyteModel == null){
				modelMap.put("section", Constants.BUSINESS_SECTION);
				paymentSrv.insertPaymentTableInfo(user, incyyteModel, paymentModel2);
                //Amount after processed from Barclays will get converted to pounds (5p will be 0.05)
                //Hence multiplying back with 100
                amount = String.valueOf((int) (Double.valueOf(amount) * 100));
                Logger.debug("after manipulation:"+amount);
                paymentModel2.setAmount(amount);
                long count = paymentSrv.getPaymentFailureCount(user, incyyteModel, paymentModel2);
				Logger.debug("count:for Business payment:"+count);
				modelMap.put("businessPaymentFailureCount", count);
			}else{
				modelMap.put("section", Constants.POLL_SECTION);
				paymentSrv.insertPaymentTableInfo(user, incyyteModel, paymentModel2);
                //Amount after processed from Barclays will get converted to pounds (5p will be 0.05)
                //Hence multiplying back with 100
                amount = String.valueOf((int) (Double.valueOf(amount) * 100));
                Logger.debug("after manipulation:"+amount);
                paymentModel2.setAmount(amount);
                long count = paymentSrv.getPaymentFailureCount(user, incyyteModel, paymentModel2);
				Logger.debug("count:for poll payment:"+count);
				modelMap.put("pollPaymentFailureCount", count);
			}
		}catch (Exception e) {
			Logger.error("inserting of payment table info when error was failed" , e);
		}
		return "payment/paymentFailure";
	}

	@RequestMapping(value = "/insertPaymentInfo.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String insertPaymentInfo(@ModelAttribute("PaymentForm") PaymentModel paymentModel, ModelMap modelMap, BindingResult result, SessionStatus status, HttpSession session, HttpServletRequest request) throws Exception {
		Logger.debug("paymentModel:Inserting:" + paymentModel);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("user::" + user);

		UserAddresses userAddressesBilling = new UserAddresses();
		userAddressesBilling.setAddress1(paymentModel.getAddressPaymentBilling1());
		userAddressesBilling.setAddress2(paymentModel.getAddressPaymentBilling2());
		userAddressesBilling.setCity(paymentModel.getCityBilling());
		userAddressesBilling.setPostcode(paymentModel.getPostcodeBilling());
		userAddressesBilling.setCountry(paymentModel.getCountryBilling());
		userAddressesBilling.setUserId(user.getId());
		userAddressesBilling.setAddressType(AddressType.BILLING);
		userAddressesBilling.setCompanyName(paymentModel.getCompanyName());

		Logger.debug("userAddressesBilling::" + userAddressesBilling);

		UserAddresses userAddressesUser = new UserAddresses();

		userAddressesUser.setAddress1(paymentModel.getAddressPayment1());
		userAddressesUser.setAddress2(paymentModel.getAddressPayment2());
		userAddressesUser.setCity(paymentModel.getCity());
		userAddressesUser.setPostcode(paymentModel.getPostcode());
		userAddressesUser.setCountry(paymentModel.getCountry());
		userAddressesUser.setUserId(user.getId());
		userAddressesUser.setAddressType(AddressType.USER);

		Logger.debug("userAddressesUser::" + userAddressesUser);

		try {
			Logger.debug("user:" + user);
			Logger.debug("request:" + request);
			paymentSrv.insertPaymentInfo(paymentModel, userAddressesBilling, userAddressesUser, user);
			user.setFirstname(paymentModel.getPaymentFirstName());
			user.setLastname(paymentModel.getPaymentLastName());
			user.setMobile(paymentModel.getMobileNumber());
			session.setAttribute(SessionKeys.LOGIN_USER , user);
		} catch (Exception e) {
			Logger.error(e);
		}
		return "success";
	}

	@RequestMapping(value="/businessUserPaymentProcess.cyt", method ={RequestMethod.GET, RequestMethod.POST})
	public String businessUserPaymentInfo(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, ModelMap model, BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		session.removeAttribute(SessionKeys.INCYYTE_MODEL);
		Logger.debug("inside payment controller");
		PaymentModel paymentModel = new PaymentModel();
		model.put("PaymentForm", paymentModel);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String usersDefaultEmail = paymentSrv.getUsersEmailInfo(user);
		Logger.debug("useremail::" + usersDefaultEmail);
		paymentModel.setEmail(usersDefaultEmail);
		//Here the Billing and user addresses are getting from Db
		UserPollPage useraddress = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.USER);
		Logger.debug("useraddress::"+useraddress);
		UserPollPage billingAddress = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.BILLING);
		Logger.debug("BillingAddress::"+billingAddress);

		paymentModel.setPaymentFirstName(StringUtils.defaultString(user.getFirstname()));
		paymentModel.setPaymentLastName(StringUtils.defaultString(user.getLastname()));
		paymentModel.setMobileNumber(StringUtils.defaultString(user.getMobile()));
		//TODO:Lookup type is hardcoded. Need to make dynamic 
		double amountWOTax = Double.parseDouble(lookupSrv.getLookupValue("BUSINESS_SILVER", "SUBSCRIPTION"));
		int amount1 = (int) (amountWOTax * 100);
		paymentModel.setAmountWOTax(String.valueOf(amount1));
		double taxRate = 0.0;
		if(StringUtils.equals(user.getCountryCode(), "GB") || StringUtils.equals(user.getCountryCode(), "UK")){
			taxRate=Double.parseDouble(taxDetailSrv.getTaxRate("STANDARD_RATE","UK"));
		} else {
			taxRate=Double.parseDouble(taxDetailSrv.getTaxRate("ZERO_RATE",null));
		}
		Logger.debug("taxvalue.....:" + taxRate);
		int amount =(int)(amount1 + (amount1 * taxRate));
		Logger.debug("valuewithvat..:"+amount);
		paymentModel.setAmount(String.valueOf(amount));
		if(useraddress.getUserAddress() != null) {
			paymentModel.setAddressPayment1(StringUtils.defaultString(useraddress.getUserAddress().getAddress1()));
			paymentModel.setAddressPayment2(StringUtils.defaultString(useraddress.getUserAddress().getAddress2()));
			paymentModel.setPostcode(StringUtils.defaultString(useraddress.getUserAddress().getPostcode()));
			paymentModel.setCity(StringUtils.defaultString(useraddress.getUserAddress().getCity()));
		}
		if(billingAddress.getUserAddress() != null){
			paymentModel.setAddressPaymentBilling1(StringUtils.defaultString(billingAddress.getUserAddress().getAddress1()));
			paymentModel.setAddressPaymentBilling2(StringUtils.defaultString(billingAddress.getUserAddress().getAddress2()));
			paymentModel.setPostcodeBilling(StringUtils.defaultString(billingAddress.getUserAddress().getPostcode()));
			paymentModel.setCityBilling(StringUtils.defaultString(billingAddress.getUserAddress().getCity()));
			paymentModel.setCompanyName(StringUtils.defaultString(billingAddress.getUserAddress().getCompanyName()));
		}
		model.put("PaymentForm", paymentModel);
		Logger.debug("payment model" + paymentModel);
		model.put("section", Constants.BUSINESS_SECTION);
		Logger.debug("before return statement");
		return "payment/PaymentInfo";
	}
}
