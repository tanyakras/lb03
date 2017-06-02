package n1;


import freemarker.cache.FileTemplateLoader;
import freemarker.core.Configurable;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 * org.h2.tools.Console
 */

/**
 * Created by admin on 14.04.2017.
 */
public class TestServlet extends HttpServlet {

    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
    private ToDoList list = new ToDoList();
    {//list.add("");
    try{
        cfg.setTemplateLoader(new FileTemplateLoader(new File(".")));
        cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri=req.getRequestURI();
        if(uri.equals("/add"))
        {String what=req.getParameter("task");
        try {
            list.add(what);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        } else if(uri.equals("/delete")){
            try{
                String id=req.getParameter("id");
                list.delete(Integer.parseInt(id));
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        try {
            Template t = cfg.getTemplate("todo.html");
            Map<String, Object> map = new HashMap<>();
            map.put("tasks", list.view());
            t.process(map, resp.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
        }
        /*StringBuilder buf= new StringBuilder();
        List<Smth> items = list.view();
        for (Smth item : items) {
            buf.append("<li>"+item.what+"</li>\n");
        }*/


        /*resp.getWriter().write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Список дел</title>\n" +
                "    <body>\n" +
                "<form method=\"post\" action=\"add\">\n" +
                "    Задача: <input name=\"task\">\n" +
                "    <input type=\"submit\" value=\"добавить\">\n" +
                "</form>\n" +
                "<ol>\n" +buf+
                "\n" +
                "</ol>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</head>\n" +
                "</html>");*/

    }


}
