package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 10.12.2016.
 */
public class InsertLikeordislike extends SqlUpdate{
    private static final String SQL_INSERT="INSERT INTO rating_personal_table(user_creator,idea_link,likeordislike) " +
            "VALUES ((SELECT id FROM user_table WHERE username=:user_creator),:idea_link,:likeordislike)";

    public InsertLikeordislike(DataSource dataSource){
        super(dataSource,SQL_INSERT);
        super.declareParameter(new SqlParameter("user_creator", Types.VARCHAR));
        super.declareParameter(new SqlParameter("idea_link", Types.INTEGER));
        super.declareParameter(new SqlParameter("likeordislike", Types.INTEGER));
    }

}
