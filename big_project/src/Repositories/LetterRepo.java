package Repositories;

import Objects.Letter;
import Objects.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        PreparedStatement statement = null;
        List<User> users;
        Set<Integer> ids;
        conn = DBConnection.getConnection();

        try {
            UserRepo userRepo = new UserRepo();
            users = new ArrayList<>();
            ids = new HashSet<>();
            int sender_id, recepient_id;
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select DISTINCT  recepient_id, sender_id from letters WHERE sender_id = ? or recepient_id = ?");
            statement.setInt(1, sender.getId());
            statement.setInt(2, sender.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sender_id = resultSet.getInt("sender_id");
                recepient_id = resultSet.getInt("recepient_id");
                if (sender_id != sender.getId()) {
                    ids.add(sender_id);
                }
                if (recepient_id != sender.getId()) {
                    ids.add(recepient_id);
                }
            }

            for (int id : ids) {
                users.add(userRepo.getEasyUserById(id));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Letter> getConversation(User sender, User recepinet) {
        PreparedStatement statement;
        List<Letter> letters;

        try {
            conn = DBConnection.getConnection();
            letters = new ArrayList<>();

            statement = conn.prepareStatement("select id, header, body, date from letters order by \"date\" ASC where " +
                    "(sender_id = ? and recepient_id = ?) or (user_id = ? and recepient = ?)");
            statement.setInt(1, sender.getId());
            statement.setInt(2, recepinet.getId());
            statement.setInt(3, recepinet.getId());
            statement.setInt(4, sender.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                letters.add(new Letter(resultSet.getInt("id"), resultSet.getString("header"),
                        resultSet.getString("body"), resultSet.getTimestamp("date"), resultSet.getInt("sender_id"), resultSet.getInt("recepient_id")));
            }

            return letters;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addLetter(Letter letter) {
        PreparedStatement statement;

        try {
            conn = DBConnection.getConnection();

            statement = conn.prepareStatement("insert into letters(header, body, date, sender_id, recepient_id) values (?, ?, ?, ?, ?)");
            statement.setString(1, letter.getHeader());
            statement.setString(2, letter.getBody());
            statement.setTimestamp(3, (Timestamp) letter.getDate());/////////////////////////voprooosishe

            statement.setInt(4, letter.getSender_id());
            statement.setInt(5, letter.getRecepient_id());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
