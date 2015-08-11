/*jadclipse*/// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.

package org.brickred.socialauth.spring.bean;

import org.brickred.socialauth.SocialAuthManager;

public class SocialAuthTemplate {

	public SocialAuthTemplate() {
	}

	public SocialAuthManager getSocialAuthManager() {
		return socialAuthManager;
	}

	public void setSocialAuthManager(SocialAuthManager socialAuthManager) {
		this.socialAuthManager = socialAuthManager;
	}

	private SocialAuthManager socialAuthManager;
}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\Chandan\newworkspace\inCyyte_v1\WebContent\WEB-INF\lib\socialauth-spring-2.0-beta2.jar
	Total time: 116 ms
	Jad reported messages/errors:
The class file version is 50.0 (only 45.3, 46.0 and 47.0 are supported)
	Exit status: 0
	Caught exceptions:
*/