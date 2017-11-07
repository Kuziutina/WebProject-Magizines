package Objects;

import java.util.Calendar;

public class MagazineReview {
    private int id;
    private String review;
    private Calendar date;
    private int score;
    private int user_id;
    private int magazine_id;

    private Magazine magazine;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
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
}
