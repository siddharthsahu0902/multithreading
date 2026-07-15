package com.threadsExamples.syncrhonized;

public class Counter {
    private int counter;
    public Counter(int counter) {
        this.counter = counter;
    }
    public int getCounter() {
        return this.counter;
    }
//    This is one way to make the entire function synchronized
    public synchronized void increment() {
        counter++;
    }

//    To make a block of code synchronized
//    public void increment() {
//        synchronized(this) {
//            counter++;
//        }
//    }
}
