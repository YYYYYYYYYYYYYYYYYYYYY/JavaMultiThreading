package nsu.fit.db;

import java.util.concurrent.Semaphore;

public class Widget extends Thread {

    private int widgets;     // number of widgets
    private Semaphore SEMAPHORE;

    private boolean[] a;
    private boolean[] b;
    private boolean[] c;


    Widget (int num, boolean[] a, boolean[] b, boolean[] c, Semaphore s){
        this.widgets = num;
        this.a = a;
        this.b = b;
        this.c = c;
        SEMAPHORE = s;
    }

    @Override
    public void run() {
        for (int i = 0; i < widgets; i++) {
            while(true) {
                if (canCreateWidget(i)) break;
                else {
                    try{
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.printf("Widget %d created\n", i + 1);

        }
    }

    private boolean canCreateWidget(int i) {
        boolean res = false;
        try {
            SEMAPHORE.acquire();
            res = a[i] && b[i] && c[i];
            SEMAPHORE.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return res;
    }

}
