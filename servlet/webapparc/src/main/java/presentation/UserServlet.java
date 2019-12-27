package presentation;

import com.google.common.base.Joiner;
import logic.Validate;
import logic.ValidateImpl;
import model.Role;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    private static final String LN = System.getProperty("line.separator");
    private final Validate logic = ValidateImpl.getInstance();
    private final DispatchPattern pattern = new DispatchPattern();


    private class DispatchPattern {
        private final Map<String, Function<User, Boolean>> dispatch = new HashMap<>();

        public void init() {
            dispatch.put("update", logic::update);
            dispatch.put("delete", user-> {
                User res = logic.findById(user.getId());
                boolean flag = true;
                if (res != null) {
                    logic.delete(res);
                    File imagine = new File(Joiner.on(File.separator).join("images", res.getPhotoId()));
                    imagine.delete();
                } else {
                    flag = false;
                }
                return flag;
            });
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address;
        if ("update".equals(req.getParameter("action"))) {
            User currentUser = (User) req.getSession().getAttribute("user");
            if (currentUser.getRole().getRoleName().equals("user")) {
                address = "/WEB-INF/Views/editForUser.jsp";
            } else {
                address = "/WEB-INF/Views/edit.jsp";
                req.setAttribute("roles", logic.findAllRoles());
            }
            req.setAttribute("user", logic.findById(Integer.parseInt(req.getParameter("id"))));
        } else if ("add".equals(req.getParameter("action"))) {
            address = "/WEB-INF/Views/create.jsp";
            req.setAttribute("roles", logic.findAllRoles());
        } else {
            address = "/WEB-INF/Views/index.jsp";
            req.setAttribute("users", logic.findAll());
        }
        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.pattern.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = buildUserFromRequest(req);
        updateUserInSessionIfNeeded(req, user);

        if (!pattern.dispatch.get(req.getParameter("action")).apply(user)) {
            resp.setContentType("text/html");
            resp.getOutputStream().println("Invalid operation!");
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    private User buildUserFromRequest(HttpServletRequest req) {
        User user = new User();
        if (req.getParameter("id") != null) {
            user.setId(Integer.parseInt(req.getParameter("id")));
        }
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        Role role = new Role();
        if (req.getParameter("role") == null) {
            role.setRoleName("user");
        } else {
            role.setRoleName(req.getParameter("role"));
        }
        user.setRole(role);
        return user;
    }

    private void updateUserInSessionIfNeeded(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser.getRole().getRoleName().equals("user")
                && req.getParameter("action").equals("update")) {
            user.setPhotoId(currentUser.getPhotoId());
            user.setCreateDate(currentUser.getCreateDate());
            session.setAttribute("user", user);
        }
    }
}
