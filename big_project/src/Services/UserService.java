package Services;

import DAO.DAOImpl.UserDAO;
import DAO.Interfaces.UserDAOInterface;
import Models.User;
import Services.Interfaces.UserServiceInterface;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface{
    private UserDAOInterface userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void changeConfirmation(String confirmation) {

    }

    @Override
    public void changeConfirmation(User user, String confirmation) {
        userDAO.updateConfirmation(user, confirmation);
        user.setConfirmation(confirmation);
    }

    @Override
    public User getByConfirmation(String confirmation) {
        return userDAO.getByConfirmation(confirmation);
    }

    @Override
    public List<User> getUserWithConversation(User user, List<User> conversations) {
        List<User> friends = userDAO.getFriends(user);

        List<User> low = new ArrayList<>();
        low.addAll(friends);
        low.retainAll(conversations);
        friends.addAll(conversations);
        friends.removeAll(low);
        friends.addAll(low);

        return friends;
    }

    @Override
    public String restoringPassword(User user) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String pwd = RandomStringUtils.random( 8, characters );
        userDAO.updatePassword(user, pwd);

        return pwd;
    }
}
