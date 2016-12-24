package ua.pp.idea.dao;


import org.springframework.security.access.prepost.PreAuthorize;
import ua.pp.idea.dao.crud.DeleteUser;
import ua.pp.idea.dao.crud.InsertUser;
import ua.pp.idea.dao.crud.SelectUser;
import ua.pp.idea.dao.crud.UpdateUser;
import ua.pp.idea.entity.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Dark on 09.11.2016.
 */

public class UserDaoImpl implements UserDao, Serializable {
    private DataSource dataSource;
    private InsertUser insertUser;
    private SelectUser selectUser;
    private UpdateUser updateUser;
   private DeleteUser deleteUser;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {  //      return selectUser.executeByNamedParam(paramMap);
        this.dataSource = dataSource;
        this.insertUser = new InsertUser(dataSource);
        this.selectUser = new SelectUser(dataSource);
        this.updateUser = new UpdateUser(dataSource);
       this.deleteUser = new DeleteUser(dataSource);
    }

    @Override
    public void createUser(User user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("username", user.getUsername());
        paramMap.put("pwd", user.getUserpwd());
        paramMap.put("useremail", user.getUseremail());

        insertUser.updateByNamedParam(paramMap);

    }

    @Override
    public List<User> findUserByName(String username) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", username);
        return selectUser.executeByNamedParam(paramMap);
    }

    @Override
    public void updateUser(User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", user.getUsername());
        //paramMap.put("useremail",user.getUseremail());
        paramMap.put("userpwd", user.getUserpwd());
        updateUser.updateByNamedParam(paramMap);
    }
@PreAuthorize(value = "authenticated")
@Override
    public void deleteUser(String username) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("username",username);
        deleteUser.updateByNamedParam(paramMap);

    }
}
