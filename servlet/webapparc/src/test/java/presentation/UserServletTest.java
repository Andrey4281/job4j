package presentation;

import logic.Validate;
import logic.ValidateImpl;
import logic.ValidateStub;
import model.Role;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateImpl.class)
public class UserServletTest {

    @Test
    public void whenDeleteUserThenShouldNotGetIt() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        User user = new User();
        user.setName("Andrey Semenov");
        user.setRole(new Role(1, "administrator"));
        validate.add(user);
        PowerMockito.mockStatic(ValidateImpl.class);
        when(ValidateImpl.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getSession()).thenReturn(session);
        UserServlet servlet = new UserServlet();
        servlet.init(null);

        servlet.doPost(req, resp);

        assertThat(validate.findAll().isEmpty()).isEqualTo(true);
    }

    @Test
    public void whenUpdateUserThenShouldGetUpdatedIt() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        User user = new User();
        user.setName("Andrey Semenov");
        user.setLogin("Andrey");
        user.setEmail("Andrey@mail.ru");
        user.setPassword("123");
        user.setRole(new Role(1, "administrator"));
        validate.add(user);
        PowerMockito.mockStatic(ValidateImpl.class);
        when(ValidateImpl.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Andrey");
        when(req.getParameter("login")).thenReturn("Andrey123");
        when(req.getParameter("email")).thenReturn("Andrey@yandex.ru");
        when(req.getParameter("password")).thenReturn("1234");
        when(req.getParameter("role")).thenReturn("user");
        when(req.getParameter("action")).thenReturn("update");
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);
        when(req.getSession()).thenReturn(session);
        UserServlet servlet = new UserServlet();
        servlet.init(null);
        User expected = new User();
        expected.setName("Andrey");
        expected.setLogin("Andrey123");
        expected.setEmail("Andrey@yandex.ru");
        expected.setPassword("1234");
        expected.setRole(new Role(0, "user"));

        servlet.doPost(req, resp);

        assertThat(validate.findById(0)).isEqualTo(expected);
    }

    @Test
    public void whenFindAllUserThenShouldGetThem() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        User user = new User();
        user.setName("Andrey Semenov");
        validate.add(user);
        PowerMockito.mockStatic(ValidateImpl.class);
        when(ValidateImpl.getInstance()).thenReturn(validate);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getRequestDispatcher("/WEB-INF/Views/index.jsp")).thenReturn(rd);

        new UserServlet().doGet(req, resp);

        verify(req).setAttribute("users", validate.findAll());
        verify(rd).forward(req, resp);
    }
}