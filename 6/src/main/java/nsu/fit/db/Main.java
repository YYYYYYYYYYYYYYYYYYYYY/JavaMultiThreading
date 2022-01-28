package nsu.fit.db;

public class Main {
    public static void main(String[] args) {
        Company company = new Company(5);
        Founder founder = new Founder(company);
        founder.start();
    }
}
