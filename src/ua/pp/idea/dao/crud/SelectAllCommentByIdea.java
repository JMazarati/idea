package ua.pp.idea.dao.crud;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.idea.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dark on 21.11.2016.
 */
public class SelectAllCommentByIdea extends MappingSqlQuery<Comment> {
    @Override
    protected Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
