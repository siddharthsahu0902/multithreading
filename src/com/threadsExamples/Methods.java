package com.threadsExamples;

public class Methods extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Methods.run()");
    }

    public static void main(String[] args) throws InterruptedException {
        Methods m = new Methods();
        m.start();
        m.join();
        System.out.println("Waited 1000ms");
    }
}
