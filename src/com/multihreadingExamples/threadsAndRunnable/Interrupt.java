package com.multihreadingExamples.threadsAndRunnable;

public class Interrupt extends Thread{

    public Interrupt(String name){
        super(name);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("tHREAD : "  + Thread.currentThread().getName() + " RUNNING");
            Thread.yield();
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted: " + e);
        }
    }
    public static void main(String[] args) {
        Interrupt t1 = new Interrupt("t1");
        t1.start();

        Interrupt t2 = new Interrupt("t2");
        t2.start();
        t1.interrupt();
    }
}
