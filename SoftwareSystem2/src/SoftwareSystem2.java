import it.uniupo.graphLib.DirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class SoftwareSystem2 {
    private DirectedGraph system;
    private boolean[] nodiScoperti;
    private int[] nodiPadre;
    private boolean hasCycle;

    public SoftwareSystem2(DirectedGraph system) {
        this.system = system;
    }

    private void bfs(int nodoSorgente) {
        init();
        ArrayList<Integer> coda = new ArrayList<>();
        this.nodiScoperti[nodoSorgente] = true;
        this.nodiPadre[nodoSorgente] = nodoSorgente;

        coda.add(nodoSorgente);

        while (!coda.isEmpty()) {
            int nodo = coda.removeFirst();
            for (int vicino : this.system.getNeighbors(nodo)) {
                if (!this.nodiScoperti[vicino]) {
                    this.nodiScoperti[vicino] = true;
                    this.nodiPadre[vicino] = nodo;
                    coda.add(vicino);
                }
                else if (this.nodiPadre[nodo] != vicino) {
                    this.hasCycle = true;
                }
            }
        }
    }

    public int depends(int s1, int s2) {
        // Se uno dei due non è parte del sistema
        if (s1 < 0 || s1 > this.system.getOrder() || s2 < 0 || s2 > this.system.getOrder()) {
            return -1;
        }

        // Se sono uguali e sono nel sistema
        if (s1 == s2) {
            return -2;
        }

        bfs(s1);
        boolean s2_dipende_da_s1 = this.nodiScoperti[s2];

        bfs(s2);
        boolean s1_dipende_da_s2 = this.nodiScoperti[s1];

        if (s1_dipende_da_s2 && s2_dipende_da_s1) {
            return -3;
        }

        if (s1_dipende_da_s2) {
            return 1;
        }
        if (s2_dipende_da_s1) {
            return 2;
        }
        return 0;
    }

    private void init() {
        this.nodiScoperti = new boolean[this.system.getOrder()];
        this.nodiPadre = new int[this.system.getOrder()];

        Arrays.fill(this.nodiScoperti, false);
        Arrays.fill(this.nodiPadre, -1);

        this.hasCycle = false;
    }
}
