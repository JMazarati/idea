package ua.pp.idea.entity;

/**
 * Created by Dark on 10.12.2016.
 */
public class Rating {
    private int id;
    private String user_creator;
    private int idea_link;
    private int likeordislike;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_creator() {
        return user_creator;
    }

    public void setUser_creator(String user_creator) {
        this.user_creator = user_creator;
    }

    public int getIdea_link() {
        return idea_link;
    }

    public void setIdea_link(int idea_link) {
        this.idea_link = idea_link;
    }

    public int getLikeordislike() {
        return likeordislike;
    }

    public void setLikeordislike(int likeordislike) {
        this.likeordislike = likeordislike;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
