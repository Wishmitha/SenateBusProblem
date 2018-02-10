/**
 * Created by wolfpack on 2/8/18.
 */
public class Bus extends Thread {
    Bus() {
        System.out.printf(Main.C1 + "\nBus %d arrived\n" + Main.NC, this.getId());
    }

    public void run() {
        try {
            Main.mutex.acquire();
            int n = Math.min(Main.waiting, 50);
            for (int i = 0; i < n; i++) {
                Main.semBus.release();
                Main.semBoarded.acquire();
            }
            Main.waiting = Math.max(Main.waiting - 50, 0);
            Main.mutex.release();
            this.depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void depart() {
        System.out.printf(Main.C2 + "\nBus %d left\n" + Main.NC, this.getId() % 100);
    }
}
