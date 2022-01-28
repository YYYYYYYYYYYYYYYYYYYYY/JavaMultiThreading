package nsu.fit.db;

import java.util.concurrent.TimeUnit;

public class Module extends Thread {

    private int time;
    private int widgets;
    private final boolean[] mod;


    Module(int time, int n, boolean[] a) {
        this.time = time;
        this.widgets = n;
        this.mod = a;
    }

    @Override
    public void run() {
        for (int i = 0; i < widgets; i++) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(time));

                synchronized (mod) {
                    mod[i] = true;
                }


            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
