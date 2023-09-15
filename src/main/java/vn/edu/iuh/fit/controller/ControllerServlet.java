package vn.edu.iuh.fit.controller;

import jakarta.ejb.Local;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.Log;
import vn.edu.iuh.fit.service.AccountService;
import vn.edu.iuh.fit.service.LogService;
import vn.edu.iuh.fit.util.Connection;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(urlPatterns = {"/ControllerServlet", "/controllerservlet"})
public class ControllerServlet extends HttpServlet {
    private AccountService accountService;
    private LogService logService;
    List<Account> accountList;

    @Override
    public void init() throws ServletException {
        Connection.getInstance().getEntityManagerFactory().createEntityManager();
        accountService = new AccountService();
        logService= new LogService();
        accountList = accountService.findAll();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        String action = req.getParameter("action");
        switch (action) {
            case "login": {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                break;
            }
            case "logout": {
                req.getSession().invalidate();

                resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=login");
                break;
            }
            case "account": {
                resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
                break;
            }
            case "role": {
                resp.sendRedirect(req.getContextPath() + "/role?action=roles");
                break;
            }
            case "log": {
                resp.sendRedirect(req.getContextPath() + "/log?action=logs");
                break;
            }
            case "grant-access": {
                resp.sendRedirect(req.getContextPath() + "/grant-access?action=grant-accesses");
                break;
            }
            default:
                resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acction = req.getParameter("action");
        switch (acction) {
            case "loginsubmit": {
                authentication(req, resp);
                break;
            }
            case "logoutsubmit": {
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=login");
                break;
            }
        }
    }

    private void authentication(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Account accountLogin = accountService.findByEmailOrId(username, password);
        if (accountLogin != null) {
            req.getSession().invalidate();
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", accountLogin.getId());
            Log log = new Log(accountLogin.getId(),"");
            logService.save(log);
            resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
        } else {
            req.getSession().setAttribute("status", "user name or password wrong");
            resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=login");
        }
    }
}


