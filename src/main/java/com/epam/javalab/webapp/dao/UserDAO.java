package com.epam.javalab.webapp.dao;


import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.user.User;


import java.util.List;

public interface UserDAO {

    public List<User> getAll();

    public void add(User user) throws DAOException;

    public void delete(int userID);

    public void update(User user) throws DAOException;

    public User findByNameAndPass(String name, String password);

    public User findUserByID(int userID);

    public User findByName(String name);
}
