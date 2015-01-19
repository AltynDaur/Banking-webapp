package com.epam.javalab.webapp.dao;


import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.user.User;


import java.util.List;

public interface UserDAO {

    public List<User> getAll() throws DAOException;

    public void add(User user) throws DAOException;

    public void delete(int userID) throws DAOException;

    public void update(User user) throws DAOException;

    public User findByNameAndPass(String name, String password) throws DAOException;

    public User findUserByID(int userID) throws DAOException;

    public User findByName(String name) throws DAOException;
}
