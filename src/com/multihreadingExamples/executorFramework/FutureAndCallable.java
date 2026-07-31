package com.multihreadingExamples.executorFramework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureAndCallable {
    public static void main(String[] args) {
         ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> f1 = executor.submit(()->{
            try{
                Thread.sleep(3000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            return 1;
        });

        try{
            System.out.println(f1.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Executing the further code.");

        executor.shutdown();

    }
}
