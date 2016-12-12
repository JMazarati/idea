package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 10.12.2016.
 */
public class UpdateRating extends SqlUpdate {
    private static final String SQL_UPDATE="UPDATE rating_personal_table " +
            "SET user_creator=(SELECT id FROM user_table WHERE username=:user_creator), idea_link=:idea_link,rating=:rating " +
            "WHERE user_creator=(SELECT id FROM user_table WHERE username=:user_creator) AND idea_link=:idea_link";

    public UpdateRating(DataSource dataSource){
        super(dataSource,SQL_UPDATE);
        super.declareParameter(new SqlParameter("user_creator", Types.VARCHAR));
        super.declareParameter(new SqlParameter("idea_link", Types.INTEGER));
        super.declareParameter(new SqlParameter("rating", Types.INTEGER));
        compile();
    }
}