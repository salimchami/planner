package io.edukativ.myskoolin.front.web.util;

import javax.servlet.http.HttpServletRequest;


/**
 * Web util class.
 */
public class WebUtil {

    private WebUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Unable to construct WebUtil Class");
    }

    /**
     * return base url from a request.
     * @param request http servlet request
     * @return base url
     */
    public static String baseUrl(HttpServletRequest request) {
        return request.getScheme() + // "http"
            "://" +                                // "://"
            request.getServerName() +              // "myhost"
            ":" +                                  // ":"
            request.getServerPort() +              // "80"
            request.getContextPath();              // "/myContextPath" or "" if deployed in root context
    }
}
