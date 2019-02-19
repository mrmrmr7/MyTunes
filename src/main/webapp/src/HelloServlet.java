import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws   IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        Enumeration<String> enumiration = req.getParameterNames();

        while (enumiration.hasMoreElements()) {
            String elementName = enumiration.nextElement();
            System.out.println(elementName + " = " + req.getParameter(elementName));
        }
        resp
                .getWriter()
                .write("<html> " +
                        "<body>" +
                        "name = " + name +
                        "<br>surname = " + surname +
                        "<body/>" +
                        "<html/>");
//        try {
//            req.getRequestDispatcher("/api.jsp").forward(req, resp);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
