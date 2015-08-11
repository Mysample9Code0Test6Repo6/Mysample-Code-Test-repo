/*
 ===========================================================================
 Copyright (c) 2010 BrickRed Technologies Limited

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sub-license, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ===========================================================================

 */

package org.brickred.controller;

import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.IncyyteProperties;
import com.incyyte.app.service.util.Logger;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.exception.ServerDataException;
import org.brickred.socialauth.exception.SocialAuthException;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.brickred.socialauth.util.MethodType;
import org.brickred.socialauth.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UpdateStatusController {
    @Autowired
    private SocialAuthTemplate socialAuthTemplate;

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public ModelAndView getRedirectURL(final HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        ConfigManager cfgMgr = getConfigManager();

        SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
        AuthProvider provider = manager.getCurrentAuthProvider();
        String statusMsg = request.getParameter("statusMessage");
        StringBuilder flag = new StringBuilder(cfgMgr.getProperty(ConfigProperties.SN_STATUSUPDATE_FLAG));

        try {
            provider.updateStatus(statusMsg);
            mv.addObject("Message", "Status Updated successfully");
        } catch (SocialAuthException e) {
            mv.addObject("Message", e.getMessage());
            Logger.error("Exception:", e);
        }
        mv.setViewName("/jsp/statusSuccess.jsp");
        return mv;
    }

    @RequestMapping(value = "/sendinvitesSn", method = RequestMethod.POST)
    public ModelAndView postmsg(final HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        ConfigManager cfgMgr = getConfigManager();

        SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
        AuthProvider provider = manager.getCurrentAuthProvider();
        String statusMsg = request.getParameter("statusMessage");
        StringBuilder flag = new StringBuilder(cfgMgr.getProperty(ConfigProperties.SN_STATUSUPDATE_FLAG));
        String ids = request.getParameter("ids");

        try {
            //if(flag.equals("on"))
            if (statusMsg == null || statusMsg.trim().length() == 0) {
                throw new ServerDataException("Status cannot be blank");
            }
            StringBuilder strb = new StringBuilder();
            strb.append("message=").append(statusMsg);

            Response res = provider.api("https://graph.facebook.com/" + ids.replace("'", "") + "/feed", MethodType.GET.toString(), null, null, strb.toString());

            Logger.debug("chandan --- >" + res.getStatus());
            mv.addObject("Message", "Status Updated successfully");
        } catch (SocialAuthException e) {
            mv.addObject("Message", e.getMessage());
            Logger.error("Exception:", e);
        }
        mv.setViewName("/jsp/statusSuccess.jsp");
        return mv;
    }


    private ConfigManager getConfigManager() {
        ConfigManager cfgMgr = null;
        try {
            cfgMgr = ConfigManager.getInstance();
            IncyyteProperties ip = new IncyyteProperties(null);
            cfgMgr.setIncyyteProperties(ip);
        } catch (ConfigurationException e) {
            Logger.error("Exception:", e);
        }
        return cfgMgr;
    }
}