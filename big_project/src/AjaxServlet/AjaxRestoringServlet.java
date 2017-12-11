package AjaxServlet;

import DAO.DAOImpl.UserDAO;
import Helper.SenderEmail;
import Models.User;
import Services.Interfaces.UserServiceInterface;
import Services.UserService;
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
    private UserServiceInterface userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getByLogin(email);
        JSONObject jo = new JSONObject();

        if (user == null) {
            jo.put("errors", true);
        }
        else {
            SenderEmail senderEmail = new SenderEmail(userService.restoringPassword(user), email);
            new Thread(senderEmail).start();
//            senderEmail.sendMessage();
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();

    }
}
