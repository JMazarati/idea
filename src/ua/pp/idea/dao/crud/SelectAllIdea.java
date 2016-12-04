package ua.pp.idea.dao.crud;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.idea.entity.Idea;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dark on 15.11.2016.
 */
public class SelectAllIdea extends MappingSqlQuery<Idea>{
    private static final String SQL_SELECT_ALL_IDEA = "SELECT i.id,i.caption,i.txt,u.username,i.rating,i.date_create, i.category_link, i.tags FROM user_table u inner join idea_table i on(i.owner=u.id) ORDER BY id";

    public SelectAllIdea(DataSource dataSource){
        super(dataSource,SQL_SELECT_ALL_IDEA);

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
