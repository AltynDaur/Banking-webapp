package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAll();

    public void add(User user);

    public void delete(User user);

    public void update(User user);

    public User findByNameAndPass(String name, String password);
}
