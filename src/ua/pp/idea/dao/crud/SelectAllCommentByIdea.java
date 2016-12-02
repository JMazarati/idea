package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.idea.entity.Comment;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 21.11.2016.
 */
public class SelectAllCommentByIdea extends MappingSqlQuery<Comment> {
    //private static final String SQL_SELECT_COMMENT_BY_IDEA="SELECT id,idea_link,parent FROM comments_table WHERE idea_link=:idea_id AND id=parent";
    private static final String SQL_SELECT_COMMENT_BY_IDEA_FOR_CHILD="SELECT c.id,u.username,c.idea_link,c.parent,c.note,c.date_comments,c.depth " +
            "FROM comments_table c \n" +
            "JOIN user_table u ON (u.id=c.user_creator) \n" +
            "WHERE c.idea_link=:idea_id AND c.parent=coalesce(:parent, c.id) AND \n" +
            "(CASE WHEN :parent IS NOT NULL THEN c.parent<>c.id ELSE TRUE END) ORDER BY c.date_comments ";

    public SelectAllCommentByIdea(DataSource ds) {
        super(ds,SQL_SELECT_COMMENT_BY_IDEA_FOR_CHILD);
        super.declareParameter(new SqlParameter("idea_id", Types.INTEGER));
        super.declareParameter(new SqlParameter("parent", Types.INTEGER));

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
