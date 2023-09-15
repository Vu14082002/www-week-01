package vn.edu.iuh.fit.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.util.Connection;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.logging.LogRecord;

@WebFilter(urlPatterns = {"/account/*","/log/*","/role/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        if (checkLogin(servletRequest, servletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {

            response.sendRedirect(request.getContextPath() + "/controllerservlet?action=login");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private Boolean checkLogin(ServletRequest request, ServletResponse response) {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        return session != null && session.getAttribute("userId") != null;
    }
}
