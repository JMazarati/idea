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
public class SelectIdeaById extends MappingSqlQuery<Idea>{
    private static final String SQL_SELECT_IDEA_BY_ID="select i.id, i.txt, i.pict,i.video, i.caption, i.rating, i.count_like, i.count_dislike, u.username, i.date_create, i.category_link,i.tags from user_table u inner join idea_table i on(i.owner=u.id) WHERE i.id=:id";

    public SelectIdeaById(DataSource dataSource){
       super(dataSource,SQL_SELECT_IDEA_BY_ID);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }

    @Override
    protected Idea mapRow(ResultSet resultSet, int i) throws SQLException {
        Idea idea = new Idea();
        idea.setId(resultSet.getLong("id"));
        idea.setTxt(resultSet.getString("txt"));
        idea.setPict(resultSet.getString("pict"));
        idea.setVideo(resultSet.getString("video"));
        idea.setCaption(resultSet.getString("caption"));
        idea.setRating(resultSet.getFloat("rating"));
        idea.setCount_like(resultSet.getInt("count_like"));
        idea.setCount_dislike(resultSet.getInt("count_dislike"));
        idea.setUsername(resultSet.getString("username"));
        idea.setDate_create(resultSet.getDate("date_create"));
        idea.setCategory(resultSet.getString("category_link"));
        idea.setTags(resultSet.getString("tags"));

        return idea;
    }
}
