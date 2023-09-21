package vn.edu.iuh.fit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.GrantAccess;
import vn.edu.iuh.fit.enties.Role;
import vn.edu.iuh.fit.service.AccountService;
import vn.edu.iuh.fit.service.GrantAccessService;
import vn.edu.iuh.fit.service.RoleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/grant-access")
public class GrantAccessController extends HttpServlet {
    private static String PATH_VIEW_GRANT_ACCESS = "view/grantAccess-manager/grantAccess.jsp";
    private static String PATH_VIEW_GRANT_ACCESS_FORM = "view/grantAccess-manager/grantAccessForm.jsp";
    private static String PATH_VIEW_ADD_GRANT_ACCESS_FORM = "view/grantAccess-manager/addGrantAccessForm.jsp";
    private GrantAccessService grantAccessService;
    private AccountService accountService;
    private RoleService roleService;
    private List<GrantAccess> grantAccessList;
    private List<Account> accountList;
    private List<Role> roleList;


    @Override
    public void init() throws ServletException {
        grantAccessService = new GrantAccessService();
        accountService = new AccountService();
        roleService = new RoleService();
        grantAccessList = new ArrayList<>();
        accountList = new ArrayList<>();
        grantAccessList = grantAccessService.findAll();
        roleList = roleService.findAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "grant-accesses": {
                req.getSession().setAttribute("grantAccessList", grantAccessList);
                req.getRequestDispatcher(PATH_VIEW_GRANT_ACCESS).forward(req, resp);
                break;
            }
            case "add": {
                accountList = accountService.findAllAccountNoRole();
                req.getSession().setAttribute("roleList", roleList);
                req.getSession().setAttribute("accountList", accountList);
                req.getRequestDispatcher(PATH_VIEW_ADD_GRANT_ACCESS_FORM).forward(req, resp);
                break;
            }
            case "update": {
                GrantAccess grantAccess = grantAccessService.findById(req.getParameter("account"), req.getParameter("role"));
                req.getSession().setAttribute("grantAccess", grantAccess);
                req.getRequestDispatcher(PATH_VIEW_GRANT_ACCESS_FORM).forward(req, resp);
            }
            case "delete": {
                String account = req.getParameter("account");
                String role = req.getParameter("role");
                boolean delete = grantAccessService.delete(account, role);
                if (delete) {
                    grantAccessList = grantAccessService.findAll();
                }
                resp.sendRedirect(req.getContextPath() + "/grant-access?action=grant-accesses");
            }
            default:
                req.getRequestDispatcher(PATH_VIEW_GRANT_ACCESS);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add": {
                add(req, resp);
                resp.sendRedirect(req.getContextPath() + "/grant-access?action=grant-accesses");
                break;
            }
            case "update": {
                update(req, resp);
                resp.sendRedirect(req.getContextPath() + "/grant-access?action=grant-accesses");
                break;
            }

        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GrantAccess> dataList = getData(req, resp);
        for (GrantAccess grantAccess : dataList) {
            GrantAccess saved = grantAccessService.save(grantAccess);
            grantAccessList.add(saved);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GrantAccess> data = getData(req, resp);
        GrantAccess grantAccess =data.get(0);
        boolean updated = grantAccessService.update(grantAccess);
        if (updated) {
            updateList(data.get(0));
        }
    }

    private List<GrantAccess> getData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GrantAccess> grantAccesses = new ArrayList<>();
        String accountId = req.getParameter("accountId");
        String[] roles = req.getParameterValues("roleId");
        String note = req.getParameter("note");
        Boolean check = req.getParameter("isGrant").equalsIgnoreCase("ENABLE") ? Boolean.TRUE : Boolean.FALSE;
        if (roles.length > 0) {
            for (int index = 0; index < roles.length; index++) {
                Account account = accountService.findById(accountId);
                Role role = roleService.findById(roles[index]);
                GrantAccess grantAccess = new GrantAccess(role, account, check, note);
                grantAccesses.add(grantAccess);
            }
        }
        return grantAccesses;
    }

    private void updateList(GrantAccess grantAccess) {
        for (GrantAccess e : grantAccessList) {
            if (e.getRole().getId().equalsIgnoreCase(grantAccess.getRole().getId())
                    && e.getAccount().getId().equals(grantAccess.getRole().getId())
            ) {
                e.setIsGrant(false);
                break;
            }
        }
    }
}
