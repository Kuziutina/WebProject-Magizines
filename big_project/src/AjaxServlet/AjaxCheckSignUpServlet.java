package AjaxServlet;

import DAO.DAOImpl.UserDAO;
import Helper.MD5Hash;
import Helper.SenderEmail;
import Models.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxCheckSignUpServlet")
public class AjaxCheckSignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repPassword = request.getParameter("repeate_password");
        String confirmation = "";
        JSONObject jo = new JSONObject();


        UserDAO userDAO = new UserDAO();
        boolean has = userDAO.getByLogin(email) != null;
        if (has) {
            jo.put("email_used", "");
        }
        else if (userDAO.getByName(username) != null) {
            jo.put("username_used", "");
        }
        else {
            confirmation = String.valueOf(java.util.UUID.randomUUID());
            User user = User.newBuilder().setName(username).setLogin(email).setPassword(MD5Hash.getHash(password))
                    .setConfirmation(confirmation).build();

            userDAO.add(user);
            user = userDAO.getByLogin(user.getLogin());
            request.getSession().setAttribute("current_user", user);
        }



        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();

        if (!has) {
            SenderEmail senderEmail = new SenderEmail("https://localhost:8080/confirmation/" + confirmation, email);
            new Thread(senderEmail).start();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean remember = Boolean.parseBoolean(request.getParameter("remember_me"));
        UserDAO userDAO = new UserDAO();
        JSONObject jo = new JSONObject();
        User user = userDAO.getByLogin(email);

        boolean errors = user == null || !user.getPassword().equals(MD5Hash.getHash(password));
        if (!errors) {
            request.getSession().setAttribute("current_user", user);
            if (remember) {
                String cookie = String.valueOf(java.util.UUID.randomUUID());
                Cookie c = new Cookie("userid", cookie);
                c.setMaxAge(15*60);
                response.addCookie(c);

                userDAO.updateCookie(user, cookie);
            }
        }
        jo.put("errors", errors);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();
    }
}
