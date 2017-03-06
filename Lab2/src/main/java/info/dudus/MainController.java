package info.dudus;

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

        try {
            Double value1 = Double.valueOf(request.getParameter("value1"));
            Double value2 = Double.valueOf(request.getParameter("value2"));
            String method = request.getParameter("value3");

            Calculator calculator = new Calculator();

            request.setAttribute("error", "");
            request.setAttribute("result", calculator.getResult(value1, value2, method));
            request.getRequestDispatcher("/result.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Only real numbers are allowed");
        }


    }
}
