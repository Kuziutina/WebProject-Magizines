package Models;

import java.util.Date;

public class MagazineIssueReview extends Review {


    private int magazine_copy_id;


    public MagazineIssueReview() {
    }

    public MagazineIssueReview(int id, String review, Date date, int score, int user_id, int magazine_id) {
        this.id = id;
        this.review = review;
        this.date = date;
        this.score = score;
        this.user_id = user_id;
        this.magazine_copy_id = magazine_id;
    }

    public MagazineIssueReview(String review, Date date, int score, int user_id, int magazine_id) {
        this.review = review;
        this.date = date;
        this.score = score;
        this.user_id = user_id;
        this.magazine_copy_id = magazine_id;
    }

    public int getMagazine_copy_id() {
        return magazine_copy_id;
    }

    public void setMagazine_copy_id(int magazine_copy_id) {
        this.magazine_copy_id = magazine_copy_id;
    }
}
