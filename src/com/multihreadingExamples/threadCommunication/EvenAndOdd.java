package com.multihreadingExamples.threadCommunication;

public class EvenAndOdd {
    private int counter = 0;
    private boolean evenFlag = false;



    public synchronized void printEven() {

        while (counter%2 == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(counter);
        counter++;
        evenFlag = false;
        notify();
    }

    public synchronized void printOdd() throws InterruptedException {

        while (counter%2 == 0) {
            wait();
        }
        System.out.println(counter);
        counter++;
        evenFlag = true;
        notify();
    }
}

class EvenThread implements Runnable{
    private final EvenAndOdd evenAndOdd;
    public EvenThread(EvenAndOdd evenAndOdd) {
        this.evenAndOdd = evenAndOdd;
    }
    @Override
    public void run() {
         for(int i = 0; i < 5; i++){
            evenAndOdd.printEven();
        }
    }
}

class OddThread implements Runnable{
    private final EvenAndOdd evenAndOdd;
    public OddThread(EvenAndOdd evenAndOdd) {
        this.evenAndOdd = evenAndOdd;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for(int i = 0; i < 5; i++){
                    evenAndOdd.printOdd();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Run{
    public static void main(String[] args) throws InterruptedException {
        EvenAndOdd evenAndOdd = new EvenAndOdd();
        EvenThread evenRunnable = new EvenThread(evenAndOdd);
        OddThread oddRunnable= new OddThread(evenAndOdd);

        Thread odd = new Thread(oddRunnable);
        Thread even  = new Thread(evenRunnable);

        odd.start();
        even.start();
    }
}
