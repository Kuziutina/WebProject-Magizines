package Objects;

import Repositories.UserRepo;

import java.util.Date;

public class Review {
    protected int id;
    protected String review;
    protected Date date;
    protected int score;
    protected int user_id;


    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        if (user == null) {
            UserRepo userRepo = new UserRepo();
            user = userRepo.getEasyUserById(user_id);
        }
        return user;
    }
}
