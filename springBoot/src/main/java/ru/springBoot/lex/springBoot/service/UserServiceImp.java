package ru.springBoot.lex.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.springBoot.lex.springBoot.dao.UserDao;
import ru.springBoot.lex.springBoot.model.User;


import java.util.List;

@Component
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String name) { return userDao.getUserByName(name); }
    @Override
    public List<User> getCountUser(String count) { return userDao.getCountUser(count); }
    @Override
    public User getUserId(long id) { return userDao.getUserId(id);}
    @Override
    public void saveUser(User user){ userDao.saveUser(user); }
    @Override
    public void updateUser(User user){ userDao.updateUser(user);}
    @Override
    public void deleteUser(User user){ userDao.deleteUser(user);}
}
