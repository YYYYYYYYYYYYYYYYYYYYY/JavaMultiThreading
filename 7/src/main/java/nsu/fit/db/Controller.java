package nsu.fit.db;

public class Controller {

    private int iters;
    private int threads;

    private double result;

    Controller(int iterations, int numOfThreads) {
        this.iters = iterations;
        this.threads = numOfThreads;

        this.result = 0;
    }

    public void compute() {

        int iterInThread = iters/threads;

        Counting[] cnt = new Counting[threads];

        for (int i = 0; i < threads; i++) {
            int start, end;

            start = iterInThread * i;

            if (i != threads - 1) {
                end = iterInThread * (i + 1) - 1;
            } else {
                end = iters - 1;
            }

            cnt[i] = new Counting(start, end, this);
            cnt[i].start();
        }

        for (int i = 0; i < threads; i++) {
            try{
                cnt[i].join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void setResult(double res) {
        result += res;
    }

    public double getResult() {
        return result;
    }
}
