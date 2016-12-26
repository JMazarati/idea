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
public class SelectIdeaByTextOrderByRating extends MappingSqlQuery<Idea> {
    private static final String SQL_SELECT_IDEA_BY_CATEGORY = "SELECT i.id, i.txt, i.pict,i.video, i.caption, i.rating, i.count_like, i.count_dislike, u.username, i.date_create, cat.title,i.tags FROM user_table u " +
            "INNER JOIN idea_table i ON(i.owner=u.id) INNER JOIN category_table cat ON (cat.id=i.category_link)  WHERE lower(i.txt) like :text ORDER BY rating DESC";

    public SelectIdeaByTextOrderByRating(DataSource dataSource) {
        super(dataSource, SQL_SELECT_IDEA_BY_CATEGORY);
        super.declareParameter(new SqlParameter("text", Types.VARCHAR));
    }

    @Override
    protected Idea mapRow(ResultSet resultSet, int i) throws SQLException {
        Idea idea = new Idea();
        idea.setId(resultSet.getInt("id"));
        idea.setTxt(resultSet.getString("txt"));
        idea.setPict(resultSet.getString("pict"));
        idea.setVideo(resultSet.getString("video"));
        idea.setCaption(resultSet.getString("caption"));
        idea.setRating(resultSet.getFloat("rating"));
        idea.setCount_like(resultSet.getInt("count_like"));
        idea.setCount_dislike(resultSet.getInt("count_dislike"));
        idea.setUsername(resultSet.getString("username"));
        idea.setDate_create(resultSet.getDate("date_create"));
        idea.setCategory(resultSet.getString("title"));
        idea.setTags(resultSet.getString("tags"));

        return idea;
    }
}
