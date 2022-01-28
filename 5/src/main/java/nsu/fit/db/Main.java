package nsu.fit.db;

public class Main {
    public static void main(String [] args) {

        System.out.println("Main thread started");

        Runnable r = () -> {
            System.out.printf("%s started\n", Thread.currentThread().getName());

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("text");
            }

            System.out.printf("%s was interrupted\n", Thread.currentThread().getName());
        };

        Thread thread = new Thread(r,"Some thread");
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread finished");
    }
}
