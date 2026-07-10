package com.threadsExamples;

public class World{


    public static void main(String args[]) {
        // Two threads are working together but randomly. This is etxending the Thread class
//        Hello h =  new Hello();
//        h.start();

//        Runnable implemntation
        HelloRunnable hr = new HelloRunnable(); //NEW
        Thread t1 = new Thread(hr);
        t1.start(); //RUNNABLE
        for(int j=0;j<=10;j+=2){
            System.out.println(j);
        }
    }
}
