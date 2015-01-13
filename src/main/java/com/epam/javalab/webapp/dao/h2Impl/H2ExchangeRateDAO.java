package com.epam.javalab.webapp.dao.h2Impl;

import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.ConnectionPool;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class H2ExchangeRateDAO implements ExchangeRateDAO {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER = Logger.getLogger(H2ExchangeRateDAO.class);
    ResourceBundle sqlBundle = ResourceBundle.getBundle("sql");
    @Override
    public List<ExchangeRate> findAll() {
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("findAllExchangeRates");
        List<ExchangeRate> currentList = new ArrayList<ExchangeRate>();
        ResultSet ratesSet = null;
        Statement statement = null;
        ExchangeRate rate = null;
        try {
            statement = connection.createStatement();
            ratesSet = statement.executeQuery(sql);
            while (ratesSet.next()){
                rate = new ExchangeRate();
                rate.setId(ratesSet.getInt(1));
                rate.setCurrency(ratesSet.getString(2));
                rate.setValue(ratesSet.getInt(3));
                currentList.add(rate);
            }
            ratesSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return currentList;
    }

    @Override
    public void add(ExchangeRate rate) {
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("addExchangeRate");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,rate.getCurrency());
            preparedStatement.setInt(2,rate.getValue());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
    }

    @Override
    public void deleteByID(int id) {
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("deleteExchangeRateByID");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
    }

    @Override
    public void update(ExchangeRate rate) {
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("updateExchangeRate");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,rate.getCurrency());
            preparedStatement.setInt(2, rate.getValue());
            preparedStatement.setInt(3,rate.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
    }

    public ExchangeRate findByName(String transactionCurrency) {
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("findExchangeRateByName");
        ExchangeRate rate = new ExchangeRate();
        ResultSet ratesSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,transactionCurrency);
            ratesSet = preparedStatement.executeQuery();
            while (ratesSet.next()){
                rate.setCurrency(transactionCurrency);
                rate.setValue(ratesSet.getInt(1));
            }
            ratesSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return rate;
    }
}
