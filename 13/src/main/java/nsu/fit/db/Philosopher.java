package nsu.fit.db;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread{

    private int id;
    private final ArrayList<Boolean> forks;

    private final Condition wakeup;
    private final ReentrantLock lock;

    Philosopher(int id, ArrayList<Boolean> f, ReentrantLock l, Condition w) {
        this.id = id;
        this.forks = f;
        this.wakeup = w;
        this.lock = l;
    }


    @Override
    public void run() {

        int eating = 0;
        System.out.printf("Philosopher %d sits down at the table\n", id);

        while (eating != 3) {
            try {
                while (true) {
                    lock.lock();

                    if (forks.get(id) && forks.get((id + 1) % 5)) {
                        forks.set(id, false);
                        forks.set(((id + 1) % 5), false);
                        lock.unlock();
                        break;
                    }

                    wakeup.await();
                    lock.unlock();
                }

                System.out.printf("Philosopher %d eats!\n", id);
                eating++;
                sleep(300);

                lock.lock();

                forks.set(id, true);
                forks.set(((id + 1) % 5), true);
                wakeup.signalAll();
                System.out.printf("Philosopher %d thinks...\n", id);

                lock.unlock();

                sleep(50);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
