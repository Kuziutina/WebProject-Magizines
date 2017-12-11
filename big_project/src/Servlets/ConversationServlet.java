package Servlets;

import Helper.Render;
import Models.Letter;
import Models.User;
import DAO.DAOImpl.LetterDAO;
import DAO.DAOImpl.UserDAO;
import Services.Interfaces.UserServiceInterface;
import Services.UserService;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ConversationServlet", urlPatterns = "/conversation")
public class ConversationServlet extends HttpServlet {
    private UserServiceInterface userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");

        UserDAO userDAO = new UserDAO();
        int another_id = Integer.parseInt(request.getParameter("an_id"));

        User another_user = userDAO.find(another_id);

        LetterDAO letterDAO = new LetterDAO();
        List<User> conversations = letterDAO.getAllUserConversation(user);

        List<User> friends = userService.getUserWithConversation(user, conversations);
        if (!friends.contains(another_user)) {
            friends.add(another_user);
        }

        List<List<Letter>> letters = new ArrayList<>();
        for (User conver: conversations) {
            letters.add(letterDAO.getConversationByUser(user, conver));
        }

        Map<String, Object> objects = new HashMap<>();
        objects.put("another_user", another_user);
        objects.put("user", user);
        objects.put("friends", friends);
        objects.put("conversations", conversations);
        objects.put("letters", letters);

        try {
            Render.render(response, objects, "/my_conversation.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");

        List<List<Letter>> letters = new ArrayList<>();

        LetterDAO letterDAO = new LetterDAO();
        List<User> conversations = letterDAO.getAllUserConversation(user);
        List<User> friends = userService.getUserWithConversation(user, conversations);


        for (User conver: conversations) {
            letters.add(letterDAO.getConversationByUser(user, conver));
        }

        Map<String, Object> objects = new HashMap<>();
        objects.put("user", user);
        objects.put("friends", friends);
        objects.put("conversations", conversations);
        objects.put("letters", letters);

        try {
            Render.render(response, objects, "/my_conversation.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


}
