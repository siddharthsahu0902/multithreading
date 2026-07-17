package com.multihreadingExamples.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock {
    private final Lock fairLock = new ReentrantLock(true);

    public void accessResouces(){
        fairLock.lock();
        try{
            System.out.println("Accessing Resouces by accquring the lock by " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        finally{
            System.out.println("Releasing the lock by " + Thread.currentThread().getName());
            fairLock.unlock();
        }
    }
    public static void main(String[] args) {
        FairLock lock = new FairLock();
        Runnable task  = new Runnable() {
            @Override
            public void run() {
                lock.accessResouces();
            }
        };
        Thread t1 = new Thread(task, "1");
        Thread t2 = new Thread(task, "2");
        Thread t3 = new Thread(task, "3");

        t1.start();
        t2.start();
        t3.start();
    }
}
