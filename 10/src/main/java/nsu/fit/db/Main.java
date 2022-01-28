package nsu.fit.db;

import java.util.concurrent.Semaphore;

public class Main {

    private static final Semaphore semaphore = new Semaphore(1, true);

    public static void main(String[] args) {

        Runnable r = () -> {
            try {

                for (int i = 0; i < 10; i++) {
                    semaphore.acquire();
                    System.out.printf("String %d\n", i);
                    semaphore.release();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        try {

            Thread thread = new Thread(r, "Some thread");
            thread.start();

            for (int i = 0; i < 10; i++) {
                semaphore.acquire();
                System.out.printf("Main %d\n", i);
                semaphore.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

