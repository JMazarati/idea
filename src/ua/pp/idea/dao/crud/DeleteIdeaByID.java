package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 03.12.2016.
 */
public class DeleteIdeaByID extends SqlUpdate{
    private static final String SQL_DELETE="DELETE FROM idea_table WHERE id=:id";

    public DeleteIdeaByID(DataSource dataSource){
        super(dataSource,SQL_DELETE);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
        compile();
    }
}
