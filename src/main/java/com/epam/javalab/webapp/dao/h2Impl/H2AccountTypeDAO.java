package com.epam.javalab.webapp.dao.h2Impl;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
import com.epam.javalab.webapp.dao.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class H2AccountTypeDAO implements AccountTypeDAO {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResourceBundle sqlBundle = ResourceBundle.getBundle("sql");
    private static final Logger LOGGER = Logger.getLogger(H2AccountTypeDAO.class);
    @Override
    public List<AccountType> findAll() {
        LOGGER.debug("Find all account types started");
        Connection connection = connectionPool.takeConnection();
        List<AccountType> resultList = new ArrayList<AccountType>();
        String sql = sqlBundle.getString("findAllAccType");
        ResultSet accTypeSet = null;
        AccountType currentAccType = null;
        try {
            Statement statement = connection.createStatement();
            accTypeSet = statement.executeQuery(sql);
            while (accTypeSet.next()){
                currentAccType = new AccountType();
                currentAccType.setId(accTypeSet.getInt(1));
                currentAccType.setName(accTypeSet.getString(2));
                currentAccType.setPercent(accTypeSet.getInt(3));
                currentAccType.setPeriod(accTypeSet.getInt(4));
                currentAccType.setCurrency(accTypeSet.getString(5));
                resultList.add(currentAccType);
            }
            statement.close();
            accTypeSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return resultList;
    }

    @Override
    public void add(AccountType accountType) {
        LOGGER.debug("Add account type started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("addAccType");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,accountType.getName());
            preparedStatement.setInt(2,accountType.getPercent());
            preparedStatement.setInt(3,accountType.getPeriod());
            preparedStatement.setString(4, String.valueOf(accountType.getCurrency()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
    }

    @Override
    public void update(AccountType targetAccType) {
        LOGGER.debug("Update account type by ID started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("updateAccTypeByID");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, targetAccType.getName());
            preparedStatement.setInt(2,targetAccType.getPercent());
            preparedStatement.setInt(3,targetAccType.getPeriod());
            preparedStatement.setString(4, String.valueOf(targetAccType.getCurrency()));
            preparedStatement.setInt(5,targetAccType.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
    }

    @Override
    public void delete(int accTypeID) {
        LOGGER.debug("Delete account type started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("deleteAccType");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accTypeID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
    }

    @Override
    public AccountType findAccTypeByID(int accTypeID) {
        LOGGER.debug("Find account type by ID started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("findAccTypeByID");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AccountType accountType = new AccountType();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accTypeID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                accountType.setId(resultSet.getInt(1));
                accountType.setName(resultSet.getString(2));
                accountType.setPercent(resultSet.getInt(3));
                accountType.setPeriod(resultSet.getInt(4));
                accountType.setCurrency(resultSet.getString(5));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return accountType;
    }
}
