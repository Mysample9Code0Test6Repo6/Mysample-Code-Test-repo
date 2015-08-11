package com.incyyte.app.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.payment.PaymentDao;
import com.incyyte.app.dao.profile.ProfileDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.dao.userPollPage.UserPollDao;
import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddresses;
import com.incyyte.app.domain.UserType;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.PaymentModel;

public class PaymentMangerImpl implements PaymentManager {

	@Autowired
	private UserPollDao userPollDao;
	@Autowired
	private AbstractDao abstractDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private EmailManager emailMgr;

	@Override
	public boolean insertPaymentInfo(PaymentModel paymentModel,UserAddresses billingAddress, UserAddresses userAddress,User user)  throws Exception {
		Logger.debug("paymentModel:::"+paymentModel);
		Logger.debug("billingAddress:::"+billingAddress);
		Logger.debug("userAddress:::"+userAddress);

		UserAddresses userAddressesDb = userPollDao.getUserAddressesInfo(userAddress.getUserId(), userAddress.getAddressType());
		Logger.debug("userAddressesDb:::"+userAddressesDb);
		UserAddresses billingAddressesDb = userPollDao.getUserAddressesInfo(billingAddress.getUserId(), billingAddress.getAddressType());
		Logger.debug("billingAddressesDb:::"+billingAddressesDb);

		JdbcTemplate template = null;
		try{
			//begin the transaction 
			abstractDao.getTxnHelper().beginTxn();
			template = abstractDao.getJdbcTemplate("insertPaymentInfo");

			//update the user table .
			userDao.updateUsersFrmPayment(paymentModel , userAddress.getUserId());

			//update the user emails table.
			profileDao.saveUsersEmailFrmPayent(user, paymentModel);

			//Insert into User Address
			if (userAddressesDb != null  ) {
				userPollDao.updateUserAddressesInfo(userAddress, template);
			}else{
				userPollDao.insertUserAddressesInfo(userAddress, template);
			}
			if(billingAddressesDb != null){
				userPollDao.updateUserAddressesInfo(billingAddress, template);
			}else{
				userPollDao.insertUserAddressesInfo(billingAddress, template);
			}

			//If Successful then Commit
			abstractDao.getTxnHelper().commitTxn();
			return true;	

		}catch (Exception e){
			Logger.error(e);
			abstractDao.getTxnHelper().rollbackTxn();
		}finally {
			abstractDao.freedbpool(template.getDataSource(), "insertPaymentInfo");
		}
		return false;
	}


	@Override
	public PaymentModel getPaymentInfo(long userId, AddressType billing) throws Exception {

		UserAddresses userAddressesDb = userPollDao.getUserAddressesInfo(userId, billing);	
		PaymentModel paymentModel = new PaymentModel();
		return paymentModel;
	}


	@Override
	public String getUsersEmailInfo(User user) throws Exception {
		return profileDao.getDefaultEmail(user);
	}


	@Override
	public boolean insertPaymentTableInfo(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception {
		return paymentDao.insertPaymentTableInfo(user , incyyteModel, paymentModel);
	}

	@Override
	public boolean updateUserType(User user,  UserType business_Sliver) throws Exception {
		return userDao.updateUserType(user ,business_Sliver );
	}

	@Override
	public Long getPaymentFailureCount(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception {
		return paymentDao.getPaymentFailureCount(user , incyyteModel , paymentModel);
	}

	@Override
	public void sendMailToSender(PaymentModel paymentModel,User user, InCyyte incyyte) throws Exception  {
		emailMgr.setPaymentModel(paymentModel);
		emailMgr.setIncyyte(incyyte);
		emailMgr.setUser(user);
		emailMgr.setRunFlag(Constants.SEND_PAYMENT_EMAIL);
		new Thread(emailMgr).start();
	}
}