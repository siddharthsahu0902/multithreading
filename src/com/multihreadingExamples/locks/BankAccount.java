package com.multihreadingExamples.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance  = 100;

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) throws InterruptedException {
        System.out.println("Thread " + Thread.currentThread().getName() + " Attempt to Withdrawing "+amount);
        try{
            if(lock.tryLock(4000, TimeUnit.MILLISECONDS)) {
                if(balance >= amount) {
                    try{
                        System.out.println("Thread " + Thread.currentThread().getName() + " Withdrawing "+amount);
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println("Thread " + Thread.currentThread().getName() + " Withdrawn..");

                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        lock.unlock();
                    }
                } else{
                    System.out.println("Thread " + Thread.currentThread().getName() + " Failed, insufficient funds");
                }
                System.out.println("Available funds : " + balance);
            }
            else{
                System.out.println( Thread.currentThread().getName() + " Could not acquire lock, will try again later");
            }
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
