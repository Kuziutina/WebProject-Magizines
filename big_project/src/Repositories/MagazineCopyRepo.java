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
                        resultSet.getTimestamp("date"), resultSet.getInt("magazine_id"), resultSet.getInt("number"));///
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
                return new MagazineCopy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"),
                        resultSet.getTimestamp("date"), resultSet.getInt("magazine_id"), resultSet.getInt("number"));///
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MagazineCopy> getMagazineCopyByMagazineId(int id) {
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
                        resultSet.getTimestamp("date"), id, resultSet.getInt("number")));///
            }
            return magazineCopies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MagazineCopy> getMagazineCopyByMagazine(Magazine magazine) {
        return getMagazineCopyByMagazineId(magazine.getId());
    }

    public MagazineCopy getConcreteMagazineCopy (Magazine magazine, int number) {
        PreparedStatement statement = null;

        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines_copies where magazine_id = ? and number = ?");
            statement.setInt(1, magazine.getId());
            statement.setInt(2, number);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new MagazineCopy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("description"),
                        resultSet.getString("picture_path"), resultSet.getString("path"),
                        resultSet.getTimestamp("date"), magazine.getId(), number);///
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
