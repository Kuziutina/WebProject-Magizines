package Repositories;

import Objects.Magazine;
import Objects.MagazineCopy;

import java.sql.*;
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
            statement = conn.prepareStatement("select * from magazines where name=?");
            statement.setInt(1, id);

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





}
