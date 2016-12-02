package ua.pp.idea.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Created by Dark on 21.11.2016.
 */
public class Comment {
    private int id;
    private String userLink;
    private int ideaLink;
    private int parentLink;
    private String note;
    private Timestamp dateComment;
    private int depth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    public int getIdeaLink() {
        return ideaLink;
    }

    public void setIdeaLink(int ideaLink) {
        this.ideaLink = ideaLink;
    }

    public int getParentLink() {
        return parentLink;
    }

    public void setParentLink(int parentLink) {
        this.parentLink = parentLink;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getDateComment() {
        return dateComment;
    }

    public void setDateComment(Timestamp dateComment) {
        this.dateComment = dateComment;
    }

    public int getDepth() {return depth;}

    public void setDepth(int depth) {this.depth = depth;}

    public static final Comparator<Comment> sortByParrent = new Comparator<Comment>(){
        @Override
        public int compare(Comment c1, Comment c2){return c1.getParentLink()-c2.getParentLink();}
    };
}
