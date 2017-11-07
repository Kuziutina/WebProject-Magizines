package Objects;

import java.util.List;

public class Magazine {
    private int id;
    private String name;
    private String description;
    private String picture_path;
    private String path;

    private List<User> subscribers;
    private List<MagazineCopy> copies;
    private List<MagazineReview> reviews;

    public Magazine() {
    }

    public Magazine(int id, String name, String description, String picture_path, String path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture_path = picture_path;
        this.path = path;
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
}
