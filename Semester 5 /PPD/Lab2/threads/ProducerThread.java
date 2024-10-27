package threads;

import domain.Vector;

public class ProducerThread extends Thread {
    public int length;
    public Vector firstVector, secondVector;
    public PCBuffer buffer;

    public ProducerThread(Vector firstVector, Vector secondVector, PCBuffer buffer) {
        super("Producer");
        this.firstVector = firstVector;
        this.secondVector = secondVector;
        this.buffer = buffer;
        this.length = firstVector.getLength();
    }

    @Override
    public void run() {
        for(int i=0; i<length; i++) {
            try {
                buffer.addProductsToTheQueue(firstVector.get(i) * secondVector.get(i));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
