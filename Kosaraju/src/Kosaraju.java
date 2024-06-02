import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Kosaraju {
    private GraphInterface grafo;
    private GraphInterface alberoScoperta;
    private int[] nodiPadre;
    private boolean[] nodiScoperti;
    private boolean[] nodiTerminati;
    private ArrayList<Integer> ordineFinevisita;
    private int[] componenteConnessa;
    private int activeComponent;
    private boolean hasCycle;

    public Kosaraju(GraphInterface grafo) {
        this.grafo = grafo;
        init();
    }

    private void dfs(int nodoSorgente) {
        this.nodiScoperti[nodoSorgente] = true;
        this.componenteConnessa[nodoSorgente] = this.activeComponent;

        for (int vicino : this.grafo.getNeighbors(nodoSorgente)) {
            if(!this.nodiScoperti[vicino]) {
                this.nodiScoperti[vicino] = true;
                alberoScoperta.addEdge(nodoSorgente, vicino);
                dfs(vicino);
            }
            else if(!this.nodiTerminati[vicino]) {
                this.hasCycle = true;
            }
        }
        this.nodiTerminati[nodoSorgente] = true;
        this.ordineFinevisita.add(nodoSorgente);
    }

    private void visitaCompletaDfs() {
        for (int nodo = 0; nodo < this.grafo.getOrder(); nodo++) {
            if (!this.nodiScoperti[nodo]) {
                dfs(nodo);
                activeComponent++;
            }
        }
    }

    public boolean isConnected() {
        init();
        this.dfs(0);
        for (int nodo = 0; nodo < this.grafo.getOrder(); nodo++) {
            if (!this.nodiScoperti[nodo]) {
                return false;
            }
        }
        return true;
    }

    public int[] connectedComponents() {
        this.visitaCompletaDfs();
        return this.componenteConnessa;
    }

    public boolean hasCycleImpl() {
        for (int nodo = 0; nodo < this.grafo.getOrder(); nodo++) {
            if(!this.nodiScoperti[nodo]) {
                dfs(nodo);
            }
        }

        return this.hasCycle;
    }

    public ArrayList<Integer> topologicalOrder() {
        if (this.grafo instanceof UndirectedGraph || this.hasCycleImpl()) {
            throw new IllegalArgumentException("il grafo deve essere un dag");
        }
        this.visitaCompletaDfs();
        Collections.reverse(this.ordineFinevisita);
        return this.ordineFinevisita;
    }

    private void init() {
        this.nodiScoperti = new boolean[this.grafo.getOrder()];
        this.nodiTerminati = new boolean[this.grafo.getOrder()];
        this.nodiPadre = new int[this.grafo.getOrder()];
        this.componenteConnessa = new int[this.grafo.getOrder()];
        this.alberoScoperta = grafo.create();

        Arrays.fill(this.nodiScoperti, false);
        Arrays.fill(this.nodiTerminati, false);
        Arrays.fill(this.nodiPadre, -1);
        Arrays.fill(this.componenteConnessa, -1);

        ordineFinevisita = new ArrayList<>();
        this.activeComponent = 0;
        this.hasCycle = false;
    }
}
