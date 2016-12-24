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
public class SelectTopIdea extends MappingSqlQuery<Idea> {
    private static final String SQL_SELECT_TOP_IDEA = "SELECT * FROM idea_table WHERE (pict<>'') ORDER BY rating DESC LIMIT 5";

    public SelectTopIdea(DataSource dataSource) {
        super(dataSource, SQL_SELECT_TOP_IDEA );

    }

    @Override
    protected Idea mapRow(ResultSet resultSet, int i) throws SQLException {
        Idea idea = new Idea();
        idea.setId(resultSet.getInt("id"));
        idea.setTxt(resultSet.getString("txt"));
        idea.setPict(resultSet.getString("pict"));
        //idea.setVideo(resultSet.getString("video"));
        idea.setCaption(resultSet.getString("caption"));
        idea.setRating(resultSet.getFloat("rating"));
        idea.setCount_like(resultSet.getInt("count_like"));
        idea.setCount_dislike(resultSet.getInt("count_dislike"));
        //idea.setUsername(resultSet.getString("username"));
        idea.setDate_create(resultSet.getDate("date_create"));
        //idea.setCategory(resultSet.getString("title"));
        idea.setTags(resultSet.getString("tags"));

        return idea;
    }
}
