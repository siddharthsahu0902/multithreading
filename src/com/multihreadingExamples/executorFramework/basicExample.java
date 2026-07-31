package com.multihreadingExamples.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class basicExample {

    public static void main(String[] args) {
         ExecutorService executor = Executors.newFixedThreadPool(2);
         for (int i = 0; i < 5; i++) {
             final int taskId = i;
             executor.execute(()->System.out.println("Task " + taskId + " Thread: " +Thread.currentThread().getName()));
         }
         executor.shutdown();
    }
}
