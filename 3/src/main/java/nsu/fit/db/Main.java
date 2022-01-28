package nsu.fit.db;

import java.util.*;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String [] args) {

        Runner r = new Runner("text", "another text", "string", "thread", "five");

        Thread thread1 = new Thread(r, "First");
        Thread thread2 = new Thread(r, "Second");
        Thread thread3 = new Thread(r, "Third");
        Thread thread4 = new Thread(r, "Fourth");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}


class Runner implements Runnable {

    private final ArrayList<String> text;

    public Runner (String ...param) {
        this.text = new ArrayList<>(Arrays.asList(param));
    }

    @Override
    public void run() {

        while (true) {
            synchronized (text) {
                Iterator<String> i = text.iterator();

                if (i.hasNext()) {
                    System.out.println(Thread.currentThread().getName() + ": " + i.next());
                    i.remove();
                }
                else {
                    break;
                }
            }

            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

