package vn.edu.iuh.fit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.GrantAccess;
import vn.edu.iuh.fit.enties.Log;
import vn.edu.iuh.fit.enties.Status;
import vn.edu.iuh.fit.service.AccountService;
import vn.edu.iuh.fit.service.GrantAccessService;
import vn.edu.iuh.fit.service.LogService;
import vn.edu.iuh.fit.util.Connection;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/ControllerServlet", "/controllerservlet"})
public class ControllerServlet extends HttpServlet {
    private AccountService accountService;
    private GrantAccessService grantAccessService;
    private LogService logService;
    List<Account> accountList;

    @Override
    public void init() throws ServletException {
        Connection.getInstance().getEntityManagerFactory().createEntityManager();
        accountService = new AccountService();
        logService = new LogService();
        grantAccessService = new GrantAccessService();
        accountList = accountService.findAll();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") == null) {
            req.getSession().setAttribute("role", "user");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
        String action = req.getParameter("action");
        switch (action) {
            case "homepage": {
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
            case "admin": {
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
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
            case "grant-accesses": {
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
            authorization(accountLogin, req, resp);
        } else {
            req.getSession().setAttribute("status", "user name or password wrong");
            resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=login");
        }

    }

    private void authorization(Account account, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        HttpSession session = req.getSession(true);
        session.setAttribute("userId", account.getId());
        System.out.println("-------------------------");
        System.out.println(account.getGrantAccesses().size());
        if (account.getGrantAccesses().size() < 1) {
            resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=login");
        } else {
            Log log = new Log(account.getId(), "");
            String roleId = account.getGrantAccesses().iterator().next().getRole().getId();
            if (roleId.equalsIgnoreCase("admin")
            ) {
                logService.save(log);
                req.getSession().setAttribute("role", "admin");
                resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=account");
            } else if (roleId.equalsIgnoreCase("user")) {
                logService.save(log);
                req.getSession().setAttribute("role", "user");
                resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=homepage");
            } else {
                resp.sendRedirect(req.getContextPath() + "/controllerservlet?action=login");
            }
        }

    }
}


