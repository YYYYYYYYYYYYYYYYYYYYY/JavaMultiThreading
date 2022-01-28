package nsu.fit.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public final class Founder {

    private final List<Runnable> workers;
    private static CyclicBarrier BARRIER;

    public Founder(final Company company) {
        this.workers = new ArrayList<>(company.getDepartmentsCount());

        BARRIER = new CyclicBarrier(company.getDepartmentsCount(), company::showCollaborativeResult);

        for (int i = 0; i < company.getDepartmentsCount(); i++) {
            int finalI = i;
            Runnable worker = () -> {
                Department dep = company.getFreeDepartment(finalI);
                dep.performCalculations();

                try {
                    BARRIER.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    ex.printStackTrace();
                }

            };
            workers.add(worker);
        }


    }

    public void start() {
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }
    }
}
