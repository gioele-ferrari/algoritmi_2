package gioele.ferrari;

import it.uniupo.graphLib.GraphInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class SoftwareSystem {
    private GraphInterface system;
    private boolean[] nodiTerminati;
    private boolean[] nodiScoperti;
    private boolean hasCycle;

    public SoftwareSystem(GraphInterface system) {
        this.system = system;
        this.hasCycle = false;
        init();
    }

    public void dfs(int nodoSorgente) {
        this.nodiScoperti[nodoSorgente] = true;
        for (Integer vicino : this.system.getNeighbors(nodoSorgente)) {
            if (!this.nodiScoperti[vicino]) {
                this.nodiScoperti[vicino] = true;
                dfs(vicino);
            } else if (!this.nodiTerminati[vicino]) {
                this.hasCycle = true;
            }
        }
        this.nodiTerminati[nodoSorgente] = true;
    }

    public boolean hasCycle() {
        for (int nodo = 0; nodo < this.system.getOrder(); nodo++) {
            if (!this.nodiScoperti[nodo]) {
                dfs(nodo);
            }
        }

        return this.hasCycle;
    }

    public void init() {
        this.nodiScoperti = new boolean[this.system.getOrder()];
        this.nodiTerminati = new boolean[this.system.getOrder()];

        Arrays.fill(this.nodiScoperti, false);
        Arrays.fill(this.nodiTerminati, false);
    }

}
