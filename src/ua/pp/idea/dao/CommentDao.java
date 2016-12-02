package ua.pp.idea.dao;

import ua.pp.idea.entity.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark on 21.11.2016.
 */
public interface CommentDao {
    public List<Comment> getAllCommentsByIdeaLink(int idea_id);

    ArrayList<Comment> getChildCommentsByParrentId(int idea_id);

    void createNewComments(Comment comment);

    int getCnt();
}
