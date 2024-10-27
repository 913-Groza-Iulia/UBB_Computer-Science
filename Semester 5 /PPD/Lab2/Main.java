import domain.Vector;
import threads.ConsumerThread;
import threads.PCBuffer;
import threads.ProducerThread;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector firstVector = new Vector(Arrays.asList(1,2,3,4));
        Vector secondVector = new Vector(Arrays.asList(5,6,7,8));

        PCBuffer buffer = new PCBuffer();
        ProducerThread producer = new ProducerThread(firstVector, secondVector, buffer);
        ConsumerThread consumer = new ConsumerThread(buffer, firstVector.getLength());

        producer.start();
        consumer.start();
    }
}