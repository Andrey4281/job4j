package presentation;

import logic.ValidateImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Views/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = ValidateImpl.getInstance().findUserByLogin(login);
        if (user == null || !password.equals(user.getPassword())) {
            req.setAttribute("error", "Invalid login or password!");
            doGet(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        }
    }
}
