import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.Arrays;

public class Prim {
    private UndirectedGraph grafo;
    private GraphInterface albero;
    private MinHeap<Edge, Integer> heap;
    private boolean[] nodiScoperti;
    private int[] distanza;
    private int costoMST;


    public Prim(UndirectedGraph grafo) {
        this.grafo = grafo;
        this.albero = grafo.create();
        this.heap = new MinHeap<>();

        this.nodiScoperti = new boolean[grafo.getOrder()];
        this.distanza = new int[grafo.getOrder()];
    }

    private void creaAlbero() {
        init();
        this.nodiScoperti[0] = true;

        for (Edge e : this.grafo.getOutEdges(0)) {
            this.heap.add(e, e.getWeight());
        }

        while (!heap.isEmpty()) {
            Edge e = heap.extractMin();
            int nodoW = e.getHead();

            if (!this.nodiScoperti[nodoW]) {
                this.nodiScoperti[nodoW] = true;
                this.albero.addEdge(e);
                this.costoMST += e.getWeight();
                for (Edge e2 : this.grafo.getOutEdges(nodoW)) {
                    if (!this.nodiScoperti[e2.getHead()]) {
                        this.heap.add(e2, e2.getWeight());
                    }
                }
            }
        }
    }

    public UndirectedGraph getMST() {
        this.creaAlbero();
        return (UndirectedGraph) this.albero;
    }

    public int getCost() {
        this.creaAlbero(); // Per comodità usiamo sempre 0
        return this.costoMST;
    }

    private void init() {
        Arrays.fill(this.nodiScoperti, false);
        Arrays.fill(this.distanza, -1);
        this.albero = grafo.create();
        this.costoMST = 0;
    }

}
