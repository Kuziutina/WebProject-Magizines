package Servlets;

import Helper.Render;
import Models.Letter;
import Models.User;
import DAO.DAOImpl.LetterDAO;
import DAO.DAOImpl.UserDAO;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");

        UserDAO userDAO = new UserDAO();
        int another_id = Integer.parseInt(request.getParameter("an_id"));

        User another_user = userDAO.find(another_id);
        List<User> friends = userDAO.getFriends(user);
        LetterDAO letterDAO = new LetterDAO();
        List<User> conversations = letterDAO.getAllUserConversation(user);

//        friends.removeAll(conversations);
        List<User> low = new ArrayList<>();
        low.addAll(friends);
        low.retainAll(conversations);
        friends.addAll(conversations);
        friends.removeAll(low);
        friends.addAll(low);
        friends.remove(another_user);


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

        UserDAO userDAO = new UserDAO();
        List<List<Letter>> letters = new ArrayList<>();

        List<User> friends = userDAO.getFriends(user);
        LetterDAO letterDAO = new LetterDAO();
        List<User> conversations = letterDAO.getAllUserConversation(user);
//        for (int i = 0; i < friends.size(); i++) {
//            for (User con: conversations) {
//                if (i < friends.size() && con.getId() == friends.get(i).getId()) {
//                    friends.remove(i);
//                }
//            }
//        }

        List<User> low = new ArrayList<>();
        low.addAll(friends);
        low.retainAll(conversations);
        friends.addAll(conversations);
        friends.removeAll(low);
        friends.addAll(low);

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
