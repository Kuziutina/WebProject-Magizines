package Objects;

import java.util.Date;
import java.util.Date;

public class MagazineCopyReview {
    private int id;
    private String review;
    private Date date;
    private int score;
    private int user_id;
    private int magazine_copy_id;

    private MagazineCopy magazineCopy;
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

    public int getMagazine_copy_id() {
        return magazine_copy_id;
    }

    public void setMagazine_copy_id(int magazine_copy_id) {
        this.magazine_copy_id = magazine_copy_id;
    }
}
