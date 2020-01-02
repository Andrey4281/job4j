package presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONServlet extends HttpServlet {
    AtomicInteger id = new AtomicInteger(0);
    Map<Integer, Item> map = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), map.values());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder res = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            reader.lines().forEach(res::append);
        }
        ObjectMapper mapper = new ObjectMapper();
        Item item = (Item) mapper.readValue(res.toString(), Item.class);
        map.put(id.getAndIncrement(), item);
    }
}
