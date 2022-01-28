package nsu.fit.db;

public class Main {
    public static void main(String [] args) {

        System.out.println("Main thread started");

        Runnable r = () -> {
            System.out.printf("%s started\n", Thread.currentThread().getName());

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("text");
            }
        };

        Thread thread = new Thread(r,"Some thread");
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        System.out.println("Main thread finished");
    }
}
