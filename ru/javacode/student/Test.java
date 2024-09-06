package ru.javacode.student;

public class Test {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new BlockingQueue<>(10);
        int messageCounter = 100;

        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= messageCounter; ++i) {
                try {
                    queue.enqueue(i);
                    System.out.println("Produced: " + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Integer message = queue.dequeue();
                    System.out.println("Consumed: " + message);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

    }

}
