package ru.springBoot.lex.springBoot.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.springBoot.lex.springBoot.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Transactional(readOnly = true)
@Component
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> q =
                entityManager.createQuery("select u from User u where u.name = : name", User.class);
        q.setParameter("name", name);
        return  q.getResultList().stream().filter(user -> user.getName().equals(name))
                .findAny().orElse(null);
    }

    @Override
    public List<User> getCountUser(String count) {
        int countUsers = Integer.parseInt(count);
        List<User> userList = entityManager.createQuery("select u from User u ", User.class).getResultList();
        if (countUsers <=0 || countUsers >= 5){ return userList.subList(0, userList.size());}
            return userList.subList(0 , countUsers);
    }

    @Override
    public User getUserId(long id) {
        TypedQuery<User> q =
                entityManager.createQuery("select u from User u where u.id = : id", User.class);
        q.setParameter("id", id);
        return  q.getResultList().stream().filter(user -> user.getId() == id)
                .findAny().orElse(null);
    }

    @Override
    @Transactional
    public void saveUser(User user){entityManager.persist(user); }

    @Override
    @Transactional
    public void deleteUser(User user){entityManager.remove(entityManager.merge(user)); }

    @Override
    @Transactional
    public void updateUser( User user) { entityManager.merge(user);}
}
