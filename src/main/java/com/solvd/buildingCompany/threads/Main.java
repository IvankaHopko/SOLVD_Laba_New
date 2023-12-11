package com.solvd.buildingCompany.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        int threadPoolSize = 7;
        ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);

        Runnable connectionTask = () -> {
            try {
                Connection connection = connectionPool.acquireConnection();
                LOGGER.info(Thread.currentThread().getName() + " acquired " + connection.getName());
                Thread.sleep(1000);
                connectionPool.releaseConnection(connection);
                LOGGER.info(Thread.currentThread().getName() + " released " + connection.getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("An error occurred while processing a connection: " + e.getMessage());
            }
        };
        for (int i = 0; i < threadPoolSize; i++) {
            threadPool.execute(connectionTask);
        }

        Runnable waitingTask = () -> {
            try {
                LOGGER.info(Thread.currentThread().getName() + " waiting for a connection ");
                Connection connection = connectionPool.acquireConnection();
                LOGGER.info(Thread.currentThread().getName() + " acquired " + connection.getName());
                Thread.sleep(1000);
                connectionPool.releaseConnection(connection);
                LOGGER.info(Thread.currentThread().getName() + " released " + connection.getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("An error occurred while processing a connection: " + e.getMessage());
            }
        };
        for (int i = 0; i < 2; i++) {
            threadPool.execute(waitingTask);
        }
        threadPool.shutdown();
    }
}


