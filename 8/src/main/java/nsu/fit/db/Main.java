package nsu.fit.db;

public class Main {

    private static int THREADS = 3;

    private static Controller controller = new Controller(THREADS);

    public static void main(String[] args) {

//        THREADS = Integer.parseInt(args[0]);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            System.out.println("Calculations were interrupted");

            controller.interrupt();
            System.out.println("Result: " + controller.getResult());

        }));

        System.out.println("Starting calculations");
        controller.compute();

    }
}
