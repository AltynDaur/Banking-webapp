package com.epam.javalab.webapp.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static String databaseProperties = "database";
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connectionQueue;

    private ConnectionPool(String driver, String url, String login, String pass, int poolSize) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Driver not found!");
            e.printStackTrace();
        }
        connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = DriverManager.getConnection(url,login,pass);
                connectionQueue.offer(connection);
            } catch (SQLException e) {
                LOGGER.error("Connection problem!");
                e.printStackTrace();
            }
        }
    }

    public static synchronized ConnectionPool getInstance(){
        if(instance == null){
            init();
        }
        return instance;
    }
    private static void init(){
            ResourceBundle databaseBundle = ResourceBundle.getBundle(databaseProperties);
            String driver = databaseBundle.getString("databaseDriver");
            String url = databaseBundle.getString("databaseURL");
            String login = databaseBundle.getString("databaseLogin");
            String pass = databaseBundle.getString("databasePass");
            int poolSize = Integer.parseInt(databaseBundle.getString("databasePoolSize"));
            instance = new ConnectionPool(driver, url, login, pass, poolSize);

    }
    public Connection takeConnection(){
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
        LOGGER.error("Take Connection command error");

        }
        return connection;
    }
    public void realeseConnection(Connection connection){
        try {
            if(!connection.isClosed()){
                if(!connectionQueue.offer(connection)){
                    try {
                        connectionQueue.put(connection);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dispose(){
        if(instance != null){
            instance.clearConnectionQueue();
            instance = null;
        }
    }

    private void clearConnectionQueue() {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null){
            try {
                if(!connection.getAutoCommit()){
                    connection.commit();
                }
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Can't close connection");
                e.printStackTrace();
            }

        }
    }


}
