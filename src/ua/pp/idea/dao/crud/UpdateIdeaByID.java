package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 03.12.2016.
 */
public class UpdateIdeaByID extends SqlUpdate{
private static final String SQL_UPDATE="UPDATE idea_table SET txt=:txt,pict=:pict,video=:video,caption=:caption,tags=:tags WHERE id=:id";

public UpdateIdeaByID(DataSource dataSource){
    super(dataSource,SQL_UPDATE);
    super.declareParameter(new SqlParameter("id", Types.INTEGER));
    super.declareParameter(new SqlParameter("txt",Types.VARCHAR));
    super.declareParameter(new SqlParameter("pict", Types.VARCHAR));
    super.declareParameter(new SqlParameter("video", Types.VARCHAR));
    super.declareParameter(new SqlParameter("caption", Types.VARCHAR));
    //super.declareParameter(new SqlParameter("category", Types.INTEGER));
    super.declareParameter(new SqlParameter("tags", Types.VARCHAR));
}

}
