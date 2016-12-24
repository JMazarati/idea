package ua.pp.idea.dao;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.pp.idea.entity.User;

import java.util.List;

/**
 * Created by Dark on 09.11.2016.
 */
public interface UserDao {
    void createUser(User user);

    List<User> findUserByName(String username);

    void updateUser(User user);

    @PreAuthorize(value = "authenticated")
    void deleteUser(String username);
}
