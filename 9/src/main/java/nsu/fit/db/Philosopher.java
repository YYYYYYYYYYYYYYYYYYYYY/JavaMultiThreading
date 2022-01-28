package nsu.fit.db;

public class Philosopher extends Thread{

    private int id;

    private final Object leftFork;
    private final Object rightFork;

    Philosopher(int id, Object l, Object r) {
        this.id = id;
        this.leftFork = l;
        this.rightFork = r;
    }

    @Override
    public void run() {

        int eating = 0;
        System.out.printf("Philosopher %d thinks...\n", id);

        while (eating != 3) {
            try {
                synchronized (leftFork) {
                    synchronized (rightFork) {
                        System.out.printf("Philosopher %d eats!\n", id);
                        sleep(500);
                        eating++;
                    }
                }

                System.out.printf("Philosopher %d thinks...\n", id);
                sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
