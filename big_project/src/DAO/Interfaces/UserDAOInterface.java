package DAO.Interfaces;

import Models.User;

import java.util.List;

public interface UserDAOInterface extends DAOInterface<User, Integer> {
    User getEasyUserById(int id);
    User getByConfirmation(String confirmation);
    User getByLogin(String login);
    User getByName(String name);
    User getByCookie(String cookie);
    List<User> getFriends(User user);
    List<User> getByNamePattern(String name);
    boolean addFriend(User user, int friend_id);
    boolean deleteFriend(User user, int friend_id);
    boolean hasFriend(User user, int friend_id);
    boolean updateCookie(User user, String cookie);
    boolean updatePassword(User user, String password);
    boolean updateConfirmation(User user, String confirmation);
    boolean updateUsername(User user, String username);
    boolean updateLogin(User user, String login);
}
