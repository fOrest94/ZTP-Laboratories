package info.dudus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by f0rest94 on 2017-03-06.
 */
@WebServlet
public class MainController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");

        try {
            Double v1 = Double.valueOf(request.getParameter("value1"));
            Double v2 = Double.valueOf(request.getParameter("value2"));
            String method = request.getParameter("value3");
            Calculator calculator = new Calculator();
            System.out.println(method+" edfdsfdsfsdf");
            out.write(String.format("%.2f %s %.2f = %.2f", v1, method, v2, calculator.getResult(v1, v2, method)));

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Only real numbers are allowed");
        }


    }
}
