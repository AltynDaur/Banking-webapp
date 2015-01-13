package com.epam.javalab.webapp.dao.h2Impl;

import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.ConnectionPool;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class H2TransactionHistoryDAO implements TransactionHistoryDAO {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    ResourceBundle sqlBundle = ResourceBundle.getBundle("sql");
    private static final Logger LOGGER = Logger.getLogger(H2TransactionHistoryDAO.class);

    @Override
    public void add(TransactionHistoryRecord record) {
        LOGGER.debug("TransactionHistoryRecord adding started");
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("addTransHistRecord");
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,record.getStartAcc());
            statement.setInt(2,record.getFinalAcc());
            statement.setLong(3, record.getAmount());
            statement.setString(4, String.valueOf(record.getCurrency()));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);

    }

    @Override
    public List<TransactionHistoryRecord> find(int accID) {
        LOGGER.debug("Finding TransactionHistoryRecord started");
        List<TransactionHistoryRecord> currentList = new ArrayList<TransactionHistoryRecord>();
        Connection connection = connectionPool.takeConnection();
        String sql = sqlBundle.getString("findTransHistRecordByAccID");
        ResultSet recordSet = null;
        TransactionHistoryRecord record;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accID);
            preparedStatement.setInt(2,accID);
            recordSet = preparedStatement.executeQuery();
            while (recordSet.next()){
                record = new TransactionHistoryRecord();
                record.setId(recordSet.getInt(1));
                record.setStartAcc(recordSet.getInt(2));
                record.setFinalAcc(recordSet.getInt(3));
                record.setAmount(recordSet.getLong(4));
                record.setCurrency(recordSet.getString(5));
                currentList.add(record);
            }
            recordSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return currentList;
    }
    public List<TransactionHistoryRecord> findAll(){
        LOGGER.debug("Finding all transaction records");
        Connection connection = connectionPool.takeConnection();
        List<TransactionHistoryRecord> currentList = new ArrayList<TransactionHistoryRecord>();
        String sql = sqlBundle.getString("findAllTransRecords");
        TransactionHistoryRecord record;
        ResultSet recordsSet = null;
        try {
            Statement statement = connection.createStatement();
            recordsSet = statement.executeQuery(sql);
            while (recordsSet.next()){
                record = new TransactionHistoryRecord();
                record.setId(recordsSet.getInt(1));
                record.setStartAcc(recordsSet.getInt(2));
                record.setFinalAcc(recordsSet.getInt(3));
                record.setAmount(recordsSet.getLong(4));
                record.setCurrency(recordsSet.getString(5));
                currentList.add(record);
            }
            recordsSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.realeseConnection(connection);
        return currentList;
    }
}
