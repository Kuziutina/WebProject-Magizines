package DAO.DAOImpl;

import DAO.Interfaces.LetterDAOInterface;
import Helper.DBConnection;
import Models.Letter;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LetterDAO implements LetterDAOInterface{

    private Connection conn;

    @Override
    public List<Letter> getLetterBuUsers(User sender, User recipient) {
        PreparedStatement statement = null;
        List<Letter> letters;

        try {
            letters = new ArrayList<>();
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from letters order by date asc where sender_id = ? and recepient_id = ?");
            statement.setInt(1, sender.getId());
            statement.setInt(2, recipient.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                letters.add(new Letter(resultSet.getInt("id"), resultSet.getString("header"),
                        resultSet.getString("body"), resultSet.getTimestamp("date"),
                        sender.getId(), recipient.getId()));
            }
            return letters;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUserConversation(User sender) {
        PreparedStatement statement = null;
        List<User> users;
        Set<Integer> ids;
        conn = DBConnection.getConnection();

        try {
            UserDAO userDAO = new UserDAO();
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
                users.add(userDAO.getEasyUserById(id));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }////TO DO

    @Override
    public List<Letter> getConversationByUser(User sender, User recipient) {
        PreparedStatement statement;
        List<Letter> letters;

        try {
            conn = DBConnection.getConnection();
            letters = new ArrayList<>();

            statement = conn.prepareStatement("select * from letters where " +
                    "(sender_id = ? and recepient_id = ?) or (sender_id = ? and recepient_id = ?) order by date ASC");
            statement.setInt(1, sender.getId());
            statement.setInt(2, recipient.getId());
            statement.setInt(3, recipient.getId());
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

    @Override
    public boolean hasConversationWithUser(User sender, int recipient_id) {
        PreparedStatement statement;

        try {
            conn = DBConnection.getConnection();

            statement = conn.prepareStatement("select id, header, body, date from letters  where " +
                    "(sender_id = ? and recepient_id = ?) or (sender_id = ? and recepient_id = ?) order by date ASC");
            statement.setInt(1, sender.getId());
            statement.setInt(2, recipient_id);
            statement.setInt(3, recipient_id);
            statement.setInt(4, sender.getId());

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean add(Letter letter) {
        PreparedStatement statement;

        try {
            conn = DBConnection.getConnection();

            statement = conn.prepareStatement("insert into letters(header, body, date, sender_id, recepient_id) values (?, ?, ?, ?, ?)");
            statement.setString(1, letter.getHeader());
            statement.setString(2, letter.getBody());
            statement.setTimestamp(3, new Timestamp(letter.getDate().getTime()));/////////////////////////voprooosishe

            statement.setInt(4, letter.getSender_id());
            statement.setInt(5, letter.getRecepient_id());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Letter find(Long id) {
        return null;
    }

    @Override
    public void delete(Letter model) {

    }

    @Override
    public void update(Letter model) {

    }

    @Override
    public List<Letter> findAll() {
        return null;
    }
}
