package Helper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "LoaderServlet")
public class LoaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = "D://" + request.getPathInfo();

        ServletContext sc = getServletContext();


        // Get the MIME type of the image
        String mimeType = sc.getMimeType(filename);
        if (mimeType == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Set content type
        response.setContentType(mimeType);

        // Set content size
        File file = new File(filename);
        response.setContentLength((int)file.length());

        // Open the file and output streams
        FileInputStream in = new FileInputStream(file);
        BufferedInputStream bs = new BufferedInputStream(in);
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);


        // Copy the contents of the file to the output stream

        int ch =0; ;
        while((ch=bs.read())!=-1)
            bos.write(ch);
        bos.flush();
        bos.close();
        bs.close();
        in.close();
        out.close();
    }
}
