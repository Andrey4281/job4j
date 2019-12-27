package presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class UserCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Create User</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/" + "'" + " method='post'>"
                + "Name: " + "<input type='text' name='name'/>" + "<br>"
                + "Login: " + "<input type='text' name='login'/>" + "<br>"
                + "Email: " + "<input type='text' name='email'/>" + "<br>"
                + "CreateDate" + "<input type='text' name='createDate'/>" + "<br>"
                + "<input type='hidden' name='action' value='add'/>"
                + "Submit: " + "<input type='submit' value='create'/>" + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }
}
