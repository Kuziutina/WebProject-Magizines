package Repositories;

import Models.Magazine;
import Models.User;

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

    public User getUsersByName(String name) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();
        List<User> users;

        try {
            users = new ArrayList<>();
            statement = conn.prepareStatement("select id, name FROM users where name = ?");
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getFriends(User user) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();
        List<User> users;

        try {
            users = new ArrayList<>();
            statement = conn.prepareStatement("select * FROM friends where user_id = ?");
            statement.setInt(1, user.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(getUserById(resultSet.getInt("friend_id")));
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

    public boolean addFriend(User user, int friend_id) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("insert into friends VALUES (?, ?)");
            statement.setInt(1, user.getId());
            statement.setInt(2, friend_id);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User getUserByCookie(String cookie) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();

        try {
            statement = conn.prepareStatement("select * from users where cookie_login = ?");
            statement.setString(1, cookie);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return User.newBuilder().setId(resultSet.getInt("id")).setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password")).setName(resultSet.getString("name"))
                        .setConfirmation(resultSet.getString("confirmation")).setCookie_login(cookie).build();
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

    public boolean deleteFriend(User user, int friend_id) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("delete from friends where user_id = ? and friend_id = ?");
            statement.setInt(1, user.getId());
            statement.setInt(2, friend_id);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addSubscription(User user, int magazine_id) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("insert into subscriptions VALUES (?, ?)");
            statement.setInt(1, user.getId());
            statement.setInt(2, magazine_id);

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSubscription(User user, int magazine_id) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("delete from subscriptions where user_id = ? and magazine_id = ?");
            statement.setInt(1, user.getId());
            statement.setInt(2, magazine_id);

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean hasSubscription(User user, int magazine_id) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from subscriptions where user_id = ? and magazine_id = ?");
            statement.setInt(1, user.getId());
            statement.setInt(2, magazine_id);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean hasFriend(User user, int friend_id) {
        PreparedStatement statement;
        try {
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from friends where user_id = ? and friend_id = ?");
            statement.setInt(1, user.getId());
            statement.setInt(2, friend_id);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public List<Magazine> getSubscriptions(User user) {
        PreparedStatement statement;
        List<Magazine> magazines;
        try {
            magazines = new ArrayList<>();
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("select * from magazines join subscriptions on id = magazine_id where user_id = ?");
            statement.setInt(1, user.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                magazines.add(new Magazine().newBuilder().setName(resultSet.getString("name")).setDescription(resultSet.getString("description"))
                                            .setId(resultSet.getInt("id")).setPicture_path(resultSet.getString("picture_path")).build());
            }
            return magazines;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public List<User> getUserByPartName(String name) {
        PreparedStatement statement;
        conn = DBConnection.getConnection();
        List<User> users;

        try {
            users = new ArrayList<>();
            statement = conn.prepareStatement("select * FROM users where name like ?");
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(new User().newBuilder().setLogin(resultSet.getString("login")).setName(resultSet.getString("name"))
                                    .setId(resultSet.getInt("id")).build());
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
