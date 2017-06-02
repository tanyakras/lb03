package n1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by admin on 14.04.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(80);
        ServletContextHandler h = new ServletContextHandler();
        h.setResourceBase("web");
        h.addServlet(DefaultServlet.class, "/");
        h.addServlet(RestServlet.class,"/rest/*");
        h.addServlet(TestServlet.class,"");
        h.addServlet(TestServlet.class,"/add");
        h.addServlet(TestServlet.class,"/delete");
        h.addServlet(TestServlet.class,"/view");

        server.setHandler(h);
        server.start();

    }
}
