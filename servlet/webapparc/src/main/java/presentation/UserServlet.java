package presentation;

import logic.Validate;
import logic.ValidateImpl;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    private static final String LN = System.getProperty("line.separator");
    private final Validate logic = ValidateImpl.getInstance();
    private final DispatchPattern pattern = new DispatchPattern();


    private class DispatchPattern {
        private final Map<String, Function<User, Boolean>> dispatch = new HashMap<>();

        public void init() {
            dispatch.put("add", logic::add);
            dispatch.put("update", logic::update);
            dispatch.put("delete", logic::delete);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = logic.findAll();
        StringBuilder message = new StringBuilder();
        if (list.isEmpty()) {
            message.append("It is not exists users in system");
        } else {
            list.stream().forEach(u->message.append(u.getName()).append(LN));
        }
        resp.setContentType("text/html");
        resp.getOutputStream().print(message.toString());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.pattern.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        if (req.getParameter("id") != null) {
            user.setId(Integer.parseInt(req.getParameter("id")));
        }
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        if (req.getParameter("createDate") != null && req.getParameter("createDate") != "") {
            user.setCreateDate(Long.parseLong(req.getParameter("createDate")));
        }
        if (!pattern.dispatch.get(req.getParameter("action")).apply(user)) {
            resp.setContentType("text/html");
            resp.getOutputStream().println("Invalid operation!");
        }
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
