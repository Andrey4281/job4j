package presentation;

import logic.ValidateImpl;
import model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserActionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (checkRequest(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null && !user.getRole().getRoleName().equals("administrator")) {
                request.setAttribute("current", user);
                request.setAttribute("users", ValidateImpl.getInstance().findAll());
                request.getRequestDispatcher("/WEB-INF/Views/user.jsp").forward(servletRequest, servletResponse);
                return;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean checkRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("/signin") || request.getRequestURI().contains("/download") || request.getRequestURI().contains("/signout")
                || (request.getParameter("action") != null && request.getParameter("action").equals("update") || request.getRequestURI().contains("/static")
                || request.getRequestURI().contains("/json"));
    }
}
