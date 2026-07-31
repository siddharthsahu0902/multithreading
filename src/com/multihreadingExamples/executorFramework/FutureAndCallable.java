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

        // The execption is lost in the case of execute() as it doesn't return and there is no way to catch it
        // We can catch the exception using Callable and submit()


//        executor.execute(()->{int x = 10/0;});

        Future<Integer> f2 = executor.submit(()->10/0);
        try{
            f2.get();
        } catch (Exception e) {
            System.err.println("Exception occured while executing the Callable code.");
        }

        executor.shutdown();

    }
}
