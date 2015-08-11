package com.incyyte.app.web.controller;

import com.incyyte.app.domain.Dashboard;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.HomeService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.AmazonS3ClientHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class MultiController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private HomeService homeSrv;

    @RequestMapping("/userOption.cyt")
    public ModelAndView userOptionPage(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

        String userAddr = request.getParameter("userAddr");
        request.getSession().setAttribute("userAddr", userAddr);
        Logger.debug("userAddr opt -" + userAddr);
        ModelAndView mav = new ModelAndView("userOption");
        mav.addObject("userAddr", userAddr);
        return mav;
    }

    @RequestMapping("/profile.cyt")
    public ModelAndView profilePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("profile/incyyteProfile");
        return mav;
    }

    @RequestMapping("/thankYouPage.cyt")
    public ModelAndView thankYouPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("thankYouPage");
        return mav;
    }

    @RequestMapping("/logout.cyt")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Logger.debug("logout");
        HttpSession session = request.getSession();
        session.removeAttribute(SessionKeys.LOGIN_USER);
        session.invalidate();
        Cookie[] cookie = request.getCookies();
    	for (int i = 0; i < cookie.length; i++) {
    		   if(cookie[i].getName().equals("email")) {
    			   Logger.debug("If Cokiee value::" + cookie[i].getValue());
    			   cookie[i].setMaxAge(0);
    			   cookie[i].setPath("/");
    			   response.addCookie(cookie[i]);
    		   }
    	}
        return "redirect:snLogout.do";
    }

    @RequestMapping("/displayvideo.cyt")
    public ModelAndView displayVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("main/display_video");
        mav.addObject("incyyteVideo", "ui/video/Bear.flv");
        return mav;
    }

    @RequestMapping("/validatePageName.cyt")
    @ResponseBody
    public String validatePageName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Logger.debug("%%%%%%%%%%% validatePageName - " + request.getParameter("name"));
        String pageName = request.getParameter("name");

        if (StringUtils.isEmpty(pageName)) {
            return "Page name can not be empty";
        } else if (StringUtils.isWhitespace(pageName)) {
            return "Page name can not contain space";
        } else if (!StringUtils.isAlphanumeric(pageName) || !StringUtils.isAlpha(pageName)) {
            return "Page name contain invalid characters";
        }
        return "";
    }

    @RequestMapping("/dashboard.cyt")
    @ResponseBody
    public String dashboardPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Logger.debug("dashboardPage");
        User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        Dashboard dashboard = homeSrv.updateUserDashboard(user.getEmail());

        if (dashboard == null)
            dashboard = new Dashboard();

        Logger.debug("Incoming: " + dashboard.getIncoming());
        Logger.debug("Sent: " + dashboard.getSent());
        Logger.debug("Completed: " + dashboard.getCompleted());
        Logger.debug("Petitions: " + dashboard.getPetitions());

        request.getSession().setAttribute(SessionKeys.DASHBOARD, dashboard);

        StringBuilder dash = new StringBuilder();
        dash.append("<div id='portletLinks' class='panelLink'>");
        dash.append("  Incoming (" + dashboard.getIncoming() + ")");
        dash.append("</div><BR/>");
        dash.append("<div id='portletLinks' class='panelLink'> ");
        dash.append("  Sent (" + dashboard.getSent() + ") ");
        dash.append("</div><BR/>");
        dash.append("<div id='portletLinks' class='panelLink'>	");
        dash.append("  Completed (" + dashboard.getCompleted() + ")");
        dash.append("</div><BR/>");
        dash.append("<div id='portletLinks' class='panelLink'> 		");
        dash.append("  	Petitions (" + dashboard.getPetitions() + ")");
        dash.append("</div><BR/>");

        Logger.debug(dash.toString());
        return dash.toString();
    }

    @RequestMapping("/displayuploadfile.cyt")
    public ModelAndView displayUploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("display_uploadfile");
        Logger.debug("displayUploadFile fileLocation - " + request.getParameter("uploadfile"));
        String ctype = request.getParameter("ctype");
        String ftype = request.getParameter("ftype");
        String uploadfile = request.getParameter("uploadfile");
        String fname = request.getParameter("fname");
        Logger.debug("%%%%% displayUploadFile Parameter - " + ctype + " - " + ftype + "  - " + fname + "  - " + uploadfile);
        /*File file = new File(serverPath + File.separator + ftype + File.separator + fname);

          if(!file.exists()){
              File dir = new File(serverPath + File.separator + ftype);
              if(!dir.exists())
                  dir.mkdir();
              Logger.debug("The url dir is = " + dir.getAbsolutePath());
              file = new File(dir.getAbsolutePath() + File.separator + fname);
              //file.createNewFile();
              Logger.debug("%%%%%%% file - " + file.getAbsolutePath());
              AmazonS3ClientHelper.downloadFile(file.getAbsolutePath(), uploadfile, ftype);
          }*/

        mav.addObject("fname", fname);
        mav.addObject("ctype", ctype);
        mav.addObject("ftype", ftype);
        mav.addObject("uploadfile", uploadfile);
        return mav;
    }

    public static String downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filename = (String) request.getParameter("fname");
        String ctype = (String) request.getParameter("ctype");
        String containerName = (String) request.getParameter("ftype");
        String uploadfile = (String) request.getParameter("uploadfile");
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
        //File downloadfile = null;

        try {

            Logger.debug("%%%%% downloadFile Parameter - " + ctype + " - " + containerName + "  - " + filename + "  - " + uploadfile);

            if (uploadfile != null) {

                uploadfile = uploadfile.substring(uploadfile.indexOf("/") + 1, uploadfile.length());

                myOut = response.getOutputStream();

                //String serverPath = request.getSession().getServletContext().getRealPath("/");

                //downloadfile = new File(serverPath + "/" + uploadfile);

                //Logger.debug("The downloadfile is = " + downloadfile.getAbsoluteFile());

                //set response headers
                response.setContentType(ctype);

                response.addHeader("Content-Disposition", "attachment; filename=" + filename);

                //response.setContentLength( (int) downloadfile.length( ) );

                FileInputStream input = (FileInputStream) AmazonS3ClientHelper.downloadFile(uploadfile, containerName);
                buf = new BufferedInputStream(input);
                int readBytes = 0;

                //read from the file; write to the ServletOutputStream
                while ((readBytes = buf.read()) != -1)
                    myOut.write(readBytes);
            }

        } catch (IOException ioe) {
            throw new ServletException(ioe.getMessage());
        } finally {

            //close the input/output streams
            if (myOut != null)
                myOut.close();
            if (buf != null)
                buf.close();
        }


        return "success";
    }


    public static String getType(String fileUrl) throws Exception {
        URL u = new URL(fileUrl);
        URLConnection uc = u.openConnection();
        String type = uc.getContentType();
        return type;
    }

    @RequestMapping("/photo.cyt")
    public void getPhoto(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);

        if (null != user.getPhoto()) {
            response.setContentType("image/jpg");
            response.getOutputStream().write(user.getPhoto());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }


}
