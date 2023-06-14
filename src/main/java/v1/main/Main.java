package v1.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import v1.servlets.Default;

public class Main {
    public static void main(String[] args) throws Exception {

        Default defaultServlet = new Default();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS); // Servlet handler
        context.addServlet(new ServletHolder(defaultServlet), "/*");
        // now container know: frontend works with requests made to /authform

        Server server = new Server(8080);    // Jetty server, port 8080
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
