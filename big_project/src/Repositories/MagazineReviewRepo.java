package Repositories;

import Models.Magazine;
import Models.MagazineReview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagazineReviewRepo {

    Connection conn;

    public List<MagazineReview> getMagazineReview(Magazine magazine) {
        PreparedStatement statement;
        List<MagazineReview> magazineReviews;
        try {
            magazineReviews = new ArrayList<>();
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_reviews WHERE magazine_id = ?");
            statement.setInt(1, magazine.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                magazineReviews.add(new MagazineReview(resultSet.getInt("id"), resultSet.getString("review"),
                                    resultSet.getTimestamp("date"), resultSet.getInt("score"),
                                    resultSet.getInt("user_id"), magazine.getId()));
            }

            return magazineReviews;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addMagazineReview(MagazineReview magazineReview) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("insert into magazines_reviews(review, date, score, user_id, magazine_id) VALUES (?,?,?,?,?)");
            statement.setString(1, magazineReview.getReview());
            statement.setTimestamp(2, new Timestamp(magazineReview.getDate().getTime()));
            statement.setInt(3, magazineReview.getScore());
            statement.setInt(4, magazineReview.getUser_id());
            statement.setInt(5, magazineReview.getMagazine_id());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
