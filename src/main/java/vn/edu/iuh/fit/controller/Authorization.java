package vn.edu.iuh.fit.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/account/*","/log/*","/role/*","/grant-access/*"})
public class Authorization implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        if (checkRole(servletRequest, servletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect(request.getContextPath() + "/controllerservlet?action=homepage");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    private Boolean checkRole(ServletRequest request, ServletResponse response) {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        String role=session.getAttribute("role").toString();
        return session != null && session.getAttribute("role").toString().equalsIgnoreCase("admin");
    }
}
