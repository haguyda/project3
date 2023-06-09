package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


public class ConnectionPool {

    private static ConnectionPool connectionPool = new ConnectionPool();

    private final static int SIZE = 10;

    private final Stack<Connection> connections = new Stack<>();


    private ConnectionPool() {
        InitConnections();
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    private void InitConnections() {
        for (int i = 0; i < SIZE; i++) {
            try {
                connections.push(DriverManager.getConnection(Credentials.URL, Credentials.USER, Credentials.PASSWORD));
            } catch (SQLException e) {
                System.out.println("Unable to create Connection Pool : " + e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        synchronized (this.connections) {
            if (connections.size() == 0) {
                try {
                    this.connections.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return connections.pop();
        }
    }

    public void restoreConnection(Connection connection) {
        synchronized (this.connections) {
            connections.push(connection);
            this.connections.notify();
        }
    }

    public void closeAllConnections() {
        synchronized (this.connections){
            while (this.connections.size()!=SIZE){
                try {
                    this.connections.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.connections.clear();
        }
    }
}