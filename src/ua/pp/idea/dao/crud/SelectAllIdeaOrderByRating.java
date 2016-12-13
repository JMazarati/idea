package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.idea.entity.Idea;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 15.11.2016.
 */
public class SelectAllIdeaOrderByRating extends MappingSqlQuery<Idea> {
    private static final String SQL_SELECT_ALL_IDEA = "SELECT i.id,i.caption,i.txt,u.username,i.rating,i.date_create, i.category_link, i.tags FROM user_table u INNER JOIN idea_table i ON(i.owner=u.id) ORDER BY rating";

    public SelectAllIdeaOrderByRating(DataSource dataSource) {
        super(dataSource, SQL_SELECT_ALL_IDEA);
    }

    @Override
    protected Idea mapRow(ResultSet resultSet, int i) throws SQLException {
        Idea idea = new Idea();
        idea.setId(resultSet.getInt("id"));
        idea.setTxt(resultSet.getString("txt"));
        // idea.setPict(resultSet.getString("pict"));
        // idea.setVideo(resultSet.getString("video"));
        idea.setCaption(resultSet.getString("caption"));
        idea.setRating(resultSet.getFloat("rating"));
        // idea.setCount_like(resultSet.getInt("count_like"));
        //  idea.setCount_dislike(resultSet.getInt("count_dislike"));
        idea.setUsername(resultSet.getString("username"));
        idea.setDate_create(resultSet.getDate("date_create"));
        idea.setCategory(resultSet.getString("category_link"));
        idea.setTags(resultSet.getString("tags"));

        return idea;
    }
}
