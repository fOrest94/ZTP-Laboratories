package info.dudus;


import javax.ejb.EJB;
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
public class Controller extends HttpServlet {

    @EJB
    private Calculator calc;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter write = response.getWriter();

        try {
            calc.setNumber1(Double.valueOf(request.getParameter("value1")));
            calc.setNumber2(Double.valueOf(request.getParameter("value2")));
            calc.setSymbol(request.getParameter("value3").charAt(0));


            write.println("Result: " + calc.getResult(calc.getNumber1(), calc.getNumber2(), calc.getSymbol()));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Only real numbers are allowed");
        }
    }
}
