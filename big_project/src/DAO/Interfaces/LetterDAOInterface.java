package DAO.Interfaces;

import Models.Letter;
import Models.User;

import java.util.List;

public interface LetterDAOInterface extends DAOInterface<Letter, Long> {
    List<Letter> getLetterBuUsers(User sender, User recipient);
    List<User> getAllUserConversation (User sender);
    List<Letter> getConversationByUser(User sender, User recipient);
    boolean hasConversationWithUser(User sender, int recipient);
}
