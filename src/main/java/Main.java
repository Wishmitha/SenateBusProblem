import java.util.concurrent.Semaphore;

/**
 * Created by wolfpack on 2/8/18.
 */
public class Main {

    // Semaphores
    static Semaphore mutex = new Semaphore(1);
    static Semaphore semBus = new Semaphore(0);
    static Semaphore semBoarded = new Semaphore(0);

    // Waiting count
    static int waiting = 0;

    // Means
    private static int busMean = 20 * 60;
    private static int riderMean = 30;

    // term colors
    static final String NC = (char) 27 + "[0m";
    static final String C1 = (char) 27 + "[31m";
    static final String C2 = (char) 27 + "[32m";
    static final String C3 = (char) 27 + "[33m";
    static final String C4 = (char) 27 + "[34m";

    public static void main(String[] args) {

        Thread busDispatchThread = new Thread(new Runnable() {
            public void run() {
                ExponentialDistributionGenerator expBus = new ExponentialDistributionGenerator(busMean);
                while (true) {
                    // Dispatch buses threads at random intervals
                    try {
                        Thread.sleep(expBus.getSample());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Bus().start();
                }
            }
        });

        Thread riderDispatchThread = new Thread(new Runnable() {
            public void run() {
                ExponentialDistributionGenerator expRider = new ExponentialDistributionGenerator(riderMean);
                while (true) {
                    // dispatch riders at random intervals
                    try {
                        Thread.sleep(expRider.getSample());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Rider().start();
                }
            }
        });

        // Start dispatcher threads
        busDispatchThread.start();
        riderDispatchThread.start();
    }
}