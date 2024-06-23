import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.Arrays;

public class Navigatore {
    private DirectedGraph carta;
    private boolean[] nodiScoperti;
    private int[] distanza;

    private int partenza;
    private  boolean[][] autostrada;
    public Navigatore(DirectedGraph carta, int partenza, boolean[][] autostrada) {
        this.carta = carta;
        this.partenza = partenza;
        this.autostrada = autostrada;
    }

    private void dijkstra(int nodoSorgente, boolean evitaAutostrade) {
        this.init();
        MinHeap<Edge, Integer> minHeap = new MinHeap<>();
        this.nodiScoperti[nodoSorgente] = true;
        this.distanza[nodoSorgente] = 0;

        for (Edge edge : this.carta.getOutEdges(nodoSorgente)) {
            /**
             * Se questo arco non è un nodo autostradale ed evitaAutostrade non è true allora lo aggiungiamo
             */
            if (!(evitaAutostrade && this.autostrada[edge.getTail()][edge.getHead()])) {
                minHeap.add(edge, edge.getWeight());
            }
        }

        while (!minHeap.isEmpty()) {
            Edge minEdge = minHeap.extractMin();
            if (!this.nodiScoperti[minEdge.getHead()]) {

                this.nodiScoperti[minEdge.getHead()] = true;
                this.distanza[minEdge.getHead()] = this.distanza[minEdge.getTail()] + minEdge.getWeight();

                for (Edge edge : this.carta.getOutEdges(minEdge.getHead())) {
                    if (!(evitaAutostrade && this.autostrada[edge.getTail()][edge.getHead()])) {
                        minHeap.add(edge, this.distanza[edge.getTail()] + edge.getWeight());
                    }
                }
            }
        }
    }

    public int distance(int destinazione, boolean evitaAutostrade) {
        this.dijkstra(this.partenza, evitaAutostrade);
        return this.distanza[destinazione];
    }

    public void init() {
        this.nodiScoperti = new boolean[this.carta.getOrder()];
        this.distanza = new int[this.carta.getOrder()];

        Arrays.fill(this.nodiScoperti, false);
        Arrays.fill(this.distanza, 0);
    }
}
