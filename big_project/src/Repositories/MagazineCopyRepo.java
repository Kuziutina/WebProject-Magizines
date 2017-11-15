package Repositories;

import Objects.Magazine;
import Objects.MagazineCopy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MagazineCopyRepo {
    private Connection conn;

    public MagazineCopy getMagazineCopyByName(String name) {
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies where name=?");
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new MagazineCopy(resultSet.getInt("id"), name,resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"),
                        resultSet.getTimestamp("date"), resultSet.getInt("magazine_id"));///
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MagazineCopy getMagazineCopyById(int id) {
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies where id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new MagazineCopy(id, resultSet.getString("name"),resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"),
                        resultSet.getTimestamp("date"), resultSet.getInt("magazine_id"));///
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getMagazineCopyId(MagazineCopy magazineCopy) {
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies where name=? and picture_path=? and path=?");
            statement.setString(1, magazineCopy.getName());
            statement.setString(2, magazineCopy.getPicture_path());
            statement.setString(3, magazineCopy.getPath());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public List<MagazineCopy> getMagazineCopiesByMagazineId(int id) {
        PreparedStatement statement = null;
        List<MagazineCopy> magazineCopies = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies where magazine_id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                 magazineCopies.add(new MagazineCopy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"),
                        resultSet.getTimestamp("date"), id));///
            }
            return magazineCopies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MagazineCopy> getMagazineCopiesByMagazine(Magazine magazine) {
        return getMagazineCopiesByMagazineId(magazine.getId());
    }

//    public MagazineCopy getConcreteMagazineCopy (MagazineCopy magazineCopy) {
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
//                return new MagazineCopy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
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
