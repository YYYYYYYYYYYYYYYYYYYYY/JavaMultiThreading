package nsu.fit.db;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final ArrayList<Boolean> forks = new ArrayList<>(5);
    private static final Philosopher [] philosophers = new Philosopher[5];

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition wakeup = lock.newCondition();

    public static void main (String[] args) {

        for (int i = 0; i < 5; i++) {
            forks.add(true);
        }

        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i, forks, lock, wakeup);
            philosophers[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
