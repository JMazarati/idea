package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.idea.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 18.11.2016.
 */
public class SelectUserByEmail extends MappingSqlQuery {
    private static final String SQL_CHECK_USER_BY_EMAIL="SELECT username,email FROM user_table WHERE email=:useremail";

    public SelectUserByEmail(DataSource ds) {
        super(ds, SQL_CHECK_USER_BY_EMAIL);
        super.declareParameter(new SqlParameter("useremail", Types.VARCHAR));
    }

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setUseremail(rs.getString("email"));
        return user;
    }


}
