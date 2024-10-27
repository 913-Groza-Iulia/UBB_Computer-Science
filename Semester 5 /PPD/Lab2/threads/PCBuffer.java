package threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PCBuffer {
    private static final int capacity = 1;
    private final Queue<Integer> queue = new LinkedList<>(); //holds the values produced by the producer before the consumer retrieves them
    private final Lock lock = new ReentrantLock();
    private final Condition conditionVariable = lock.newCondition();

    public void addProductsToTheQueue(int value) throws InterruptedException {
        lock.lock();//only 1 thread can access or modify the queue until it releases the lock
        try{
            while (queue.size() == capacity) {
                conditionVariable.await(); // when it locks at the beginning of the function, the consumer cannot access the get function
                // await releases the lock so that the consumer can delete a product from the queue making the size 0
                //so not deleting => the size will be always 1 => infinite loop
                System.out.println(Thread.currentThread().getName() + "Queue is full");
            }
            queue.add(value);
            System.out.printf("%s added %d into the queue %n", Thread.currentThread().getName(), value);
            conditionVariable.signal(); // notify the consumer that a new variable is available
        }finally {
            lock.unlock();
        }
    }

    public int get() throws InterruptedException {//called by the consumer to retrieve a product from the buffer
        lock.lock();
        try{
            while(queue.isEmpty()){
                System.out.println(Thread.currentThread().getName()
                        + ": Buffer is empty, waiting");
                conditionVariable.await();
            }
            Integer value = queue.poll(); // retrieves and removes the head of the queue
            if(value != null){
                System.out.printf("%s consumed %d from the queue %n", Thread
                        .currentThread().getName(), value);

                conditionVariable.signal();
            }
            return value;
        }finally {
            lock.unlock();
        }
    }
}
