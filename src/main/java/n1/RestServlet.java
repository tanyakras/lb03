package n1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 19.05.2017.
 */
public class RestServlet extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private ToDoList list = new ToDoList();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String uri=req.getRequestURI();
        if(uri.startsWith("/rest/add")){
            String text = req.getParameter("text");
            try {
                list.add(text);
                gson.toJson(list.view(),resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(uri.startsWith("/rest/delete")){
            String id=req.getParameter("id");
            try {
                list.delete(Integer.parseInt(id));
                gson.toJson(list.view(),resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(uri.startsWith("/rest/view")){
            try {
                gson.toJson(list.view(),resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
