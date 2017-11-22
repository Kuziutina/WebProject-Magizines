package AjaxServlet;

import Models.MagazineIssueReview;
import Models.MagazineReview;
import Models.User;
import DAO.DAOImpl.MagazineIssueReviewDAO;
import DAO.DAOImpl.MagazineReviewDAO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "AjaxMagazineReviewServlet")
public class AjaxMagazineReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int magazine_id = Integer.parseInt(request.getParameter("magazine_id"));
        String review = request.getParameter("review");
        User user = (User) request.getSession().getAttribute("current_user");
        int score = Integer.parseInt(request.getParameter("score"));
        String copy = request.getParameter("copy");

        if (copy == null) {
            MagazineReview magazineReview = new MagazineReview(review, new Date(System.currentTimeMillis()), score, user.getId(), magazine_id);
            MagazineReviewDAO magazineReviewDAO = new MagazineReviewDAO();
            magazineReviewDAO.add(magazineReview);
        }
        else {
            MagazineIssueReview magazineIssueReview = new MagazineIssueReview(review, new Date(System.currentTimeMillis()), score, user.getId(), magazine_id);
            MagazineIssueReviewDAO magazineIssueReviewDAO = new MagazineIssueReviewDAO();
            magazineIssueReviewDAO.add(magazineIssueReview);
        }

        JSONObject jsonObject = new JSONObject();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jsonObject.toString());
        response.getWriter().close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
