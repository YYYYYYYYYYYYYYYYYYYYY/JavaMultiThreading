package nsu.fit.db;

import java.util.concurrent.Semaphore;

public class Main {

    private static final Semaphore semaphore = new Semaphore(1);
    private static final int AMOUNT = 10;

    private static boolean[] a = new boolean[AMOUNT];
    private static boolean[] b = new boolean[AMOUNT];
    private static boolean[] c = new boolean[AMOUNT];


    public static void main(String[] args) {

        new Widget(AMOUNT, a, b, c, semaphore).start();

        new Module(1, AMOUNT, a).start();
        new Module(2, AMOUNT, b).start();
        new Module(3, AMOUNT, c).start();

    }
}


