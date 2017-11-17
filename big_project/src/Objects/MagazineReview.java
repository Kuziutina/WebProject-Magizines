package Objects;

import Repositories.UserRepo;

import java.util.Date;
import java.util.Date;

public class MagazineReview extends Review{

    private int magazine_id;

    private Magazine magazine;


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



    public int getMagazine_id() {
        return magazine_id;
    }

    public void setMagazine_id(int magazine_id) {
        this.magazine_id = magazine_id;
    }


}
