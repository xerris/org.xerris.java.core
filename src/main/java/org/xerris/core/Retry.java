package org.xerris.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class Retry {
    private static final int RETRY = 3;
    private static final long DELAY = 1000L;

    private static final Logger logger = LoggerFactory.getLogger(Retry.class);

    @FunctionalInterface
    public interface RunnableWithException {
        void run() throws Exception;
    }

    public static <V> V retry(Callable<V> callable, Throwable throwable, String message) {
        return retryLogics(callable, throwable, message);
    }

    public static void retry(RunnableWithException runnable, Throwable throwable, String message) {
        retryLogics(() -> {
            runnable.run();
            return null;
        }, throwable, message);
    }

    private static <T> T retryLogics(Callable<T> callable, Throwable throwable, String message) {
        int counter = 0;

        while (counter < RETRY) {
            try {
                return callable.call();
            } catch (Exception e) {
                counter++;
                logger.error("retry " + counter + " / " + RETRY + ", " + message, e);

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        throw new RuntimeException(throwable);
    }
}
