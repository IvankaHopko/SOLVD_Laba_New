package com.solvd.buildingCompany.threads;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class ConnectionPool {

    private final int poolSize;
    private final Map<Integer, Connection> connectionPool = new ConcurrentHashMap<>();
    private int initialId = 1;
    private final Semaphore availableConnections;

    public ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.availableConnections = new Semaphore(poolSize);
    }

    public void initializeConnectionPool() {
        synchronized (connectionPool) {
            for (int i = 0; i < poolSize; i++) {
                connectionPool.put(initialId++, new Connection("Connection - " + i));
            }
        }
    }

    public CompletableFuture<Connection> getConnectionAsync() {
        return CompletableFuture.supplyAsync(() -> {
            Connection connection = null;
            try {
                availableConnections.acquire();
                connection = acquireConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return connection;
        });
    }

    public Connection acquireConnection() {
        synchronized (connectionPool) {
            for (Map.Entry<Integer, Connection> entry : connectionPool.entrySet()) {
                Integer key = entry.getKey();
                Connection connection = entry.getValue();
                if (connectionPool.replace(key, connection, null)) {
                    return connection;
                }
            }
        }
        throw new RuntimeException("No available connections");
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (connectionPool) {
                connectionPool.put(initialId++, connection);
                availableConnections.release();
            }
        }
    }
}