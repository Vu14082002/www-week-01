package vn.edu.iuh.fit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.enties.Role;
import vn.edu.iuh.fit.enties.Status;
import vn.edu.iuh.fit.service.RoleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/role"})
public class RoleController extends HttpServlet {
    private static String PATH_VIEW_ROLE = "view/role-manager/role.jsp";
    private static String PATH_VIEW_ROLE_FORM = "view/role-manager/roleForm.jsp";
    private static String PATH_VIEW_ADD_ROLE_FORM = "view/role-manager/addRoleForm.jsp";
    private static String PATH_VIEW__ROLE_DETAIL = "view/role-manager/roleDetail.jsp";

    private RoleService roleService;
    private static List<Role> roleList;

    @Override
    public void init() throws ServletException {
        roleService = new RoleService();
        roleList = new ArrayList<>();
        roleList = roleService.findAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "roles": {
                req.getSession().setAttribute("roles", roleList);
                req.getRequestDispatcher(PATH_VIEW_ROLE).forward(req, resp);
                break;
            }
            case "add": {
                req.getRequestDispatcher(PATH_VIEW_ADD_ROLE_FORM).forward(req, resp);
                break;
            }
            case "update": {
                Role role = roleService.findById(req.getParameter("id").toString());
                req.setAttribute("role", role);
                req.getRequestDispatcher(PATH_VIEW_ROLE_FORM).forward(req, resp);
                break;
            }
            case "view": {
                Role role = roleService.findById(req.getParameter("id").toString());
                req.setAttribute("role", role);
                req.getRequestDispatcher(PATH_VIEW__ROLE_DETAIL).forward(req, resp);
                break;
            }
            case "delete": {
                delete(req, resp);
                break;

            }
            default: {
                resp.sendRedirect(req.getContextPath() + "/role?action=roles");
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("idDelete") != null) {
            roleService.deleteById(req.getParameter("idDelete"));
        }
        String action = req.getParameter("action");
        switch (action) {
            case "update": {
                update(req, resp);
                break;
            }
            case "add": {
                save(req, resp);
                break;
            }
            default:
                break;
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role role = getData(req, resp);
        Role savedRole = roleService.save(role);
        roleList.add(savedRole);
        resp.sendRedirect(req.getContextPath() + "/role?action=roles");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role role = getData(req, resp);
        role.setAccesses(roleService.findById(role.getId()).getAccesses());
        boolean updated = roleService.update(role);
        if (!updated) {
            req.getSession().setAttribute("checkupdate", "false");
            resp.sendRedirect(req.getContextPath() + "/role?action=accounts");
        }
        updateListRole(role);
        req.getSession().setAttribute("checkupdate", "success");
        resp.sendRedirect(req.getContextPath() + "/role?action=roles");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (roleService.deleteById(id)) {
            Role role = roleService.findById(id);
            updateListRole(role);
            resp.sendRedirect(req.getContextPath() + "/role?action=roles");
        }
    }

    private Role getData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role role = new Role();
        role.setId(req.getParameter("id"));
        role.setDescription(req.getParameter("description"));
        role.setName(req.getParameter("roleName"));
        role.setStatusValue(Integer.parseInt(req.getParameter("status")));
        role.setStatus(Status.from(Integer.parseInt(req.getParameter("status"))));
        return role;
    }

    private void updateListRole(Role role) {
        for (Role e : roleList) {
            if (e.getId().equalsIgnoreCase(role.getId())) {
                e.setName(role.getName());
                e.setDescription(role.getDescription());
                e.setStatus(role.getStatus());
                e.setStatusValue(role.getStatusValue());
                e.setAccesses(role.getAccesses());
                break;
            }
        }

    }
}
