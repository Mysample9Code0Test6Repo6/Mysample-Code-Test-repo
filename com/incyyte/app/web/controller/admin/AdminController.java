package com.incyyte.app.web.controller.admin;

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.AdminService;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.util.ExcelUtils;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.controller.BaseContoller;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserModel;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController extends BaseContoller {

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private AdminService adminSrv;
    @Autowired
    private LoginService loginSrv;
    @Autowired
    private RegistrationService registrationSrv;
    @Autowired
    private QuickStartService quickStartSrv;

    @RequestMapping("/admin")
    public String adminloginpage(@ModelAttribute("loginForm") UserModel userModel, BindingResult result, HttpSession session, Model model) {
        return "admin/adminlogin";
    }

    @RequestMapping(value = "admin/submit.cyt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String processLogin(HttpServletRequest request,
                               @ModelAttribute("loginForm") UserModel userModel, Model model,
                               HttpSession session) throws Exception {

        String email = userModel.getLogin_email();
        String password = userModel.getLogin_pwd();
        Logger.debug("Login username - " + email + " - " + password);
        model.addAttribute("signUpForm", userModel);
        try {
            if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
                if (loginSrv.authenticateAdminUserLogin(email, password)) {
                    User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
                    session.setAttribute(SessionKeys.LOGIN_ADMIN_USER, user);
                    return "success";
                } else {
                    Logger.debug("authenticateUserLogin: false -  Redirect to LOGIN...");
                    return "error";
                }
            }
        } catch (AuthenticationException e) {
            Logger.error("authenticateUserLogin: Failed:", e);
            return "error";
        }
        session.removeAttribute(SessionKeys.LOGIN_USER);
        // return form success view
        throw new Exception();
    }

    public int getTotalContacts(Long userId) throws Exception {
        return adminSrv.getTotalContacts(userId);
    }

    public int getTotalUsers() throws Exception {
        return adminSrv.getTotalUsers();
    }

    public int getTotalGroups(Long userId) throws Exception {
        return adminSrv.getTotalGroups(userId);
    }

    public int getTotalIncyyte(Long userId) throws Exception {
        return adminSrv.getTotalIncyyte(userId);
    }

    public User getUserDetail(String val, String type) throws Exception {
        return loginSrv.getUserDetails(val, type);
    }

    @ModelAttribute("topIncyyteUsers")
    public List<User> getTopIncyyteUsers() {
        try {
            return adminSrv.getTopIncyyteUsers();
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return null;
    }

    @RequestMapping("admindash.cyt")
    public String adminpage(@ModelAttribute("loginForm") UserModel userModel, BindingResult result, HttpSession session, Model model) {
        if (session.getAttribute(SessionKeys.LOGIN_ADMIN_USER) == null) {
            return "redirect:admin.cyt";
        }

        try {
            model.addAttribute("totalContacts", getTotalContacts(null));
            model.addAttribute("totalUsers", getTotalUsers());
            model.addAttribute("totalGroups", getTotalGroups(null));
            model.addAttribute("totalIncyyte", getTotalIncyyte(null));
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        model.addAttribute("userModel", userModel);
        model.addAttribute("addContactForm", new UserContactModel());
        model.addAttribute("myPostInCyytes", getMyPollInCyytes(session));
        return "admin/admindash";
    }

    @RequestMapping("searchUserDetail.cyt")
    public String userDetailPage(@ModelAttribute("loginForm") UserModel userModel, BindingResult result, HttpSession session, Model model) {
        Logger.debug("userModel.getSu_email() - > " + userModel.getSu_email());
        if (session.getAttribute(SessionKeys.LOGIN_ADMIN_USER) == null) {
            return "redirect:admin.cyt";
        }

        try {
            User user = getUserDetail(userModel.getSu_email(), Constants.GET_BY_MAIL);

            if (user != null && user.getId() != null) {
                model.addAttribute("totalContacts", getTotalContacts(user.getId()));
                model.addAttribute("totalUsers", getTotalUsers());
                model.addAttribute("totalGroups", getTotalGroups(user.getId()));
                model.addAttribute("totalIncyyte", getTotalIncyyte(user.getId()));
                model.addAttribute("username", user.getUsername());

                session.setAttribute(SessionKeys.LOGIN_USER, user);
                userModel.setUsername(user.getUsername());
            } else {
                model.addAttribute("totalContacts", getTotalContacts(null));
                model.addAttribute("totalUsers", getTotalUsers());
                model.addAttribute("totalGroups", getTotalGroups(null));
                model.addAttribute("totalIncyyte", getTotalIncyyte(null));
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        model.addAttribute("userModel", userModel);
        return "admin/adminUserdash";
    }

    @RequestMapping("loadUserDetail.cyt")
    public String loadUserDetailPage(HttpServletRequest request, Model model, HttpSession session) {
        String email = request.getParameter("email");
        Logger.debug("user email - > " + email);
        UserModel userModel = new UserModel();
        if (session.getAttribute(SessionKeys.LOGIN_ADMIN_USER) == null) {
            return "redirect:admin.cyt";
        }
        try {
            User user = getUserDetail(email, Constants.GET_BY_MAIL);
            if (user != null && user.getId() != null) {
                model.addAttribute("totalContacts", getTotalContacts(user.getId()));
                model.addAttribute("totalUsers", getTotalUsers());
                model.addAttribute("totalGroups", getTotalGroups(user.getId()));
                model.addAttribute("totalIncyyte", getTotalIncyyte(user.getId()));
                model.addAttribute("username", user.getUsername());

                session.setAttribute(SessionKeys.LOGIN_USER, user);
                userModel.setUsername(user.getUsername());
                userModel.setSu_email(user.getEmail());
            } else {
                model.addAttribute("totalContacts", getTotalContacts(null));
                model.addAttribute("totalUsers", getTotalUsers());
                model.addAttribute("totalGroups", getTotalGroups(null));
                model.addAttribute("totalIncyyte", getTotalIncyyte(null));
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        model.addAttribute("userModel", userModel);
        return "admin/adminUserdash";
    }

    @RequestMapping(value = "uploadPreRegMemCSVFile.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String uploadCSVFile(HttpServletRequest request, @ModelAttribute("addContactForm") UserContactModel usercModel, BindingResult result, SessionStatus status, HttpSession session, ModelMap model) {

        FileInputStream inputStream = null;
        if (usercModel == null || usercModel.getUploadedDocFile().isEmpty()) {
            usercModel = new UserContactModel();
        } else {
            try {
                inputStream = (FileInputStream) usercModel.getUploadedDocFile().getInputStream();

                //Get the workbook instance for XLS file
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                int uploadedUserCreatedCount = 0;
                int userExistedCount = 0;

                ExcelUtils excelUtils = ExcelUtils.getInstance(workbook);

                List<User> userList = processUsers(excelUtils.getColNames(0), excelUtils.getMappedValues(0));
                List<Contact> contacts = new ArrayList<Contact>();

                if (userList != null && !userList.isEmpty()) {

                    for (User user : userList) {
                        try {
                            if (user.getEmail() != null) {

                                if (!checkEmailExist(user.getEmail())) {
                                    user.setResetPwdFlag("Y");
                                    user.setStatus("ACTIVE");
                                    user.setAcceptTerms("1");

                                    String password = null;
                                    if (user.getFirstname() != null & user.getLastname() != null) {
                                        password = user.getFirstname().substring(0, 1) + user.getLastname();
                                    } else {
                                        password = user.getLastname() != null ? user.getLastname() : user.getFirstname();
                                    }

                                    //generate new password
                                    user.setPassword(password.toLowerCase());

                                    //user.setUsername(getUserName(user.getFirstname() != null ? user.getFirstname() : user.getLastname()));
                                    Logger.debug("Email - " + user.getEmail() + "  Username - " + user.getUsername() + "  Password - " + user.getPassword());

                                    registrationSrv.signUpPreRegisteredUser(user);
                                    uploadedUserCreatedCount++;

                                    Contact contact = new Contact();
                                    contact.setFirstName(user.getFirstname());
                                    contact.setLastName(user.getLastname());
                                    contact.setEmail(user.getEmail());
                                    contact.setPassword(user.getPassword());
                                    contact.setStatus("M");
                                    contact.setBlocked("N");

                                    contacts.add(contact);
                                } else {
                                    userExistedCount++;
                                }
                            }
                        } catch (Exception e) {
                            Logger.error("Exception:", e);
                        }
                    }

                    if (contacts != null && !contacts.isEmpty()) {
                        User adminUser = (User) session.getAttribute(SessionKeys.LOGIN_ADMIN_USER);
                        if (!StringUtils.isEmpty(usercModel.getIncyyteCode())) {
                            InCyyteChart chart = quickStartSrv.getPostedInCyyteResponse(usercModel.getIncyyteCode());

                            InCyyte incyyte = chart.getIncyyte();
                            incyyte.setContacts(contacts);
                            Logger.debug("incyyte::" + incyyte);

                            quickStartSrv.shareNewsLetter(adminUser, incyyte, contacts);
                        }
                    }

                    return " No of preregistered user loaded : " + userList.size() + "<br> No of user created : " + uploadedUserCreatedCount + "<br> No of user already exist : " + userExistedCount;
                }

            } catch (FileNotFoundException e) {
                Logger.error("Exception:", e);
            } catch (Exception e) {
                Logger.error("Exception:", e);
            } finally {
                try {
                    if (inputStream != null) inputStream.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        }
        return "failure";
    }

    private String getUserName(String name) {
        int count = 0;
        while (registrationSrv.usernameExists(name) != false) {
            name = name + "" + count;
            count++;
        }
        return name;
    }

    private boolean checkEmailExist(String email) {
        return registrationSrv.emailExists(email);
    }

    public List<User> processUsers(List<String> colNames, List<Map<String, Object>> columnMaps) {

        List<User> userList = new ArrayList<User>();
        int rowCount = 0;
        /*int entryCount = 0;        
     int successUploadCount = 0;*/
        String colKey = null;
        Iterator<String> colNamesIt = colNames.iterator();
        Iterator<Map<String, Object>> columnMapsIt = columnMaps.iterator();

        while (columnMapsIt.hasNext()) {
            Map<String, Object> columnMap = columnMapsIt.next();
            User user = new User();

            if (!checkForBlankRow(columnMap)) {

                try {

                    while (colNamesIt.hasNext()) {

                        colKey = colNamesIt.next();

                        if (colKey.indexOf("First") != -1) {
                            if (columnMap.get(colKey) != null) {
                                String val = processValue(columnMap.get(colKey).toString().trim());
                                user.setFirstname(val);
                            }
                        } else if (colKey.indexOf("Last") != -1) {
                            if (columnMap.get(colKey) != null) {
                                String val = processValue(columnMap.get(colKey).toString().trim());
                                user.setLastname(val);
                            }
                        } else if (colKey.indexOf("Email") != -1) {
                            if (columnMap.get(colKey) != null) {
                                String val = processValue(columnMap.get(colKey).toString().trim());
                                user.setEmail(val);
                            }
                        } else if (colKey.indexOf("Birth") != -1) {
                            if (columnMap.get(colKey) != null) {
                                String val = processValue(columnMap.get(colKey).toString().trim());
                                Integer intVal = Double.valueOf(val).intValue();
                                user.setBirthYear(Integer.valueOf(intVal));
                            }
                        } else if (colKey.indexOf("Gender") != -1) {
                            if (columnMap.get(colKey) != null) {
                                String val = processValue(columnMap.get(colKey).toString().trim());
                                user.setGender(val);
                            }
                        } else if (colKey.indexOf("Post") != -1) {
                            if (columnMap.get(colKey) != null) {
                                String val = processValue(columnMap.get(colKey).toString().trim());
                                user.setPostalCodeArea(val);
                            }
                        } else if (colKey.indexOf("Country") != -1) {
                            if (columnMap.get(colKey) != null) {
                                String val = processValue(columnMap.get(colKey).toString().trim());
                                user.setCountryCode(val);
                            }
                        }

                    }
                } catch (RuntimeException e) {
                    Logger.error("Exception:", e);
                    //failResult.setResult("Failed");
                    //failResult.setDetails(e.getMessage());
                    //uploadFailed = true;
                } catch (Exception e) {
                    Logger.error("Exception:", e);
                    //failResult.setResult("Failed");
                    //failResult.setDetails("Invalid : " + colKey + " value : " +columnMap.get(colKey)+ " Error : "+e.getMessage());
                    //uploadFailed = true;
                }

                colNamesIt = colNames.iterator();
            }

            userList.add(user);

        }

        return userList;

    }

    private String processValue(Object val) {

        String result = null;

        if (val != null) {
            try {
                Integer iVal = ((Double) val).intValue();
                result = iVal.toString();
            } catch (Exception e) {
                //failed hopefully because this is not a numeric
                result = ((String) val).trim();
                if ((result == null) || (!(result.length() > 0))) {
                    result = null;
                }
            }
        }
        return result;
    }

    private boolean checkForBlankRow(Map<String, Object> row) {

        boolean blank = true;

        Iterator<String> keyIt = row.keySet().iterator();
        while (keyIt.hasNext()) {
            String thisColumn = (String) keyIt.next();
            Object thisValue = row.get(thisColumn);
            if (thisValue != null) {
                blank = false;
                break;
            }
        }

        return blank;
    }

    public List<InCyyte> getMyPollInCyytes(HttpSession session) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_ADMIN_USER);
        List<InCyyte> incyytes = null;
        if (user != null && user.getId() != null) {
            try {
                incyytes = quickStartSrv.getUserInCyytesByUserId(user.getId(), Constants.SEND_BY_POST);
            } catch (Exception e) {
                Logger.error("Exception", e);
            }
        }
        session.setAttribute("myPostInCyytes", incyytes);
        return incyytes;
    }
}