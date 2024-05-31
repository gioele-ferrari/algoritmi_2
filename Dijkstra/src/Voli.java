import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Voli {
    private int[] distanza;
    private int[] tempo;
    private GraphInterface collegamenti;
    private MinHeap<Edge, Integer> heap;
    private boolean[] nodiScoperti;
    private int[] nodiPadre;

    public Voli(DirectedGraph collegamenti) {
        this.collegamenti = collegamenti;
        this.heap = new MinHeap<>();
        this.distanza = new int[collegamenti.getOrder()];
        this.tempo = new int[collegamenti.getOrder()];
        this.nodiPadre = new int[collegamenti.getOrder()];
        this.nodiScoperti = new boolean[collegamenti.getOrder()];
    }

    public void dijkstra(int sorgente) {
        init();
        this.distanza[sorgente] = 0;
        this.nodiScoperti[sorgente] = true;

        for (Edge e : this.collegamenti.getOutEdges(sorgente)) {
            heap.add(e, this.distanza[sorgente] + e.getWeight());
        }

        while (!heap.isEmpty()) {
            Edge minEdge = heap.extractMin();
            int nodoU = minEdge.getTail();
            int nodoW = minEdge.getHead();

            if (!this.nodiScoperti[nodoW]) {
                this.nodiScoperti[nodoW] = true;
                this.distanza[nodoW] = this.distanza[nodoU] + minEdge.getWeight();
                this.nodiPadre[nodoW] = nodoU;
                for (Edge e : this.collegamenti.getOutEdges(nodoW)) {
                    int nodoZ = e.getHead();
                    if (!this.nodiScoperti[nodoZ]) {
                        heap.add(e, this.distanza[nodoW] + e.getWeight());
                    }
                }
            }
        }
    }

    private void bfs(int sorgente) {
        init();
        ArrayList<Integer> coda = new ArrayList<>();
        coda.add(sorgente);

        while (!coda.isEmpty()) {
            Integer nodo = coda.removeFirst();
            for (Edge e : this.collegamenti.getOutEdges(nodo)) {
                if(!this.nodiScoperti[e.getHead()]) {
                    this.nodiScoperti[e.getHead()] = true;
                    this.tempo[e.getHead()] = this.tempo[nodo] + e.getWeight();
                    this.distanza[e.getHead()] = this.distanza[nodo] + 1;
                    coda.add((e.getHead()));
                }
            }
        }
    }

    public int tempoMinimo(int sorgente, int destinazione) {
        this.dijkstra(sorgente);
        return this.distanza[destinazione];
    }

    public int scali(int sorgente, int destinazione) {
        this.bfs(sorgente);
        return this.distanza[destinazione];
    }

    public int tempo(int sorgente, int destinazione) {
        this.bfs(sorgente);
        return this.tempo[destinazione];
    }

    public ArrayList<Edge> trattaVeloce(int sorgente, int destinazione) {
        ArrayList<Edge> tratta = new ArrayList<>();
        int nodoCorrente = destinazione;

        // FUN FACT: ho perso qualcosa come 30 minuti solo per ricordare che devo anche usare l'algoritmo di scoperta :D
        this.dijkstra(sorgente);

        while (nodoCorrente != sorgente) {
            int nodoCorrentePadre = this.nodiPadre[nodoCorrente];
            tratta.add(new Edge(nodoCorrentePadre, nodoCorrente));
            nodoCorrente = nodoCorrentePadre;
        }
        Collections.reverse(tratta);
        return tratta;
    }

    public ArrayList<Integer> elencoScali(int sorgente, int destinazione) {
        ArrayList<Integer> elenco = new ArrayList<>();
        int nodoCorrente = destinazione;

        // FUN FACT: ho perso qualcosa come 30 minuti solo per ricordare che devo anche usare l'algoritmo di scoperta :D
        this.dijkstra(sorgente);

        while (nodoCorrente != sorgente) {
            int nodoCorrentePadre = this.nodiPadre[nodoCorrente];
            elenco.add(nodoCorrentePadre);
            nodoCorrente = nodoCorrentePadre;
        }
        elenco.removeLast(); // vogliamo solo gli scali intermedi
        Collections.reverse(elenco);
        return elenco;
    }

    public void init() {
        Arrays.fill(this.distanza, -1);
        Arrays.fill(this.nodiPadre, -1);
        Arrays.fill(this.tempo, 0);
        Arrays.fill(this.nodiScoperti, false);
    }
}
