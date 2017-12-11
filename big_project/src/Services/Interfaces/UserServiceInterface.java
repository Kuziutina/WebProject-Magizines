package Services.Interfaces;

import Models.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserServiceInterface {
    void changeConfirmation(String confirmation);
    void changeConfirmation(User user, String confirmation);
    User getByConfirmation(String confirmation);
    List<User> getUserWithConversation(User user, List<User> conversation);
    String restoringPassword(User user);
}
