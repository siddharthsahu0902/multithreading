package com.multihreadingExamples.threadCommunication;

class SharedBuffer {
    private int data;
    private boolean available = false;

    // Producer
    public synchronized void produce(int value) throws InterruptedException {

        // Wait if previous data hasn't been consumed
        while (available) {
            wait();
        }

        data = value;
        System.out.println("Produced: " + data);

        available = true;

        // Wake up the consumer
        notify();
    }

    // Consumer
    public synchronized void consume() throws InterruptedException {

        // Wait until data is available
        while (!available) {
            wait();
        }

        System.out.println("Consumed: " + data);

        available = false;

        // Wake up the producer
        notify();
    }
}

class Producer extends Thread {

    private SharedBuffer buffer;

    Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.produce(i);
                Thread.sleep(1000); // simulate work
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    private SharedBuffer buffer;

    Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.consume();
                Thread.sleep(1500); // simulate work
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        SharedBuffer buffer = new SharedBuffer();

        Producer producer = new Producer(buffer);
        Thread consumer = new Thread(new Consumer(buffer));

        producer.start();
        consumer.start();
    }
}