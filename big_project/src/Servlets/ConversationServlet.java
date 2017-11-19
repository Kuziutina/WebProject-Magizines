package Servlets;

import Helper.Render;
import Objects.Letter;
import Objects.User;
import Repositories.LetterRepo;
import Repositories.UserRepo;
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
        UserRepo userRepo = new UserRepo();
        int another_id = Integer.parseInt(request.getParameter("an_id"));

        User another_user = userRepo.getUserById(another_id);
        List<User> friends = userRepo.getFriends(user);
        LetterRepo letterRepo = new LetterRepo();
        List<User> conversations = letterRepo.getAllConversation(user);

        friends.removeAll(conversations);
        friends.remove(another_user);


        List<List<Letter>> letters = new ArrayList<>();
        for (User conver: conversations) {
            letters.add(letterRepo.getConversation(user, conver));
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
        UserRepo userRepo = new UserRepo();
        List<List<Letter>> letters = new ArrayList<>();

        List<User> friends = userRepo.getFriends(user);
        LetterRepo letterRepo = new LetterRepo();
        List<User> conversations = letterRepo.getAllConversation(user);
//        for (int i = 0; i < friends.size(); i++) {
//            for (User con: conversations) {
//                if (i < friends.size() && con.getId() == friends.get(i).getId()) {
//                    friends.remove(i);
//                }
//            }
//        }
        friends.removeAll(conversations);

        for (User conver: conversations) {
            letters.add(letterRepo.getConversation(user, conver));
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