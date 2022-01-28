package nsu.fit.db;

public class Controller {

    private int threads;
    private double result;

    private Counting[] cnt;

    Controller(int numOfThreads) {
        this.threads = numOfThreads;

        this.result = 0;
        this.cnt = new Counting[threads];
    }

    public void compute() {

        for (int i = 0; i < threads; i++) {
            cnt[i] = new Counting(i, threads, this);
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

    public void interrupt() {
        for (int i = 0; i < threads; i++) {
            cnt[i].interrupt();
        }
    }
}
