package com.epam.javalab.webapp.dao;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class H2AccountDAO implements AccountDAO {
    private ConnectionPool connectionPool= ConnectionPool.getInstance();
    ResourceBundle SQLBundle = ResourceBundle.getBundle("sql");
    ResourceBundle currencyBundle = ResourceBundle.getBundle("currency");
    private static final Logger LOGGER = Logger.getLogger(H2AccountDAO.class);
    @Override
    public List<Account> findAllByUserID(int id) {
        LOGGER.debug("findAllByUserID started");
        Connection connection = connectionPool.takeConnection();
        List<Account> resultList = new ArrayList<Account>();
        ResultSet accountsSet = null;
        String sql = SQLBundle.getString("findAccByUserID");
        Account currentAccount = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            accountsSet = preparedStatement.executeQuery();
            while (accountsSet.next()) {
                currentAccount = new Account();
                currentAccount.setId(accountsSet.getInt(1));
                //currentAccount.setOwner(accountsSet.getString(2));
                //currentAccount.setAcctype(accountsSet.getString(3));
                currentAccount.setAmount(accountsSet.getLong(4));
                //currentAccount.setExchangeRate(accountsSet.getString(5),accountsSet.getInt(6));

                resultList.add(currentAccount);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find all your accounts by ID");
            e.printStackTrace();
        }

        return resultList;
    }

    public List<Account> findAll(){
        LOGGER.debug("findAllAccounts started");
        List<Account> resultList = new ArrayList<Account>();
        Connection connection = connectionPool.takeConnection();
        String sql = SQLBundle.getString("findAllAccounts");
        Account account = null;
        ResultSet accSet = null;
        try {
            Statement statement = connection.createStatement();
            accSet = statement.executeQuery(sql);
            while (accSet.next()){
                account = new Account();
                account.setId(accSet.getInt(1));
                account.setOwner(accSet.getString(2));
                account.setAcctype(accSet.getString(3));
                account.setAmount(accSet.getLong(4));
                account.setCurrency(accSet.getString(5));
                resultList.add(account);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find all your accounts");
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public void update(int accID, long amount) {
        LOGGER.debug("Update account by ID started");
        Connection connection = connectionPool.takeConnection();
        String sql = SQLBundle.getString("updateAccountByID");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,amount);
            preparedStatement.setInt(2,accID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);

    }

    @Override
    public void deleteByID(int account) {
        LOGGER.debug("Delete account by ID started");
        Connection connection = connectionPool.takeConnection();
        String sql = SQLBundle.getString("deleteAccByID");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);


    }

    @Override
    public void add(Account account) {
        LOGGER.debug("Add account started");
        Connection connection = connectionPool.takeConnection();
        String sql = SQLBundle.getString("addAccount");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getOwner());
            preparedStatement.setString(2, account.getAcctype());
            preparedStatement.setLong(3, account.getAmount());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);

    }

    @Override
    public boolean transaction(int currentAccID, int targetAccID, long amount) {
        LOGGER.debug("Transaction between accounts started");
        boolean result = false;
        Connection connection = connectionPool.takeConnection();
        String updateAccountByID = SQLBundle.getString("updateAccountByID");
        String findAccountByID = SQLBundle.getString("findAccByIDForUpdate");

        Savepoint savepoint = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            savepoint = connection.setSavepoint("savepoint1");
            PreparedStatement preparedStatement = connection.prepareStatement(findAccountByID);
            preparedStatement.setInt(1, currentAccID);
            ResultSet currentAccountSet = preparedStatement.executeQuery();

            double coeffTenge = 0;
            long resultAmount = 0;
            while (currentAccountSet.next()) {
                coeffTenge = currentAccountSet.getInt(2);
                resultAmount = (long) (currentAccountSet.getLong(1)*coeffTenge - amount);
                resultAmount = (long) (resultAmount/coeffTenge);

                if (resultAmount < 0) {
                    throw new SQLException();
                }
            }
            currentAccountSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(updateAccountByID);
            preparedStatement.setLong(1, resultAmount);
            preparedStatement.setInt(2, currentAccID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(findAccountByID);
            preparedStatement.setInt(1, targetAccID);
            ResultSet targetAccountSet = preparedStatement.executeQuery();
            while (targetAccountSet.next()) {
                coeffTenge = targetAccountSet.getInt(2);
                resultAmount = (long) (targetAccountSet.getLong(1)*coeffTenge + amount);
                resultAmount = (long) (resultAmount/coeffTenge);
            }
            targetAccountSet.close();

            preparedStatement = connection.prepareStatement(updateAccountByID);
            preparedStatement.setLong(1, resultAmount);
            preparedStatement.setInt(2, targetAccID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LOGGER.error("Can't rollback transaction");

            }
            e.printStackTrace();

        }
        connectionPool.realeseConnection(connection);
        result = true;
        return result;
    }

    public Account findByAccID(int accID) {
        Connection connection = connectionPool.takeConnection();
        String sql = SQLBundle.getString("findAccByID");
        Account currentAccount = new Account();
        ResultSet accSet = null;
        try {
            Statement statement = connection.createStatement();
            accSet = statement.executeQuery(sql);
            while (accSet.next()){
                currentAccount = new Account();
                currentAccount.setId(accSet.getInt(1));
                currentAccount.setOwner(accSet.getString(2));
                currentAccount.setAcctype(accSet.getString(3));
                currentAccount.setAmount(accSet.getLong(4));
                currentAccount.setCurrency(accSet.getString(5));
            }
            accSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return currentAccount;
    }
}
