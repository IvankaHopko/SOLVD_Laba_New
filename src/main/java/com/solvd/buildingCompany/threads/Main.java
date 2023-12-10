package com.solvd.buildingCompany.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ConnectionPool connectionPool = new ConnectionPool(5);
        connectionPool.initializeConnectionPool();

        CompletableFuture<Void>[] tasks = new CompletableFuture[7];
        for (int i = 0; i < 7; i++) {
            tasks[i] = connectionPool.getConnectionAsync()
                    .thenAcceptAsync(connection -> {
                        LOGGER.info(Thread.currentThread().getName() + " acquired " + connection.getName());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        connectionPool.releaseConnection(connection);
                        LOGGER.info(Thread.currentThread().getName() + " released " + connection.getName());
                    });
        }

        CompletableFuture.allOf(tasks).join();
    }
}


