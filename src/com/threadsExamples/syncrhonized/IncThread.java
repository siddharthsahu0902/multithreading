package com.threadsExamples.syncrhonized;

public class IncThread extends Thread{
    private Counter counter;
    public IncThread(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            counter.increment();
        }
    }
}
