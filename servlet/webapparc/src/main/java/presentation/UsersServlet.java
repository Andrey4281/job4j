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
import java.util.List;

public final class UsersServlet extends HttpServlet {
    private final Validate logic = ValidateImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<User> users = logic.findAll();
        if (users.isEmpty()) {
            writer.append("It is not exists users in system");
        } else {
            StringBuilder message = new StringBuilder("<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>List of users</title>"
                    + "</head>"
                    + "<body>"
                    + "<table><tr><td>id</td><td>Name</td><td>Login</td>"
                    + "<td>Email</td><td>createDate</td>"
                    + "<td>Delete user</td>"
                    + "<td>Edit user</td>"
                    + "</tr>");

            for (User user: users) {
                message.append("<tr>");
                message.append("<td>").append(user.getId()).append("</td>");
                message.append("<td>").append(user.getName()).append("</td>");
                message.append("<td>").append(user.getLogin()).append("</td>");
                message.append("<td>").append(user.getEmail()).append("</td>");
                message.append("<td>").append(user.getCreateDate()).append("</td>");
                message.append("<td><form action='").append(req.getContextPath()).append("/")
                        .append("'").append(" method='post'>");
                message.append("<input type='hidden' name='action' value='delete'/>");
                message.append("<input type='hidden' name='id' value='").append(user.getId())
                        .append("'/>");
                message.append("<input type='submit' value='delete'/></form></td>");
                message.append("<td><form action='").append(req.getContextPath()).append("/edit")
                        .append("'").append(" method='get'>");
                message.append("<input type='hidden' name='id' value='").append(user.getId())
                        .append("'/>");
                message.append("<input type='submit' value='edit'/></form></td>");
                message.append("</tr>");
            }
            message.append("</table>"
                    + "</body>"
                    + "</html>");
            writer.append(message.toString());
        }
        writer.flush();
    }
}
