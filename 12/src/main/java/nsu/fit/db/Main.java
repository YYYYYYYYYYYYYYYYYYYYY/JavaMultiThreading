package nsu.fit.db;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    private static final ArrayList<String> list = new ArrayList<>();
    private static final Semaphore semaphore = new Semaphore(1, true);

    public static void main(String []args) {

        ListSorter ls = new ListSorter(list, semaphore);
        ls.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a list of strings: \n");

        while (true) {
            String inputString = scanner.nextLine();

            try {
                if (inputString.equals("")) {
                    semaphore.acquire();
                    list.forEach(System.out::println);
                    break;
                } else {
                    semaphore.acquire();
                    list.add(inputString);
                }

                semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ls.interrupt();
        try {
            ls.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
