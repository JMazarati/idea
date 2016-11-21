package ua.pp.idea.entity;

import java.sql.Date;

/**
 * Created by Dark on 21.11.2016.
 */
public class Comment {
    private Long id;
    private int userLink;
    private int ideaLink;
    private int parentLink;
    private String note;
    private Date dateComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserLink() {
        return userLink;
    }

    public void setUserLink(int userLink) {
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

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }
}
