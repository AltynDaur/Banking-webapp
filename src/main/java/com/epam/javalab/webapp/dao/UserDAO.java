package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import java.util.List;

public interface UserDAO {
    public List getAll();
    public void add(String firstName, String password, Role role, String email);
    public void deleteByID(int userID);
    public void update(String firstName, String password, String email, Role role, int userID);

    public User findUserByID(int accID);
}
