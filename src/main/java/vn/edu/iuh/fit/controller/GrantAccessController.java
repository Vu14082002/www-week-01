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
import java.util.Collections;
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
        grantAccessService= new GrantAccessService();
        accountService = new AccountService();
        roleService = new RoleService();
        grantAccessList = new ArrayList<>();
        accountList = new ArrayList<>();
        grantAccessList=grantAccessService.findAll();
        accountList =accountService.findAllAccountNoRole();
        roleList=roleService.findAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action =req.getParameter("action");
        switch (action){
            case "grant-accesses":{
                req.getSession().setAttribute("grantAccessList",grantAccessList);
                req.getRequestDispatcher(PATH_VIEW_GRANT_ACCESS).forward(req,resp);
                break;
            }
            case "add":{
                req.getSession().setAttribute("roleList",roleList);
                req.getSession().setAttribute("accountList",accountList);
                req.getRequestDispatcher(PATH_VIEW_ADD_GRANT_ACCESS_FORM).forward(req,resp);
                break;
            }
            default:req.getRequestDispatcher(PATH_VIEW_GRANT_ACCESS);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action =req.getParameter("action");
        switch (action){
            case "add":{
                update(req,resp);
                resp.sendRedirect(req.getContextPath()+"/grant-access?action=grant-accesses");
                break;
            }
        }
    }
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<GrantAccess> dataList = getData(req, resp);
        for (GrantAccess grantAccess : dataList){
            GrantAccess saved = grantAccessService.save(grantAccess);
            grantAccessList.add(saved);
        }
    }
    private List<GrantAccess> getData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GrantAccess> grantAccesses = new ArrayList<>();
        String accountId =req.getParameter("accountId");
        List<String> roleId = Collections.singletonList(req.getParameter("roleId"));
        String note =req.getParameter("note");
        roleId.forEach(e->{
            Account account =accountService.findById(accountId);
            Role role = roleService.findById(e);
            grantAccesses.add(new GrantAccess(role,account,true,note));
        });
        return grantAccesses;
    }
}
