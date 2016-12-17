package ua.pp.idea.dao;

import ua.pp.idea.entity.User;

import java.util.List;

/**
 * Created by Dark on 09.11.2016.
 */
public interface UserDao {
    void createUser(User user);

    List<User> findUserByName(String username);

    void updateUser(User user);
}
