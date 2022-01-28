package nsu.fit.db;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

public class ListSorter extends Thread {

    private final ArrayList<String> list;
    private final Semaphore semaphore;

    ListSorter(ArrayList<String> list, Semaphore semaphore) {
        this.list = list;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                sleep(5000);

                semaphore.acquire();
                list.sort(Comparator.comparing(String::toString));
                semaphore.release();
            }
        } catch (InterruptedException ignored) {

        }
    }
}
