package com.threadsExamples;

public class ThreadStates extends Thread{
    @Override
    public void run() {
        System.out.println("ThreadStates Running");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadStates ts = new ThreadStates();
        System.out.println(ts.getState());
        ts.start();
        System.out.println(ts.getState());
        Thread.sleep(100);
        System.out.println(ts.getState());
        ts.join(); //Main method will wait for ts to get finished. Here it is 2000ms
        System.out.println(ts.getState());
    }
}
