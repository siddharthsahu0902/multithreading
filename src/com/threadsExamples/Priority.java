package com.threadsExamples;

public class Priority extends Thread {

    // Calling the constructor of the parent class and giivng the thread its name.
    public Priority(String name){
        super(name);
    }
    @Override
    public void run() {
        for(int i=0; i<5;i++){
            System.out.println("Thread "+Thread.currentThread().getName()+" is running with Priority : " + Thread.currentThread().getPriority() );
        }
    }

    public static void main(String[] args) {
        Priority t1 = new Priority("Low Priority");
        Priority t2 = new Priority("High Priority");
        Priority t3 = new Priority("Medium Priority");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }

}
