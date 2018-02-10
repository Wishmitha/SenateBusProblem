/**
 * Created by wolfpack on 2/8/18.
 */
public class Rider extends Thread {
    Rider() {
        System.out.printf(Main.C3 + "R %d arrived, " + Main.NC, this.getId());
    }

    public void run() {
        try {
            Main.mutex.acquire();
            Main.waiting += 1;
            Main.mutex.release();

            Main.semBus.acquire();
            this.board();
            Main.semBoarded.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void board() {
        System.out.printf(Main.C4 + "R %d boarded, " + Main.NC, this.getId() % 100);
    }
}
