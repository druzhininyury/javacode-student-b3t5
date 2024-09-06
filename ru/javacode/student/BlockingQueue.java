package ru.javacode.student;

import java.util.ArrayDeque;
import java.util.Queue;

public class BlockingQueue<T> {

    private Queue<T> queue;
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>(capacity);
    }

    public synchronized void enqueue(T newElement) throws InterruptedException {
        while (size() >= capacity) {
            wait();
        }
        queue.offer(newElement);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (size() == 0) {
            wait();
        }

        notifyAll();
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

}
