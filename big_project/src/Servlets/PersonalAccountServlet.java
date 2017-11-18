package Servlets;

import Helper.Render;
import Objects.Letter;
import Objects.Magazine;
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

@WebServlet(name = "PersonalAccountServlet")
public class PersonalAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path[] = request.getPathInfo().split("/");
        String id = path[1];

        UserRepo userRepo = new UserRepo();
        User another_user = userRepo.getEasyUserById(Integer.parseInt(id));
        User user = (User) request.getSession().getAttribute("current_user");

        if (path.length == 2) {
            if (another_user == null) {
                response.sendRedirect("/error");
            } else {
                Map<String, Object> objects = new HashMap<>();
                objects.put("has", user != null && userRepo.hasFriend(user, another_user.getId()));
                objects.put("another_user", another_user);
                objects.put("user", user);

                try {
                    Render.render(response, objects, "/personal_account.ftl");
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (path.length == 3 && path[2].equals("subscriptions")) {
            String spage = request.getParameter("p");
            String scount = request.getParameter("showby");
            int page, count;
            if (spage == null) page = 1;
            else page = Integer.parseInt(spage);

            if (scount == null) count = 20;
            else if (scount.equals("all")) count = Integer.MAX_VALUE;
            else count = Integer.parseInt(scount);


            int max_count;
            if (another_user == null) {
                response.sendRedirect("/error");
            } else {
                Map<String, Object> objects = new HashMap<>();
                List<Magazine> sub = another_user.getSubscriptions();
                max_count = sub.size()/count + 1;

                if (page > max_count) page = max_count;


                List<Magazine> result = new ArrayList<>();
                for (int i = (page - 1)*count; i <= page*count && i < sub.size(); i++) {
                    result.add(sub.get(i));
                }


                objects.put("another_user", another_user);
                objects.put("user", request.getSession().getAttribute("current_user"));
                objects.put("page", page);
                objects.put("count", count);
                objects.put("max_count", max_count);
                objects.put("subscriptions", result);

                try {
                    Render.render(response, objects, "/subscription.ftl");
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (path.length == 3 && path[2].equals("conversation")) {
            List<User> friends = userRepo.getFriends(user);
            LetterRepo letterRepo = new LetterRepo();
            List<User> conversations = letterRepo.getAllConversation(user);
            List<Letter> letters = null;
            if (conversations.size() != 0) {
                letters = letterRepo.getConversation(user, conversations.get(0));
            }

            Map<String, Object> objects = new HashMap<>();
            objects.put("user", user);
            objects.put("friends", friends);
            objects.put("convarsations", conversations);
            objects.put("letters", letters);

            try {
                Render.render(response, objects, "my_conversation.ftl");
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }

    }
}
