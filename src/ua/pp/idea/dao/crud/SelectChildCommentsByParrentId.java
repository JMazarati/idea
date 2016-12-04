package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.idea.entity.Comment;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

/**
 * Created by Dark on 29.11.2016.
 */
public class SelectChildCommentsByParrentId extends MappingSqlQuery<Comment> {
   private static final String SQL_SELECT_CHILD_COMMENTS_BY_PARRENT_ID="SELECT c.id,u.username,c.idea_link,c.parent,c.note,c.date_comments,c.depth FROM comments_table c\n" +
           "JOIN comments_service_table ct ON (c.id = ct.item_link),user_table u\n" +
           "WHERE ct.parent_link=:parent_id AND u.id=c.user_creator";
    public SelectChildCommentsByParrentId(DataSource ds) {
        super(ds, SQL_SELECT_CHILD_COMMENTS_BY_PARRENT_ID);
        super.declareParameter(new SqlParameter("parent_id", Types.INTEGER));
    }

    @Override
    protected Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getInt("id"));
        comment.setUserLink(resultSet.getString("username"));
        comment.setIdeaLink(resultSet.getInt("idea_link"));
        comment.setParentLink(resultSet.getInt("parent"));
        comment.setNote(resultSet.getString("note"));
        comment.setDateComment(resultSet.getTimestamp("date_comments"));
        comment.setDepth(resultSet.getInt("depth"));

        return comment;
    }
}
