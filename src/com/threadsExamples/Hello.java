package com.threadsExamples;

public class Hello extends Thread{
    @Override
    public void run() {
        for(int j=1;j<10;j+=2){
            System.out.println(j);
        }
    }
}
