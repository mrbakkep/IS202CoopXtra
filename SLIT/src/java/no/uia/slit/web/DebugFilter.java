/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.web;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author even
 */
@WebFilter(filterName = "DebugFilter", urlPatterns = "/teacher/j_security_check,/admin/j_security_check")
public class DebugFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        Enumeration<String> paramNames = req.getParameterNames();

        System.out.println("=====\nDebugFilter: "+req.getRequestURL());
        while (paramNames.hasMoreElements()) {
            printParameter(req, paramNames.nextElement());
        }

        Enumeration<String> attrNames = req.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            printAttribute(req, attrNames.nextElement());
        }
        System.out.println("end\n=====");
        chain.doFilter(request, response);
    }

    private void printParameter(HttpServletRequest request, String name) {
        String[] val = request.getParameterValues(name);
        System.out.print(name+" =");
        for (String v : val) System.out.print(" "+v);
        System.out.println();
    }

    private void printAttribute(HttpServletRequest request, String name) {
        Object val = request.getAttribute(name);
        System.out.println(name+" ="+val);
    }

    @Override
    public void destroy() {
    }


}
