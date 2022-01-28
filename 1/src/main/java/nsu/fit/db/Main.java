package nsu.fit.db;

public class Main {

    public static void main(String[] args) {

        System.out.println("Main thread started");

        Runnable r = () -> {
            System.out.printf("%s started\n", Thread.currentThread().getName());

            for (int i = 0; i < 10; i++) {
                System.out.printf("String %d\n", i);
            }

            System.out.printf("%s finished\n", Thread.currentThread().getName());
        };

        Thread thread = new Thread(r,"Some thread");
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.printf("Main %d\n", i);
        }

        System.out.println("Main thread finished");
    }
}

