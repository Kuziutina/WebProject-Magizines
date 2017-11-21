package AjaxServlet;

import Helper.SenderEmail;
import Models.User;
import Repositories.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxRestoringServlet")
public class AjaxRestoringServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        UserRepo userRepo = new UserRepo();
        User user = userRepo.getUserByLogin(email);
        JSONObject jo = new JSONObject();

        if (user == null) {
            jo.put("errors", true);
        }
        else {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
            String pwd = RandomStringUtils.random( 8, characters );
            userRepo.updateUserPassword(user, pwd);

            SenderEmail senderEmail = new SenderEmail(pwd, email);
            senderEmail.run();
//            senderEmail.sendMessage();
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();

    }
}
