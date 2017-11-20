package Objects;

import Repositories.MagazineCopyRepo;
import Repositories.MagazineCopyReviewRepo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MagazineCopy {
    private int id;
    private String name;
    private String description;
    private String picture_path;
    private String path;
    private Date date;
    private int magazine_id;
    private int number;
    private double score;
    private String format_date;
    private int int_score;

    private List<MagazineCopyReview> reviews;
    private Magazine magazine;

    public MagazineCopy() {
    }

    public MagazineCopy(int id, String name, String description, String picture_path, String path, Date date, int magazine_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture_path = picture_path;
        this.path = path;
        this.date = date;
        this.magazine_id = magazine_id;

    }

    public MagazineCopy(String name, String description, String picture_path, String path, Date date, int magazine_id) {
        this.name = name;
        this.description = description;
        this.picture_path = picture_path;
        this.path = path;
        this.date = date;
        this.magazine_id = magazine_id;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMagazine_id() {
        return magazine_id;
    }

    public void setMagazine_id(int magazine_id) {
        this.magazine_id = magazine_id;
    }

    public List<MagazineCopyReview> getReviews() {
        if (reviews == null) {
            MagazineCopyReviewRepo magazineCopyReviewRepo = new MagazineCopyReviewRepo();
            reviews = magazineCopyReviewRepo.getMagazineCopyReview(this);
        }
        return reviews;
    }

    public void setReviews(List<MagazineCopyReview> reviews) {
        this.reviews = reviews;
    }

    public String getFormat_date() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new SimpleDateFormat("dd", Locale.US).format(date));
        String m = new SimpleDateFormat("MM", Locale.US).format(date);
        switch (m) {
            case "12": stringBuilder.append(" декабря, ");break;
            case "11": stringBuilder.append(" ноября, ");break;
            case "10": stringBuilder.append(" октября, ");break;
            case "9": stringBuilder.append(" сентября, ");break;
            case "8": stringBuilder.append(" августа, ");break;
            case "7": stringBuilder.append(" июля, ");break;
            case "6": stringBuilder.append(" июня, ");break;
            case "5": stringBuilder.append(" мая, ");break;
            case "4": stringBuilder.append(" апреля, ");break;
            case "3": stringBuilder.append(" марта, ");break;
            case "2": stringBuilder.append(" февраля, ");break;
            case "1": stringBuilder.append(" января, ");break;
        }
        stringBuilder.append(new SimpleDateFormat("yyyy", Locale.US).format(date));
        return stringBuilder.toString();
    }

    public void setFormat_date(String format_date) {
        this.format_date = format_date;
    }

    public double getScore() {
        MagazineCopyRepo magazineCopyRepo = new MagazineCopyRepo();
        return magazineCopyRepo.getMagazineCopyScore(this);
    }

    public int getInt_score() {
        return (int) Math.round(getScore());
    }

    public void setInt_score(int int_score) {
        this.int_score = int_score;
    }
}
