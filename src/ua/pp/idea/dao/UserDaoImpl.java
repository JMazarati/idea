package ua.pp.idea.dao;


import ua.pp.idea.dao.crud.InsertUser;
import ua.pp.idea.dao.crud.SelectUser;
import ua.pp.idea.entity.User;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by Dark on 09.11.2016.
 */

public class UserDaoImpl implements UserDao{
    private DataSource dataSource;
    private InsertUser insertUser;
    private SelectUser selectUser;
   // private SelectUserByEmail selectUserByEmail;

    public DataSource getDataSource() {
        return dataSource;
    }
@Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {  //      return selectUser.executeByNamedParam(paramMap);
        this.dataSource = dataSource;
        this.insertUser=new InsertUser(dataSource);
        this.selectUser=new SelectUser(dataSource);
    //this.selectUserByEmail=new SelectUserByEmail(dataSource);
}

    @Override
    public void createUser(User user) {
        Map<String,Object> paramMap = new HashMap<String,Object>();

        paramMap.put("username",user.getUsername());
        paramMap.put("pwd",user.getUserpwd());
        paramMap.put("useremail",user.getUseremail());

        insertUser.updateByNamedParam(paramMap);

    }
    public List<User> findUserByName(String username){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("username",username);
        return selectUser.executeByNamedParam(paramMap);
    }

    //  public List<User> findUserByEmail(String useremail){
    //      Map<String,Object> paramMap = new HashMap<String,Object>();
    //      paramMap.put("useremail",useremail);

    // }
}
