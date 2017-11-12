package Repositories;

import Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    private Connection conn;

    public User getUserById(int id) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("select * from users where id = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return new User(id, resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getEasyUserById(int id) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("select name from users where id = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return new User(id, resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUserByConfirmation(String confirmation) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("select * from users where confirmation = ?");
            statement.setString(1, confirmation);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return User.newBuilder().setId(resultSet.getInt("id")).setLogin(resultSet.getString("login")).setCookie_login(resultSet.getString("cookie_login"))
                        .setPassword(resultSet.getString("password")).setName(resultSet.getString("name")).setConfirmation(confirmation).build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUserByLogin(String login) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("select * from users where login = ?");
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return User.newBuilder().setId(resultSet.getInt("id")).setLogin(login)
                        .setPassword(resultSet.getString("password")).setName(resultSet.getString("name"))
                        .setConfirmation(resultSet.getString("confirmation")).setCookie_login(resultSet.getString("cookie_login")).build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getUsersByNamePart(String name_part) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();
        List<User> users;

        try {
            users = new ArrayList<>();
            statement = conn.prepareStatement("select id, name FROM users where name like ?");
            statement.setString(1, "%" + name_part + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addUser(User user) {
        PreparedStatement statement;

        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("insert into users (login, password, name, confirmation) values (?, ?, ?, ?) ");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getConfirmation());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User getUserByCoockie(String cookie) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("select * from users where cookie_login = ?");
            statement.setString(1, cookie);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("login") , resultSet.getString("password"), resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public boolean updateUserCookie(User user, String cookie) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("update users set cookie_login = ? where login = ?");
            statement.setString(1, cookie);
            statement.setString(2, user.getLogin());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateUserPassword(User user, String password) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("update users set password = ? where login = ?");
            statement.setString(1, password);
            statement.setString(2, user.getLogin());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateUserConfirmation(User user, String confirmation) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("update users set confirmation = ? where login = ?");
            statement.setString(1, confirmation);
            statement.setString(2, user.getLogin());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateUserUsername(User user, String username) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("update users set name = ? where login = ?");
            statement.setString(1, username);
            statement.setString(2, user.getLogin());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateUserLogin(User user, String login) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("update users set login = ? where login = ?");
            statement.setString(1, login);
            statement.setString(2, user.getLogin());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

}
