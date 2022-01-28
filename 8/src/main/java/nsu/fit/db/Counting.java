package nsu.fit.db;

public class Counting extends Thread{

    private int start;
    private int threads;

    private Controller controller;

    Counting (int a, int b, Controller ctrl) {
        start = a;
        threads = b;
        controller = ctrl;
    }

    @Override
    public void run() {

        double res = 0;
        int i = start;

        while (!Thread.currentThread().isInterrupted()) {
            res += Math.pow(-1, i) / (2 * i + 1);
            i += threads;
        }

        controller.setResult(res);
    }
}
