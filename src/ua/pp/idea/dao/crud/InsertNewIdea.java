package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 15.11.2016.
 */
public class InsertNewIdea extends SqlUpdate {
    private final static String SQL_INSERT_IDEA="INSERT INTO idea_table(txt,pict,video,caption,category_link,tags,owner) VALUES (:txt,:pict,:video,:caption,:category,:tags,(SELECT id FROM user_table WHERE username=:username))";

    public InsertNewIdea(DataSource dataSource){
        super(dataSource,SQL_INSERT_IDEA);
        super.declareParameter(new SqlParameter("txt", Types.VARCHAR));
        super.declareParameter(new SqlParameter("pict", Types.VARCHAR));
        super.declareParameter(new SqlParameter("video", Types.VARCHAR));
        super.declareParameter(new SqlParameter("caption", Types.VARCHAR));
        super.declareParameter(new SqlParameter("category", Types.INTEGER));
        super.declareParameter(new SqlParameter("tags", Types.VARCHAR));
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
    }
}
