package AjaxServlet;

import Models.Letter;
import Models.User;
import DAO.DAOImpl.LetterDAO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "AjaxMessageServlet", urlPatterns = "/ajax_message")
public class AjaxMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipient_id = Integer.parseInt(request.getParameter("recipient_id"));
        String header = request.getParameter("header");
        String body = request.getParameter("message");

        User user = (User) request.getSession().getAttribute("current_user");
        LetterDAO letterDAO = new LetterDAO();
        boolean has = letterDAO.hasConversationWithUser(user, recipient_id);


        letterDAO.add(new Letter(header, body, new Date(System.currentTimeMillis()), user.getId(), recipient_id));


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("has", has);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jsonObject.toString());
        response.getWriter().close();
    }
}
