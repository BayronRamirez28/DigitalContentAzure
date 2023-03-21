package com.DigitalContentV2.DigitalContentv2.utility;

import javax.servlet.http.HttpServletRequest;

public class Utility {

	public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
