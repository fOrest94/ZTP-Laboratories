package info.dudus;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by f0rest94 on 2017-03-06.
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String FILE_PATH = getServletContext().getRealPath("/WEB-INF/");
        OutputStream out = response.getOutputStream();

        try {
            int number = Integer.valueOf(request.getParameter("picture"));
            File file;
            if (number < 5) {
                response.setContentType("image/jpeg");
                file = new File(FILE_PATH + number + ".jpg");
            } else {
                response.setContentType("image/gif");
                file = new File(FILE_PATH + number + ".gif");
            }
            FileInputStream in = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int count = 0;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            response.setContentLength((int) file.length());
            out.close();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Only real numbers are allowed");
        }
    }
}