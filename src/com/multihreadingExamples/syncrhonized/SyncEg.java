package com.multihreadingExamples.syncrhonized;

public class SyncEg extends Thread{

    public static void main(String[] args) {
        Counter counter = new Counter(1);
        IncThread t1 = new  IncThread(counter);
        IncThread t2 = new  IncThread(counter);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(counter.getCounter());
    }
}
