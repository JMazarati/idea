package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 30.11.2016.
 */
public class InsertNewComment extends SqlUpdate {
    private final static String SQL_INSERT_COMMENT="INSERT INTO comments_table(idea_link,parent,note,user_creator) VALUES (:ideaLink,:parentLink,:note,(SELECT id FROM user_table WHERE username=:userLink))";

    public InsertNewComment(DataSource dataSource) {
        super(dataSource, SQL_INSERT_COMMENT);

        super.declareParameter(new SqlParameter("ideaLink", Types.INTEGER));
        super.declareParameter(new SqlParameter("parentLink", Types.INTEGER));
        super.declareParameter(new SqlParameter("note", Types.VARCHAR));
        super.declareParameter(new SqlParameter("userLink", Types.VARCHAR));
    }
}
