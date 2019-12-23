package presentation;

import com.google.common.base.Joiner;
import logic.ValidateImpl;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AddUserInterface;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUploadServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserUploadServlet.class);
    private final DispatchPattern pattern = new DispatchPattern();

    private class DispatchPattern {
        private final Map<String, AddUserInterface> dispatch = new HashMap<>();

        public void init() {
            dispatch.put("name", (user, param)-> {
               user.setName(param);
            });
            dispatch.put("login", (user, param)-> {
                user.setLogin(param);
            });
            dispatch.put("email", (user, param)-> {
                user.setEmail(param);
            });
            dispatch.put("file", (user, param)-> {
               user.setPhotoId(param);
            });
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.pattern.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File path = new File("images");
            if (!path.exists()) {
                path.mkdirs();
            }
            User user = new User();
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(Joiner.on(File.separator).join("images", item.getName()));
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                    user.setPhotoId(item.getName());
                } else {
                    pattern.dispatch.get(item.getFieldName()).addField(user, item.getString());
                }
            }
            ValidateImpl.getInstance().add(user);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
