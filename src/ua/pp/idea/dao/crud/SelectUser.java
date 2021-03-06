package ua.pp.idea.dao.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.idea.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dark on 09.11.2016.
 */
public class SelectUser extends MappingSqlQuery{
    private static final String SQL_CHECK_USER="SELECT username,pwd,email FROM user_table WHERE username=:username";

    public SelectUser(DataSource ds) {
        super(ds, SQL_CHECK_USER);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
    }

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setUserpwd(rs.getString("pwd"));
        user.setUseremail(rs.getString("email"));
        return user;
    }
}
