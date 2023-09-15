package vn.edu.iuh.fit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.Log;
import vn.edu.iuh.fit.service.AccountService;
import vn.edu.iuh.fit.service.LogService;

import java.io.IOException;
import java.util.List;

@WebServlet( urlPatterns = {"/log"})
public class LogController extends HttpServlet {
    private static String PATH_VIEW_LOG = "view/log-manager/log.jsp";
    private static String PATH_VIEW_LOG_FORM = "view/log-manager/logForm.jsp";
    private static String PATH_VIEW_ADD_LOG_FORM = "view/log-manager/addLogForm.jsp";
    private LogService logService;
    private List<Log> logList;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") == null) {
            resp.sendRedirect(req.getContextPath() + "/account?action=accounts");
        }
        String action = req.getParameter("action");
        switch (action) {
            case "update": {
                Log log = logService.findById(Long.valueOf(req.getParameter("id").toString()));
                req.setAttribute("account", log);
                req.getRequestDispatcher(PATH_VIEW_LOG_FORM).forward(req, resp);
                break;
            }
            case "view": {
                break;
            }
//            case "delete": {
//                delete(req, resp);
//                break;
//            }
            case "add": {
                req.getRequestDispatcher(PATH_VIEW_ADD_LOG_FORM).forward(req, resp);
                break;
            }
            case "logs": {
                logList = logService.findAll();
                req.getSession().setAttribute("logList", logList);
                req.getRequestDispatcher(PATH_VIEW_LOG).forward(req, resp);
                break;
            }
            default: {
                resp.sendRedirect(req.getContextPath() + "/log?action=logs");
            }
        }
    }
}
