package com.threadsExamples.locks;

public class LockMain {

    public static void main(String[] args) throws InterruptedException {
        BankAccount sbi = new BankAccount();
        Runnable task = new Runnable(){
            public void run(){
                try {
                    sbi.withdraw(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread t1 = new Thread(task, "1");
        Thread t2 = new Thread(task, "2");
        t1.start();
        t2.start();
    }
}
