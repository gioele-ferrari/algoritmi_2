import java.util.ArrayList;
import java.util.Collections;

public class MSI {
    public int[] pesi;
    public int[] soluzioneOttimale;

    public MSI(int[] pesi) {
        this.pesi = pesi;
        this.soluzioneOttimale = new int[pesi.length + 1];
    }

    public void MSI_impl() {
        init();
        if (pesi.length == 0) {
            return;
        }

        this.soluzioneOttimale[0] = 0;
        this.soluzioneOttimale[1] = this.pesi[0];

        for (int i = 2; i <= this.pesi.length; i++) {
            this.soluzioneOttimale[i] = Math.max(this.soluzioneOttimale[i - 1], this.soluzioneOttimale[i - 2] + this.pesi[i - 1]);
        }
    }

    public int getMaxVal() {
        this.MSI_impl();
        return this.soluzioneOttimale[this.pesi.length];
    }

    public ArrayList<Integer> getOptSol() {
        this.MSI_impl();
        ArrayList<Integer> optSolList = new ArrayList<>();
        int i = this.pesi.length;

        while (i > 0) {
            if (this.soluzioneOttimale[i] > this.soluzioneOttimale[i - 1]) {
                optSolList.add(i - 1);
                i -= 2;
            } else {
                i -= 1;
            }
        }
        Collections.reverse(optSolList);
        return optSolList;
    }

    public void init() {
        this.soluzioneOttimale = new int[this.pesi.length + 1];
    }
}
