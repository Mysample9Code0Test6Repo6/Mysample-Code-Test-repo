package com.incyyte.app.security;

import com.incyyte.app.service.util.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class XssFilter implements Filter {

    static class FilteredRequest extends HttpServletRequestWrapper {


        public FilteredRequest(ServletRequest request) {
            super((HttpServletRequest) request);
        }

        public String sanitize(String input) {
            Pattern allowedChars = Pattern.compile("^[A-Za-z0-9\\s+@!:;_%�$?=&().,//'-]*$");
            StringBuffer result = new StringBuffer();
            if (input != null) {
                for (int i = 0; i < input.length(); i++) {

                    Matcher matcher = allowedChars.matcher("" + input.charAt(i));
                    if (matcher.matches()) {
                        result.append(input.charAt(i));
                    } else {
                        Logger.error("Xss Vulnerability found. Cleaning the input");
                    }
                }
                Logger.debug("Returned value for the parameter is =" + result);
                return result.toString();
            } else {
                Logger.debug("Input value is null hence returning null back to application");
                return null;
            }
        }

        public String getParameter(String paramName) {
            String value = super.getParameter(paramName);
            if (!("opentoken".equals(paramName))) {
                Logger.debug("The parameter passed for validation is =" + paramName);
                value = sanitize(value);
            }
            //value = sanitize(value);
            return value;
        }

        public String[] getParameterValues(String paramName) {
            String values[] = super.getParameterValues(paramName);
            if (!("opentoken".equals(paramName))) {
                Logger.debug("The parameter passed for validation is =" + paramName);
                for (int index = 0; index < values.length; index++) {
                    values[index] = sanitize(values[index]);
                }
            }
            return values;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            FilteredRequest newRequest = new FilteredRequest(request);
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement().toString();
                String paramValue = newRequest.getParameter(paramName);
            }
            chain.doFilter(newRequest, response);
        } else {
            Logger.error("Its not a http request...seems invalid in this case");
            filterConfig.getServletContext().getRequestDispatcher("/login.do").forward(request, response);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    private FilterConfig filterConfig;

}
