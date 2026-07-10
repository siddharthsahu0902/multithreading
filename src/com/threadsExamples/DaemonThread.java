package com.threadsExamples;

public class DaemonThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Thread " + this.getId() + " is running");
        }
    }

    public static void main(String[] args) {
        DaemonThread t1 = new DaemonThread();
        t1.setDaemon(true);
        t1.start();

//        DaemonThread t2 = new DaemonThread();
//        t2.start();
    }
}
