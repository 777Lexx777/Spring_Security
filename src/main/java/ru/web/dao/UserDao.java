package ru.web.dao;

import ru.web.model.User;
import java.util.List;

public interface UserDao {

    User getUserByName(String name);

    List<User> getCountUser(String count);

    User getUserId(long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);


}
