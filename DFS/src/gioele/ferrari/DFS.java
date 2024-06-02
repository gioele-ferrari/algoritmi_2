package gioele.ferrari;

import it.uniupo.graphLib.GraphInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    private final GraphInterface grafo;
    private GraphInterface albero;
    private ArrayList<Integer> nodiScopertiOrdine;
    private boolean[] nodiScoperti;
    private boolean hasDirectedCycle;
    private ArrayList<Integer> nodiScopertiOrdineFineVisita;

    public DFS(GraphInterface grafo) {
        this.grafo = grafo;
        init();
    }

    private void visitaGrafo(int nodoSorgente) {
        this.nodiScopertiOrdine.add(nodoSorgente);
        this.nodiScoperti[nodoSorgente] = true;

        for(Integer vicino : this.grafo.getNeighbors(nodoSorgente)) {
            if (!this.nodiScoperti[vicino]) {
                this.albero.addEdge(nodoSorgente, vicino);
                visitaGrafo(vicino);
            } else if (!this.nodiScopertiOrdineFineVisita.contains(vicino)) {
                this.hasDirectedCycle = true;
            }
        }

        this.nodiScopertiOrdineFineVisita.add(nodoSorgente);
    }

    private void visitaGrafoCompleto(int nodoSorgente) {
        init();
        for (int nodo = 0; nodo < this.grafo.getOrder(); nodo++) {
            if (!this.nodiScoperti[nodo]) {
                visitaGrafo(nodoSorgente);
            }
        }
    }

    public GraphInterface getAlbero(int nodoSorgente) {
        init();
        this.visitaGrafo(nodoSorgente);
        return this.albero;
    }

    public GraphInterface getAlberoRicoprente(int nodoSorgente) throws NotAllNodesReachedException {
        init();
        this.visitaGrafo(nodoSorgente);

        if (this.nodiScopertiOrdine.size() != this.albero.getOrder()) {
            throw new NotAllNodesReachedException("Non sono stati scoperti tutti i nodi");
        }

        return this.albero;
    }

    public boolean hasDirectedCycle() {
        for (int nodo = 0; nodo < this.grafo.getOrder(); nodo++) {
            if (!this.nodiScoperti[nodo]) {
                this.visitaGrafo(nodo);
            }
        }

        return this.hasDirectedCycle;
    }

    private void init() {
        this.albero = this.grafo.create();
        this.nodiScopertiOrdine = new ArrayList<>();
        this.nodiScoperti = new boolean[grafo.getOrder()];
        this.hasDirectedCycle = false;
        this.nodiScopertiOrdineFineVisita = new ArrayList<>();

        Arrays.fill(this.nodiScoperti, false);
    }
}
