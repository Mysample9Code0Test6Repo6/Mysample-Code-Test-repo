package com.incyyte.app.web.controller.businessaccount;

import com.incyyte.app.domain.BusinessAccount;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.BusinessAccountService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.MandatoryInfoModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BusinessAccountController {
    protected final Log log = LogFactory.getLog(getClass());
    private Long IMAGE_MAX_SIZE = 2097152L;

    @Autowired
    private BusinessAccountService businessAccountSrv;

    @RequestMapping(value = "/storeBusinessAccountInfo", method = RequestMethod.POST)
    @ResponseBody
    public String storeBusinesUserDetails(@ModelAttribute("bizAccountForm") BusinessAccount businessAccount, HttpServletRequest request, HttpSession session, Model model) throws Exception {
        Logger.debug("inside BusinessAccountController:::::" + businessAccount);
        BusinessAccount sesionBusinessAccount = (BusinessAccount) session.getAttribute("bizAccount");

        if (sesionBusinessAccount == null) {
            sesionBusinessAccount = businessAccount;
        }
        Logger.debug("Uploading company logo...");
        Logger.debug("businessAccount::::::::::::::" + businessAccount);
        Logger.debug("sesionBusinessAccount::::::::::" + sesionBusinessAccount);
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        businessAccount.setUserid(user.getId());
        Logger.debug("businessAccount::::::::::::::" + businessAccount);
        Logger.debug("sesionBusinessAccount::::::::::" + sesionBusinessAccount);
        try {
            Logger.debug("create business account::try:::::::::");
            businessAccount.setCompanyLogoUrl(sesionBusinessAccount.getCompanyLogoUrl());
            businessAccount.setBannerUrl(sesionBusinessAccount.getBannerUrl());
            businessAccountSrv.storeBussinessAccountInfo(businessAccount);
            businessAccountSrv.updateUserType(user.getId());
            Logger.debug("Stored:::::::::");
            Logger.debug("sesionBusinessAccount:::===:::" + sesionBusinessAccount.getSaveChanges());
            Logger.debug("businessAccount:::===:::" + businessAccount.getSaveChanges());
            businessAccount.setSaveChanges("");
            user.setUserType("BUSINESS");
            session.setAttribute(SessionKeys.LOGIN_USER, user);
            model.addAttribute("bizAccountForm", businessAccount);
            session.setAttribute("bizAccount", businessAccount);
            Logger.debug("bizAccountForm::::storeBusinessAccountInfo:::" + businessAccount);
            Logger.debug("bizAccount::::storeBusinessAccountInfo::::: " + businessAccount);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @RequestMapping(value = "/uploadCompanyLogo", method = RequestMethod.POST)
    public String uploadCompanyLogo(HttpServletRequest request, @ModelAttribute("bizAccountForm") BusinessAccount businessAccount,
                                    BindingResult result, SessionStatus status, HttpSession session, Model model) throws Exception {
        BusinessAccount sesionBusinessAccount = (BusinessAccount) session.getAttribute("bizAccount");
        sesionBusinessAccount.setSaveChanges(businessAccount.getSaveChanges());
        Logger.debug("uploadCompanyLogo:sesionBusinessAccount:::===:::" + sesionBusinessAccount);
        Logger.debug("uploadCompanyLogo:businessAccount:::===:::" + businessAccount);
        Logger.debug("Uploading company logo...");
        if (!businessAccount.getUploadedLogo().isEmpty()) {
            CommonsMultipartFile multipartFile = businessAccount.getUploadedLogo();
            if (multipartFile != null) {
                Logger.debug("Uploading logo:: " + multipartFile.getOriginalFilename());
                String remoteFile = generateFileName(multipartFile.getOriginalFilename());
                String type = getType(multipartFile.getContentType());
                String cdnLogoUrl = FileManagementUtil.uploadFile(type, remoteFile, multipartFile, null);
                Logger.debug("Uploaded logo URL:: " + cdnLogoUrl);

                sesionBusinessAccount.setCompanyName(businessAccount.getCompanyName());
                sesionBusinessAccount.setCity(businessAccount.getCity());
                sesionBusinessAccount.setAddress1(businessAccount.getAddress1());
                sesionBusinessAccount.setAddress2(businessAccount.getAddress2());
                sesionBusinessAccount.setPostalCode(businessAccount.getPostalCode());
                sesionBusinessAccount.setCountry(businessAccount.getCountry());
                sesionBusinessAccount.setContactEmail(businessAccount.getContactEmail());
                sesionBusinessAccount.setPhone(businessAccount.getPhone());
                sesionBusinessAccount.setCompanyInfoPara1(businessAccount.getCompanyInfoPara1());
                sesionBusinessAccount.setCompanyInfoPara2(businessAccount.getCompanyInfoPara2());
                sesionBusinessAccount.setWebsiteUrl(businessAccount.getWebsiteUrl());
                sesionBusinessAccount.setFileName(remoteFile);
                sesionBusinessAccount.setCompanyLogoUrl(cdnLogoUrl);
                sesionBusinessAccount.setUploadedLogo(multipartFile);

                model.addAttribute("bizAccountForm", sesionBusinessAccount);
                session.setAttribute("bizAccount", sesionBusinessAccount);
                Logger.debug("sesionBusinessAccount:::======:::" + sesionBusinessAccount);
                Logger.debug("businessAccount:::=======:::" + businessAccount);
                return "redirect:businessAccountPage.cyt";
            }

        }
        return "success";
    }

    @RequestMapping(value = "/validateCompanyLogoSize", method = RequestMethod.POST)
    @ResponseBody
    public String validateCompanyLogoSize(HttpServletRequest request, @ModelAttribute("bizAccountForm") BusinessAccount businessAccount, SessionStatus status, HttpSession session, Model model) throws Exception {
        Logger.debug("inside validate CompanyLogo Size");
        if (!businessAccount.getUploadedLogo().isEmpty()) {
            CommonsMultipartFile multipartFile = businessAccount.getUploadedLogo();
            if (multipartFile != null && multipartFile.getSize() > IMAGE_MAX_SIZE) {
                Logger.debug("Uploading logo:: " + multipartFile);
                return "failure";
            } else {
                return "success";
            }
        }
        return "failure";
    }

    @RequestMapping(value = "/uploadBannerLogo", method = RequestMethod.POST)

    public String uploadBannerLogo(HttpServletRequest request, @ModelAttribute("bizAccountForm") BusinessAccount businessAccount,
                                   BindingResult result, SessionStatus status, HttpSession session, Model model) throws Exception {
        BusinessAccount sesionBusinessAccount = (BusinessAccount) session.getAttribute("bizAccount");
        sesionBusinessAccount.setSaveChanges(businessAccount.getSaveChanges());
        Logger.debug("sesionBusinessAccount:::===:::" + sesionBusinessAccount);
        Logger.debug("businessAccount:::===:::" + businessAccount);

        Logger.debug("Uploading Banner logo...");
        Logger.debug("businessAccount::" + businessAccount);
        Logger.debug("sesionBusinessAccount::" + sesionBusinessAccount);
        if (!businessAccount.getUploadedBannerLogo().isEmpty()) {
            CommonsMultipartFile multipartFile = businessAccount.getUploadedBannerLogo();
            if (multipartFile != null) {
                Logger.debug("Uploading logo:: " + multipartFile.getOriginalFilename());

                String remoteFile = generateFileName(multipartFile.getOriginalFilename());
                String type = getType(multipartFile.getContentType());

                String cdnLogoUrl = FileManagementUtil.uploadFile(type, remoteFile, multipartFile, null);

                Logger.debug("Uploaded logo URL:: " + cdnLogoUrl);

                sesionBusinessAccount.setCompanyName(businessAccount.getCompanyName());
                sesionBusinessAccount.setCity(businessAccount.getCity());
                sesionBusinessAccount.setAddress1(businessAccount.getAddress1());
                sesionBusinessAccount.setAddress2(businessAccount.getAddress2());
                sesionBusinessAccount.setPostalCode(businessAccount.getPostalCode());
                sesionBusinessAccount.setCountry(businessAccount.getCountry());
                sesionBusinessAccount.setContactEmail(businessAccount.getContactEmail());
                sesionBusinessAccount.setPhone(businessAccount.getPhone());
                sesionBusinessAccount.setCompanyInfoPara1(businessAccount.getCompanyInfoPara1());
                sesionBusinessAccount.setCompanyInfoPara2(businessAccount.getCompanyInfoPara2());
                sesionBusinessAccount.setWebsiteUrl(businessAccount.getWebsiteUrl());
                sesionBusinessAccount.setBannerFileName(remoteFile);
                sesionBusinessAccount.setBannerUrl(cdnLogoUrl);
                sesionBusinessAccount.setUploadedBannerLogo(multipartFile);

                model.addAttribute("bizAccountForm", sesionBusinessAccount);
                session.setAttribute("bizAccount", sesionBusinessAccount);
                Logger.debug("sesionBusinessAccount:::======:::" + sesionBusinessAccount);
                Logger.debug("businessAccount:::=======:::" + businessAccount);
                return "redirect:businessAccountPage.cyt";
            }
            /*else{
               log.error("%%%%%%%%%%%%%%% uploadMultipartFile - upload is empty or too big");
           }*/
        }
        return "success";
    }

    @RequestMapping(value = "/validateBannerLogo", method = RequestMethod.POST)
    @ResponseBody
    public String uploadBannerLogoValidate(HttpServletRequest request, @ModelAttribute("bizAccountForm") BusinessAccount businessAccount, SessionStatus status, HttpSession session, Model model) throws Exception {
        Logger.debug("inside validate Bannner logo Size");

        if (!businessAccount.getUploadedBannerLogo().isEmpty()) {
            CommonsMultipartFile multipartFile = businessAccount.getUploadedBannerLogo();
            if (multipartFile != null && multipartFile.getSize() > 0 && multipartFile.getSize() > IMAGE_MAX_SIZE) {
                Logger.debug("inside if image size validation:::" + multipartFile);
                return "failure";
            } else {
                return "success";
            }
        }
        return "failure";
    }

    @RequestMapping(value = "/businessAccountPage.cyt", method = {RequestMethod.POST, RequestMethod.GET})
    public String businessAccountPage(@ModelAttribute("bizAccountForm") BusinessAccount businessAccount, ModelMap model, HttpServletRequest request, HttpSession session) {
        BusinessAccount sesionBusinessAccount = (BusinessAccount) session.getAttribute("bizAccount");
        Logger.debug("sesionBusinessAccount:::=======:::" + sesionBusinessAccount);
        Logger.debug("businessAccount:::=======:::" + businessAccount);
        Logger.debug("businessAccountPage:::=======:::");

        //Adding Junk data inorder to Skip the Mandatory-Information-Modal Window during Upload/Delete Images(Banner/Company Logo)
        MandatoryInfoModel mi = new MandatoryInfoModel();
        mi.setPostalCodeArea("QWERTY");
        model.put("detailsform", mi);
        model.put("page", "createBizAcct");
        model.addAttribute("bizAccountForm", sesionBusinessAccount);
        Logger.debug("sesionAccount:::=======:::" + session.getAttribute("bizAccount"));
        Logger.debug("businessAccount:::=======:::" + businessAccount);
        return "dashboard/dashboard";
    }


    /*  @Value("${updatebizacct.success}")
         private String bizaccUpdate;

       @Value("${updatebizacct.error}")
         private String bizacctUpdateError;*/
    @RequestMapping(value = "/updateBusinessAccountInfo", method = RequestMethod.POST)
    @ResponseBody
    public String updateBusinesUserDetails(@ModelAttribute("bizAccountForm") BusinessAccount businessAccount, HttpSession session, Model model) throws Exception {
        Logger.debug("inside BusinessAccountController:::::update" + businessAccount);
        BusinessAccount sesionBusinessAccount = (BusinessAccount) session.getAttribute("bizAccount");
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        if (sesionBusinessAccount == null) {
            sesionBusinessAccount = businessAccount;
        }
        Logger.debug("businessAccount::" + businessAccount);
        Logger.debug("sesionBusinessAccount::" + sesionBusinessAccount);
        businessAccount.setUserid(user.getId());
        try {
            boolean updateBusinessUserDetails = businessAccountSrv.updateBusinessAccountInfo((businessAccount));
            if (updateBusinessUserDetails) {
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/deleteBanner", method = RequestMethod.POST)
    public String deleteBannerImage(@ModelAttribute("bizAccountForm") BusinessAccount businessAccount, HttpSession session, Model model) throws Exception {
        BusinessAccount sessionBusinessAccount = (BusinessAccount) session.getAttribute("bizAccount");
        Logger.debug("inside BusinessAccountController:::::delete banner logo" + sessionBusinessAccount);
        Logger.debug("inside BusinessAccountController:::::delete banner logo" + businessAccount);
        sessionBusinessAccount.setSaveChanges(businessAccount.getSaveChanges());
        try {
            if (!StringUtils.isBlank(sessionBusinessAccount.getBannerFileName())
                    || !StringUtils.isBlank(sessionBusinessAccount.getBannerUrl())) {
                sessionBusinessAccount.setUploadedBannerLogo(null);
                sessionBusinessAccount.setBannerFileName(null);
                sessionBusinessAccount.setBannerUrl(null);
                model.addAttribute("bizAccountForm", sessionBusinessAccount);
                session.setAttribute("bizAccount", sessionBusinessAccount);
            }
            return "redirect:businessAccountPage.cyt";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/deleteCompLogo", method = RequestMethod.POST)
    public String deleteCompLogo(@ModelAttribute("bizAccountForm") BusinessAccount businessAccount, HttpSession session, Model model) throws Exception {
        BusinessAccount sessionBusinessAccount = (BusinessAccount) session.getAttribute("bizAccount");
        sessionBusinessAccount.setSaveChanges(businessAccount.getSaveChanges());
        Logger.debug("inside BusinessAccountController:::::delete company logo" + sessionBusinessAccount);
        Logger.debug("inside BusinessAccountController:::::delete company logo" + businessAccount);
        try {
            if (!StringUtils.isBlank(sessionBusinessAccount.getFileName())
                    || !StringUtils.isBlank(sessionBusinessAccount.getCompanyLogoUrl())) {
                Logger.debug("inside BusinessAccountController:::::delete company logo:::" + sessionBusinessAccount.getCompanyLogoUrl());
                sessionBusinessAccount.setUploadedLogo(null);
                sessionBusinessAccount.setFileName(null);
                sessionBusinessAccount.setCompanyLogoUrl(null);
                model.addAttribute("bizAccountForm", sessionBusinessAccount);
                session.setAttribute("bizAccount", sessionBusinessAccount);
                Logger.debug("inside BusinessAccountController:::::delete company logo:::" + sessionBusinessAccount.getCompanyLogoUrl());
            }
            return "redirect:businessAccountPage.cyt";
        } catch (Exception e) {
            return "error";
        }
    }


    protected String generateFileName(String fileName) {

        Logger.debug("File name = " + fileName);

        if (fileName != null) {

            StringBuilder fName = new StringBuilder();
            String ext = "";
            int mid = fileName.lastIndexOf(".");
            fName.append(fileName.substring(0, mid));

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy hhmmss");
            String fDate = sdf.format(date);
            Logger.debug("Today's Date - " + fDate);

            fName.append(" ");
            fName.append(fDate);
            Logger.debug("File name prefix = " + fName.toString());
            ext = fileName.substring(mid, fileName.length());
            Logger.debug("Extension = " + ext);
            fName.append(ext);
            Logger.debug("File name = " + fName.toString().replace(" ", "_"));
            return fName.toString().replace(" ", "_");
        }
        return fileName;
    }

    protected String getType(String cType) throws IOException {
        return cType.substring(0, cType.indexOf("/"));
    }
}
