import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class InterNetwork {
    private GraphInterface net;
    private int[] nodiPadre;
    private boolean[] nodiScoperti;
    private int[] distanza;

    public InterNetwork(UndirectedGraph net) {
        this.net = net;
    }

    private void bfs(int sorgente) {
        this.init();
        ArrayList<Integer> coda = new ArrayList<>();

        this.nodiScoperti[sorgente] = true;
        this.nodiPadre[sorgente] = sorgente;
        this.distanza[sorgente] = 0;

        coda.add(sorgente);

        while (!coda.isEmpty()) {
            Integer nodo = coda.removeFirst();
            for (int vicino : this.net.getNeighbors(nodo)) {
                if (!this.nodiScoperti[vicino]) {
                    this.nodiScoperti[vicino] = true;
                    this.distanza[vicino] = this.distanza[nodo] + 1;
                    this.nodiPadre[vicino] = nodo;
                    coda.add(vicino);
                }
            }
        }
    }

    public int numberOfHops(int r1, int r2) {
        if (r1 < 0 || r1 > this.net.getOrder() - 1 || r2 < 0 || r2 > this.net.getOrder() - 1) {
            return -1;
        }
        this.bfs(r1);
        return this.distanza[r2] + 1; // +1 per includere anche la partenza
    }

    private void init() {
        this.nodiScoperti = new boolean[this.net.getOrder()];
        this.distanza = new int[this.net.getOrder()];
        this.nodiPadre = new int[this.net.getOrder()];

        Arrays.fill(this.nodiScoperti, false);
        Arrays.fill(this.distanza, 0);
        Arrays.fill(this.nodiPadre, -1);
    }
}
