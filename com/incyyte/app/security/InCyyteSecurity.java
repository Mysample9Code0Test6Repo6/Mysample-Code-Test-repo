package com.incyyte.app.security;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InCyyteSecurity {
	
	private static volatile InCyyteSecurity instance = null;
	
	public static InCyyteSecurity getInstance() {
        if ( instance == null ) {
            synchronized ( InCyyteSecurity.class ) {
                if ( instance == null ) {
                    instance = new InCyyteSecurity();
                }
            }
        }
        return instance;
    }
	
	public HttpSession changeSessionIdentifier(HttpServletRequest request)
			{
		// get the current session
		HttpSession oldSession = request.getSession();

		// make a copy of the session content
		Map<String,Object> temp = new ConcurrentHashMap<String,Object>();
		Enumeration e = oldSession.getAttributeNames();
		while (e != null && e.hasMoreElements()) {
			String name = (String) e.nextElement();
			Object value = oldSession.getAttribute(name);
			temp.put(name, value);
		}

		// kill the old session and create a new one
		oldSession.invalidate();
		HttpSession newSession = request.getSession();
		
		// copy back the session content
      for (Map.Entry<String, Object> stringObjectEntry : temp.entrySet())
      {
         newSession.setAttribute(stringObjectEntry.getKey(), stringObjectEntry.getValue());
		}
		return newSession;
	}

}
