package nsu.fit.db;

public class Main {

    private static int ITER = 100;
    private static int THREADS = 3;

    public static void main(String[] args) {

//        THREADS = Integer.parseInt(args[0]);

        Controller controller = new Controller(ITER, THREADS);
        controller.compute();

        System.out.println(controller.getResult());
    }
}
