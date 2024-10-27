package threads;

public class ConsumerThread extends Thread {
    public int result = 0;
    public int length;
    public PCBuffer buffer;


    public ConsumerThread(PCBuffer buffer, int length) {
        super("Consumer");
        this.length = length;
        this.buffer = buffer;
    }

    @Override
    public void run(){
        for(int i = 0; i < length; i++){
            try{
                result += buffer.get();
                System.out.printf("Consumer: Result is now %d\n", result);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("\nConsumer: Final result is: %d", result);
    }
}
