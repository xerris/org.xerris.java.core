package org.xerris.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RetryTests {
    @Test
    public void testRunnable() {
        Retry.retry(() -> System.out.println("hi"), new IOException(), "error occurs");
    }

    @Test()
    public void testRunnableWithException() {
        Assertions.assertThrows(RuntimeException.class, () ->
                Retry.retry(() -> {
                    throw new Exception("oops");
                }, new IOException(), "error occurs"));
    }

    @Test
    public void testCallable() {
        String result = Retry.retry(() -> "hi", new IOException(), "error occurs");
        System.out.println(result);

        List<String> results = Retry.retry(() -> Arrays.asList("hi1", "hi2", "hi3"), new IOException(), "error occurs");
        results.forEach(str -> System.out.println("List : " + str));
    }

    @Test()
    public void testCallableException() {

        Assertions.assertThrows(Exception.class, () -> {
            String result = Retry.retry(() -> {
                throw new Exception("oops");
            }, new IOException(), "error occurs");
        });
    }

}