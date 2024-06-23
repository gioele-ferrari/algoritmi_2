import java.util.ArrayList;
import java.util.Collections;

public class TrasportiPortuali {
    private int[] guadagno;
    private int[] soluzioneOttimale;

    public TrasportiPortuali(int[] guadagno) {
        this.guadagno = guadagno;
    }

    private void msi() {
        init();
        this.soluzioneOttimale[0] = 0;
        this.soluzioneOttimale[1] = this.guadagno[0];

        for (int i = 2; i < this.guadagno.length + 1; i++) {
            this.soluzioneOttimale[i] = Math.max(this.soluzioneOttimale[i - 1], this.soluzioneOttimale[i - 2] + this.guadagno[i - 1]);
        }
    }

    private ArrayList<Integer> ricostruzione_opt_sol() {
        ArrayList<Integer> soluzioneOpt = new ArrayList<>();
        this.msi();
        int i = this.guadagno.length;

        while (i > 0) {
            if (soluzioneOttimale[i] > this.soluzioneOttimale[i - 1]) {
                soluzioneOpt.add(i - 1);
                i -= 2;
            }
            else {
                i -= 1;
            }
        }
        Collections.reverse(soluzioneOpt);
        return soluzioneOpt;
    }

    public boolean accettareCarico(int s) {
        if (s < 0 || s > this.guadagno.length) {
            return false;
        }
        ArrayList<Integer> soluzione_opt = this.ricostruzione_opt_sol();
        return soluzione_opt.contains(s);
    }

    private void init() {
        this.soluzioneOttimale = new int[this.guadagno.length + 1];
    }
}
