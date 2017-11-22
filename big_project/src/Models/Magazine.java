package Models;

import DAO.DAOImpl.MagazineIssueDAO;
import DAO.DAOImpl.MagazineDAO;
import DAO.DAOImpl.MagazineReviewDAO;

import java.util.List;

public class Magazine {
    private int id;
    private String name;
    private String description;
    private String picture_path;
    private String path;
    private double score;
    private int int_score;

    private List<User> subscribers;
    private List<MagazineIssue> copies;
    private List<MagazineReview> reviews;

    public Magazine() {
    }

    public static Builder newBuilder() {
        return new Magazine().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setId(int id) {
            Magazine.this.id = id;

            return this;
        }

        public Builder setName(String name) {
            Magazine.this.name = name;

            return this;
        }

        public Builder setDescription(String description) {
            Magazine.this.description = description;

            return this;
        }

        public Builder setPicture_path(String picturePath) {
            Magazine.this.picture_path = picturePath;

            return this;
        }

        public Builder setScore(double score) {
            Magazine.this.score = score;

            return this;
        }

        public Magazine build() {
            return Magazine.this;
        }
    }

    public Magazine(int id, String name, String description, String picture_path, String path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture_path = picture_path;
        this.path = path;
    }

    public List<MagazineIssue> getCopies() {
        if (copies == null) {
            MagazineIssueDAO magazineIssueDAO = new MagazineIssueDAO();
            copies = magazineIssueDAO.getByMagazine(this);
        }
        return copies;
    }

    public double getScore() {

        MagazineDAO magazineDAO = new MagazineDAO();
        return magazineDAO.getScore(this);
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<MagazineReview> getReviews() {
        if (reviews == null) {
            MagazineReviewDAO magazineReviewDAO = new MagazineReviewDAO();
            reviews = magazineReviewDAO.getReview(this);
        }
        return reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getInt_score() {
        return (int) Math.round(getScore());
    }

    public void setInt_score(int int_score) {
        this.int_score = int_score;
    }
}
