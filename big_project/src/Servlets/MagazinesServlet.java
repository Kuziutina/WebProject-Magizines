package Servlets;

import Helper.Render;
import Models.Magazine;
import DAO.DAOImpl.MagazineDAO;
import Services.Interfaces.MagazineServiceInterface;
import Services.MagazineService;
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

@WebServlet(name = "MagazinesServlet", urlPatterns = "/magazines")
public class MagazinesServlet extends HttpServlet {

    private MagazineServiceInterface magazineService;

    @Override
    public void init() throws ServletException {
        magazineService = new MagazineService(new MagazineDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String spage = request.getParameter("page");
        String scount = request.getParameter("showby");

        Map<String, Object> objects = computationPage(magazineService.getAllMagazines(), spage, scount);
        objects.put("user", request.getSession().getAttribute("current_user"));


        try {
            Render.render(response, objects, "/magazines.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> computationPage(List<Magazine> magazines, String spage, String scount) {

        int page, count;
        if (spage == null) page = 1;
        else page = Integer.parseInt(spage);

        if (scount == null) count = 20;
        else if (scount.equals("all")) count = Integer.MAX_VALUE;
        else count = Integer.parseInt(scount);


        int max_count;

        Map<String, Object> objects = new HashMap<>();


        max_count = magazines.size() / count + 1;

        if (page > max_count) page = max_count;
        List<Magazine> result = new ArrayList<>();
        if (page * count >= magazines.size()) {
            result = magazines.subList((page - 1) * count, magazines.size());
        } else {
            result = magazines.subList((page - 1) * count, page * count);
        }

        objects.put("page", page);
        objects.put("count", count);
        objects.put("max_count", max_count);
        objects.put("magazines", result);

        return objects;
    }

}
