package nsu.fit.db;

public class Counting extends Thread{

    private int start;
    private int end;

    private Controller controller;

    Counting (int a, int b, Controller ctrl) {
        start = a;
        end = b;
        controller = ctrl;
    }

    @Override
    public void run() {

        double res = 0;

        for (int i = start; i <= end; i++) {
            res += Math.pow(-1, i) / (2 * i + 1);
        }

        controller.setResult(res);

    }
}
