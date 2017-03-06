package info.dudus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by f0rest94 on 2017-03-06.
 */
@WebServlet("/main")
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");


        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String method = "Get";

        request.setAttribute("method", method);
        request.setAttribute("message", "Login: " + userName + "\nHasło: " + password);
        request.getRequestDispatcher("/result.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String method = "Post";

        request.setAttribute("method", method);
        request.setAttribute("message", "Login: " + userName + "\nHasło: " + password);
        request.getRequestDispatcher("/result.jsp").forward(request,response);
    }
}
