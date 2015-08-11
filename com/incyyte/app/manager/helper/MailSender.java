package com.incyyte.app.manager.helper;

import java.util.List;

import javax.servlet.ServletContext;

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.Group;
import com.incyyte.app.domain.User;

public interface MailSender {
	public void sendInCyyteEmail(List<Contact> contacts, String serverURL, ServletContext servletContext) ;
	//public void sendInCyyteEmail2(List<Contact> contacts, String serverURL, ServletContext servletContext) ;
	public void sendInCyyteResponseEmail(long inCyyteID, long contactResponseID, String serverURL, ServletContext servletContex);
	public void sendWelcomeEmail(Long userId);
	
}
