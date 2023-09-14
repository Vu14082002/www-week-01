package vn.edu.iuh.fit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.Status;
import vn.edu.iuh.fit.service.AccountService;
import vn.edu.iuh.fit.util.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/account"})
public class AccountController extends HttpServlet {
    private static String PATH_VIEW_ACCOUNT ="view/account-manager/account.jsp";
    private static String PATH_VIEW_ACCOUNT_FORM ="view/account-manager/accountForm.jsp";
    private static String PATH_VIEW_ADD_ACCOUNT_FORM ="view/account-manager/addAccountForm.jsp";
    private AccountService accountService;
    private List<Account> accountList;

    @Override
    public void init() throws ServletException {
        Connection.getInstance();
        accountService = new AccountService();
        accountList=new ArrayList<>();
        accountList = accountService.findAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") == null) {
            resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
        }
        String action = req.getParameter("action");
        switch (action) {
            case "update": {
                Account account = accountService.findById(req.getParameter("id").toString());
                req.setAttribute("account", account);
                req.getRequestDispatcher(PATH_VIEW_ACCOUNT_FORM).forward(req, resp);
                break;
            }
            case "view": {
                break;
            }
            case "delete": {
                deleteAccount(req, resp);
                break;

            }
            case "add": {
                req.getRequestDispatcher(PATH_VIEW_ADD_ACCOUNT_FORM).forward(req, resp);
                break;
            }
            case "accounts": {
                accountList=accountService.findAll();
                req.getSession().setAttribute("accountList", accountList);
                req.getRequestDispatcher(PATH_VIEW_ACCOUNT).forward(req, resp);
                break;
            }
            default: {
                resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("idDelete") != null) {
            accountService.deleteById(req.getParameter("idDelete"));
        }

        String action = req.getParameter("action");
        switch (action) {
            case "update": {
                update(req, resp);
                break;
            }
            case "add": {
                addAccount(req, resp);
                break;
            }
            default:
                break;
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getData(req, resp);

        Account serviceById = accountService.findById(account.getId());
        account.setGrantAccesses(serviceById.getGrantAccesses());
        boolean updated = accountService.update(account);
        if (!updated) {
            req.getSession().setAttribute("checkupdate", "false");
            resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
        }
        updateListAccount(account);
        req.getSession().setAttribute("checkupdate", "success");
        resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
    }

    private void addAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getData(req, resp);
        Account savedAccount = accountService.save(account);
        accountList.add(savedAccount);
        resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
    }

    private void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Account account = accountService.findById(id);
        account.setStatusValue(-1);
        account.setStatus(Status.from(account.getStatusValue()));
        accountService.deleteById(id);
        updateListAccount(account);
        resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
    }
    private Account getData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        Account account = new Account(id, fullname, password, email,
                phone, Status.from(Integer.parseInt(status)).getCode(), Status.from(Integer.parseInt(status)));
        return account;
    }

    private void updateListAccount(Account account) {
        accountList = accountList.stream().map(e -> {
            if (e.getId().equalsIgnoreCase(account.getId())) {
                e.setEmail(account.getEmail());
                e.setFullName(account.getFullName());
                e.setPhone(account.getPhone());
                e.setPassword(account.getPassword());
                e.setStatus(Status.from(account.getStatusValue()));
                e.setStatusValue(account.getStatusValue());
            }
            return e;
        }).collect(Collectors.toList());
    }


}
