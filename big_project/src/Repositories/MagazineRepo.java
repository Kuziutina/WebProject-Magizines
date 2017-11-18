package Repositories;

import Objects.Magazine;
import Objects.MagazineCopy;
import Objects.MagazineReview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagazineRepo {
    private Connection conn;

    public Magazine getMagazineByName(String name) {
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines where name=?");
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Magazine(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"));///
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Magazine getMagazineById(int id) {
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines where id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Magazine().newBuilder().setId(resultSet.getInt("id")).setName(resultSet.getString("name")).setDescription(resultSet.getString("description"))
                        .setPicture_path(resultSet.getString("picture_path")).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addMagazine(Magazine magazine) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("insert into magazines(name, description, picture_path) VALUES (?, ?, ?)");
            statement.setString(1, magazine.getName());
            statement.setString(2, magazine.getDescription());
            statement.setString(3, magazine.getPicture_path());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getMagazineId(Magazine magazine) {
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select id from magazines where name=? and description = ? and picture_path = ?");
            statement.setString(1, magazine.getName());
            statement.setString(2, magazine.getDescription());
            statement.setString(3, magazine.getPicture_path());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<MagazineCopy> getMagazineCopies(Magazine magazine) {
        MagazineCopyRepo magazineCopyRepo = new MagazineCopyRepo();
        return magazineCopyRepo.getMagazineCopiesByMagazine(magazine);
    }

    public List<MagazineReview> getMagazineReviews(Magazine magazine) {
        MagazineReviewRepo magazineReviewRepo = new MagazineReviewRepo();
        return magazineReviewRepo.getMagazineReview(magazine);
    }

    public double getMagazineScore(Magazine magazine) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select AVG(score) from magazines_reviews where magazine_id = ?");
            statement.setInt(1, magazine.getId());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return resultSet.getDouble("avg");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Magazine> getNewerMagazine(int n) {
        List<Magazine> magazines;
        PreparedStatement statement;
        try {
            magazines = new ArrayList<>();
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select DISTINCT test.mag_id, test.name, test.description, test.picture_path from (select magazines.id as mag_id, magazines.name, magazines.description, magazines.picture_path, date from magazines join magazines_copies on magazines.id = magazines_copies.magazine_id order by date)  AS test limit(?)");
            statement.setInt(1, n);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                magazines.add(new Magazine().newBuilder().setName(resultSet.getString("name")).setDescription(resultSet.getString("description"))
                                            .setPicture_path(resultSet.getString("picture_path")).setId(resultSet.getInt("mag_id")).build());
            }

            return magazines;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Magazine> getPopularMagazine(int n) {
        List<Magazine> magazines;
        PreparedStatement statement;
        try {
            magazines = new ArrayList<>();
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select id, name, description, picture_path from magazines left join subscriptions on id = magazine_id group by id order by id desc limit(?)");
            statement.setInt(1, n);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                magazines.add(new Magazine().newBuilder().setName(resultSet.getString("name")).setDescription(resultSet.getString("description"))
                        .setPicture_path(resultSet.getString("picture_path")).setId(resultSet.getInt("id")).build());
            }

            return magazines;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Magazine> getAllMagazines() {
        List<Magazine> magazines;
        PreparedStatement statement;
        try {
            magazines = new ArrayList<>();
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines order by name");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                magazines.add(new Magazine().newBuilder().setName(resultSet.getString("name")).setDescription(resultSet.getString("description"))
                        .setPicture_path(resultSet.getString("picture_path")).setId(resultSet.getInt("id")).build());
            }

            return magazines;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }





}
