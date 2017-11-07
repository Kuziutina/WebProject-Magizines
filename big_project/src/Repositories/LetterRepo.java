package Repositories;

import Objects.Letter;
import Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LetterRepo {

    private Connection conn;

    public List<Letter> getLetter(User sender, User recepient) {
        PreparedStatement statement = null;
        List<Letter> letters;
        try {
            letters = new ArrayList<>();
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from letters order by date asc where sender_id = ? and recepient_id = ?");
            statement.setInt(1, sender.getId());
            statement.setInt(2, recepient.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                letters.add(new Letter(resultSet.getInt("id"), resultSet.getString("header"),
                        resultSet.getString("body"), resultSet.getTimestamp("date"),
                        sender.getId(), recepient.getId()));
            }
            return letters;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllConversation(User sender) {
//        PreparedStatement statement = null;
//        List<Letter> letters;
//        try {
//            letters = new ArrayList<>();
//            conn = DBConnection.getConnection();
//            statement = conn.prepareStatement("select * from letters order by date asc where sender_id = ? and recepient_id = ?");
//            statement.setInt(1, sender.getId());
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                letters.add(new Letter(resultSet.getInt("id"), resultSet.getString("header"),
//                        resultSet.getString("body"), resultSet.getTimestamp("date"),
//                        sender.getId(), recepient.getId()));
//            }
//            return letters;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }


}
