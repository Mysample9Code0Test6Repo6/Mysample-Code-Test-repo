/**
 * 
 */
package com.incyyte.app.web.decorator;

import org.displaytag.decorator.TableDecorator;

import com.incyyte.app.domain.User;

/**
 * @author Remi Oseni
 *
 */
public class UserDisplayDecorator extends TableDecorator {

	public String getUsername() {
		return ((User)getCurrentRowObject()).getUsername();
	}
	
	public String getEmail() {
		return ((User)getCurrentRowObject()).getEmail();
	}
}
