package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UsrDao;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public class UsrServiceImpl implements UsrService {

    private final UsrDao usrDao;

    @Autowired
    public UsrServiceImpl(UsrDao usrDao) {
        this.usrDao = usrDao;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void createUser(User user) {
        usrDao.createUser(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public User readUser(int id) {
        return usrDao.readUser(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUser(int id, User user) {
        usrDao.updateUser(id, user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUser(int id) {
        usrDao.deleteUser(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return usrDao.getAllUsers();
    }
}
