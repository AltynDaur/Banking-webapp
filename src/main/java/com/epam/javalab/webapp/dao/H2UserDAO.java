package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.user.Admin;
import com.epam.javalab.webapp.user.Client;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class H2UserDAO implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(H2UserDAO.class);
    ResourceBundle sqlBundle = ResourceBundle.getBundle("sql");
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public User findUser(String userName, String password) {
        LOGGER.debug("Find user started");
        Connection connection = connectionPool.takeConnection();
        ResultSet usersSet = null;
        User currentUser = null;

        String sql = sqlBundle.getString("findUser");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            usersSet = preparedStatement.executeQuery();
            while (usersSet.next()) {
                if (usersSet.getString(5).toUpperCase().equals(Role.ADMIN)) {
                    currentUser = new Admin();
                } else {
                    currentUser = new Client();
                }

                currentUser.setId(usersSet.getInt(1));
                currentUser.setFirstName(usersSet.getString(2));
                currentUser.setPassword(usersSet.getString(3));
                currentUser.setRole(usersSet.getString(5));
                currentUser.setEmail(usersSet.getString(4));
            }
            usersSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Can not get parameters");
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return currentUser;
    }


    public List<User> getAll() {
        LOGGER.debug("Find all users started");
        Connection connection = connectionPool.takeConnection();
        List<User> resultList = new ArrayList<User>();
        String sql = sqlBundle.getString("findAllUsers");
        ResultSet usersSet = null;
        User currentUser = null;
        try {
            Statement statement = connection.createStatement();
            usersSet = statement.executeQuery(sql);
            while (usersSet.next()) {
                if (usersSet.getString(5).toUpperCase().equals(Role.ADMIN)) {
                    currentUser = new Admin();
                } else {
                    currentUser = new Client();
                }

                currentUser.setId(usersSet.getInt(1));
                currentUser.setFirstName(usersSet.getString(2));
                currentUser.setPassword(usersSet.getString(3));
                currentUser.setRole(usersSet.getInt(5));
                currentUser.setEmail(usersSet.getString(4));
                resultList.add(currentUser);
            }
            usersSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return resultList;
    }


    public void add(String firstName, String password, Role role, String email) {
        LOGGER.debug("Add user started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("addClient");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            LOGGER.debug("Can't do query!");
            e.printStackTrace();
        }
        insertAndExecute(preparedStatement, firstName, password, role, email);
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
    }

    private void insertAndExecute(PreparedStatement preparedStatement, String firstName, String password, Role role, String email) {
        try {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            if (role == Role.ADMIN) {
                preparedStatement.setInt(4, 1);
            } else if (role == Role.CLIENT) {
                preparedStatement.setInt(4, 2);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void deleteByID(int userID) {
        LOGGER.debug("Delete user by ID started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("deleteUserByID");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
    }


    public void update(String firstName, String password, String email, Role role, int userID) {
        LOGGER.debug("Update user started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("updateUser");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(5,userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertAndExecute(preparedStatement, firstName, password, role, email);
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        connectionPool.realeseConnection(connection);
    }


    public User findUserByID(int accID) {
        LOGGER.debug("Find user by ID started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("findUserByID");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User currentUser = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString(5).toUpperCase().equals(Role.ADMIN)) {
                    currentUser = new Admin();
                } else {
                    currentUser = new Client();
                }

                currentUser.setId(resultSet.getInt(1));
                currentUser.setFirstName(resultSet.getString(2));
                currentUser.setPassword(resultSet.getString(3));
                currentUser.setRole(resultSet.getString(5));
                currentUser.setEmail(resultSet.getString(4));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return currentUser;
    }
}
