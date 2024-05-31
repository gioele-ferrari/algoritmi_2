import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class Kruskal {
    private GraphInterface grafo;
    private GraphInterface albero;
    private MinHeap<Edge, Integer> heap;
    private int[] nodiScoperti;
    private int costo;

    public Kruskal(GraphInterface grafo) {
        this.grafo = grafo;
        this.albero = grafo.create();
        this.heap = new MinHeap<>();
    }

    public void creaAlbero() {
        init();
        for (Edge e : this.grafo.getOutEdges(0)) {
            this.heap.add(e, e.getWeight());
        }

        UnionFind unionFind = new UnionByRank(this.grafo.getOrder());
        while(!heap.isEmpty()) {
            Edge e = this.heap.extractMin();
            int nodoU = e.getTail();
            int nodoW = e.getHead();

            if (unionFind.find(nodoU) != unionFind.find(nodoW)) {
                this.albero.addEdge(e);
                this.costo += e.getWeight();
                unionFind.union(unionFind.find(nodoU), unionFind.find(nodoW));
            }
        }
    }

    public UndirectedGraph getMST() {
        this.creaAlbero();
        return (UndirectedGraph) this.albero;
    }

    public int getCosto() {
        this.creaAlbero();
        return this.costo;
    }

    private void init() {
        this.albero = this.grafo.create();
        this.costo = 0;
    }
}
