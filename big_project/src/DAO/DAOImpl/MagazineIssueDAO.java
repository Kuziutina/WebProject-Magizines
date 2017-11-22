package DAO.DAOImpl;

import DAO.Interfaces.MagazineIssueDAOInterface;
import Helper.DBConnection;
import Models.Magazine;
import Models.MagazineIssue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagazineIssueDAO implements MagazineIssueDAOInterface {
    private Connection conn;

//    public MagazineIssue getMagazineCopyByName(String name) {
//        PreparedStatement statement = null;
//        try {
//            conn = DBConnection.getConnection();
//            statement = conn.prepareStatement("select * from magazines_copies where name=?");
//            statement.setString(1, name);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                return new MagazineIssue(resultSet.getInt("id"), name,resultSet.getString("description"),
//                        resultSet.getString("picture_path"), resultSet.getString("path"),
//                        resultSet.getTimestamp("date"), resultSet.getInt("magazine_id"));///
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public MagazineIssue find(Integer id) {
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies where id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new MagazineIssue(id, resultSet.getString("name"),resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"),
                        resultSet.getTimestamp("date"), resultSet.getInt("magazine_id"));///
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getMagazineIssueId(MagazineIssue magazineIssue) {
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies where name=? and picture_path=? and path=?");
            statement.setString(1, magazineIssue.getName());
            statement.setString(2, magazineIssue.getPicture_path());
            statement.setString(3, magazineIssue.getPath());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private List<MagazineIssue> getByMagazineId(int id) {
        PreparedStatement statement = null;
        List<MagazineIssue> magazineCopies = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies where magazine_id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                 magazineCopies.add(new MagazineIssue(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"),
                        resultSet.getTimestamp("date"), id));///
            }
            return magazineCopies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MagazineIssue> getByMagazine(Magazine magazine) {
        return getByMagazineId(magazine.getId());
    }

    @Override
    public boolean add(MagazineIssue magazineIssue) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("insert into magazines_copies (name, description, picture_path, path, date, magazine_id)" +
                    "values(?,?,?,?,?,?)");
            statement.setString(1, magazineIssue.getName());
            statement.setString(2, magazineIssue.getDescription());
            statement.setString(3, magazineIssue.getPicture_path());
            statement.setString(4, magazineIssue.getPath());
            statement.setTimestamp(5, new Timestamp(magazineIssue.getDate().getTime()));
            statement.setInt(6, magazineIssue.getMagazine_id());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void delete(MagazineIssue model) {

    }

    @Override
    public void update(MagazineIssue model) {

    }

    public List<MagazineIssue> findAll(){
        PreparedStatement statement = null;
        List<MagazineIssue> magazineCopies;
        try {
            magazineCopies = new ArrayList<>();
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies order by date");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                magazineCopies.add(new MagazineIssue(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"),
                        resultSet.getTimestamp("date"), resultSet.getInt("magazine_id")));///
            }
            return magazineCopies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getScore(MagazineIssue magazineIssue) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select AVG(score) from magazines_copies_reviews where magazine_copy_id = ?");
            statement.setInt(1, magazineIssue.getId());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return resultSet.getDouble("avg");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

//    public MagazineIssue getConcreteMagazineCopy (MagazineIssue magazineCopy) {
//        PreparedStatement statement = null;
//
//        try {
//            conn = DBConnection.getConnection();
//            statement = conn.prepareStatement("select * from magazines_copies where magazine_id = ? and number = ?");
//            statement.setInt(1, magazine.getId());
//            statement.setInt(2, number);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                return new MagazineIssue(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
//                        resultSet.getString("picture_path"), resultSet.getString("path"),
//                        resultSet.getTimestamp("date"), magazine.getId());///
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }



}
