package com.multihreadingExamples.locks.readWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Bank {
    private int amount = 1000;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    // Try with fairness:
//    ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public void checkBalance(){
        readLock.lock();
        try{
            System.out.println("Check by " + Thread.currentThread().getName() + "Current Balance " + this.amount);
        }
        finally{
            readLock.unlock();
        }
    }

    public void withdraw(){
        writeLock.lock();
        try{
            if(this.amount >= 50){
                System.out.println("Current Balance " + this.amount + " withdrawing : " + 50 + " by " +  Thread.currentThread().getName());
                this.amount -= 50;
                System.out.println("Withdrawn. Current Balance " + this.amount);
            }
            else{
                throw new RuntimeException("Insufficient Balance");
            }
        }
        catch(RuntimeException e){
            Thread.currentThread().interrupt();
        }
        finally{
            writeLock.unlock();
        }
    }

    public static void main (String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                bank.checkBalance();
            }
        };
        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                bank.withdraw();
            }
        };

        Thread readThread1 = new Thread(readTask, "Read 1");
        Thread readThread2 = new Thread(readTask, "Read 2");
        Thread readThread3 = new Thread(readTask, "Read 3");

        Thread writeThread1 = new Thread(writeTask, "Write 1");

        readThread1.start();
        writeThread1.start();
        readThread2.start();
        readThread3.start();

        readThread1.join();
        readThread2.join();
        readThread3.join();
        writeThread1.join();


    }
}
