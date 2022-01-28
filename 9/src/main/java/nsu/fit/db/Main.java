package nsu.fit.db;

public class Main {

    private static volatile Object[] forks = new Object[5];
    private static final Philosopher [] philosophers = new Philosopher[5];

    public static void main (String[] args) {

        for (int i = 0; i < 5; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % 5]);
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
