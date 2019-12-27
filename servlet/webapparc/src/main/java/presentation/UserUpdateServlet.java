package presentation;

import logic.Validate;
import logic.ValidateImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class UserUpdateServlet extends HttpServlet {
    private final Validate logic = ValidateImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (req.getParameter("id") == null) {
            writer.append("You should specify id of user!");
        } else {
            User user = logic.findById(Integer.parseInt(req.getParameter("id")));
            if (user == null) {
                writer.append("User not found!");
            } else {
                writer.append("<!DOCTYPE html>"
                        + "<html lang=\"en\">"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Update user</title>"
                        + "</head>"
                        + "<body>"
                        + "<form action='" + req.getContextPath() + "/" + "'" + " method='post'>"
                        + "Name: " + "<input type='text' name='name'" + " value='" + user.getName() + "'/>" + "<br>"
                        + "Login: " + "<input type='text' name='login'" + " value='" + user.getLogin() + "'/>" + "<br>"
                        + "Email: " + "<input type='text' name='email'" + " value='" + user.getEmail() + "'/>" + "<br>"
                        + "CreateDate" + "<input type='text' name='createDate'" + " value='" + user.getCreateDate() + "'/>" + "<br>"
                        + "<input type='hidden' name='action' value='update'/>"
                        + "<input type='hidden' name='id' value='" + user.getId() + "'/>"
                        + "Submit: " + "<input type='submit' value='update'/>" + "</form>"
                        + "</body>"
                        + "</html>");
            }
        }
        writer.flush();
    }
}
