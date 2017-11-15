package Objects;

import Repositories.UserRepo;

import java.util.Date;
import java.util.Date;

public class MagazineReview {
    private int id;
    private String review;
    private Date date;
    private int score;
    private int user_id;
    private int magazine_id;

    private Magazine magazine;
    private User user;

    public MagazineReview() {
    }

    public MagazineReview(int id, String review, Date date, int score, int user_id, int magazine_id) {
        this.id = id;
        this.review = review;
        this.date = date;
        this.score = score;
        this.user_id = user_id;
        this.magazine_id = magazine_id;
    }

    public MagazineReview(String review, Date date, int score, int user_id, int magazine_id) {
        this.review = review;
        this.date = date;
        this.score = score;
        this.user_id = user_id;
        this.magazine_id = magazine_id;
    }

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

    public int getMagazine_id() {
        return magazine_id;
    }

    public void setMagazine_id(int magazine_id) {
        this.magazine_id = magazine_id;
    }

    public User getUser() {
        if (user == null) {
            UserRepo userRepo = new UserRepo();
            user = userRepo.getEasyUserById(user_id);
        }
        return user;
    }
}
