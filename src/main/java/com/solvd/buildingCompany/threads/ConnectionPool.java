package com.solvd.buildingCompany.threads;

import java.util.Map;
import java.util.concurrent.*;

public class ConnectionPool {

    private final int poolSize;
    private static ConnectionPool connectionPool;
    private final BlockingQueue<Connection> connections;

    private ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.connections = new LinkedBlockingQueue<>(poolSize);
        initializeConnectionPool();
    }

    public static synchronized ConnectionPool getInstance(int poolSize) {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool(poolSize);
        }
        return connectionPool;
    }

    public void initializeConnectionPool() {
        for (int i = 0; i < poolSize; i++) {
            connections.offer(new Connection("Connection - " + i));
        }
    }

    public CompletableFuture<Connection> getConnectionAsync() {
        return CompletableFuture.supplyAsync(() -> {
            Connection connection = null;
            try {
                connection = acquireConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return connection;
        });
    }

    public Connection acquireConnection() throws InterruptedException {
        return connections.take();
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connections.offer(connection);
        }
    }
}