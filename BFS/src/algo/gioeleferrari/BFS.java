package algo.gioeleferrari;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class BFS {
    /**
     * Questo è il grafo su cui lavoriamo nel progetto
     */
    private final GraphInterface grafo;
    /**
     * Questo è l'albero del grafo
     */
    private GraphInterface albero;
    /**
     * Questa lista indica tutti i nodi scoperti in ordine
     */
    private ArrayList<Integer> nodiScopertiOrdine;
    /**
     * Questa lista indica l'ordine di fine visita
     */
    private ArrayList<Integer> nodiScopertiOrdineFineVisita;

    /**
     * Questo array indica quali nodi sono stati scoperti
     */
    private boolean[] nodiScoperti;
    /**
     * Questo array indica la distanza dei nodi
     */
    private int[] distanzaNodi;
    /**
     * Questo array indica i padri dei nodi
     */
    private int[] nodiPadre;
    /**
     * La variabile indica la presenza di un ciclo
     */
    private boolean hasCycle;

    /**
     * Il costruttore della classe algo.gioeleferrari.Bfs
     * @param grafo sia per orientato e non orientato
     */

    public BFS(GraphInterface grafo) {
        this.grafo = grafo;
        init();
    }

    /**
     * Algoritmo che va a scoprire tutti i nodi di un grafo
     * @param nodoSorgente il nodo da cui si parte
     */
    private void visitaGrafo(int nodoSorgente) {
        init();

        ArrayList<Integer> coda = new ArrayList<>();

        this.nodiScopertiOrdine.add(nodoSorgente);
        this.nodiScoperti[nodoSorgente] = true;
        this.distanzaNodi[nodoSorgente] = 0;
        coda.add(nodoSorgente);

        while (!coda.isEmpty()) {
            Integer nodo = coda.removeFirst();
            for (int vicino : this.grafo.getNeighbors(nodo)) {
                if (!this.nodiScoperti[vicino]) {
                    this.albero.addEdge(nodoSorgente, vicino);
                    this.nodiScopertiOrdine.add(vicino);
                    this.nodiScoperti[vicino] = true;
                    this.distanzaNodi[vicino] = this.distanzaNodi[nodo] + 1;
                    this.nodiPadre[vicino] = nodo;
                    coda.add(vicino);
                }
                else if(vicino != this.nodiPadre[nodo]) {
                    this.hasCycle = true;
                }
            }
            this.nodiScopertiOrdineFineVisita.add(nodo);
        }
    }

    /**
     * Metodo che restituisce i nodi del grafico in ordine di come sono scoperti
     * @param nodoSorgente nodo di partenza
     * @return la lista di nodi ordinati
     */
    public ArrayList<Integer> getNodesInOrderVisit(int nodoSorgente) {
        this.visitaGrafo(nodoSorgente);
        return this.nodiScopertiOrdine;
    }

    /**
     * Metodo per calcolare la distanza dei nodi da un nodo sorgente
     * @param nodoSorgente nodo da cui iniziare il calcolo
     * @return array con dentro la distanza
     */
    public int[] getDistance(int nodoSorgente) {
        this.visitaGrafo(nodoSorgente);
        return this.distanzaNodi;
    }

    /**
     * Metodo per ricavare la distanza di un nodo da una sorgente
     * @param nodoSorgente il nodo di partenza
     * @param nodoDestinazione il nodo di arrivo
     * @return la distanza dei due nodi
     */
    public int getDistance(int nodoSorgente, int nodoDestinazione) {
        this.visitaGrafo(nodoSorgente);
        return this.distanzaNodi[nodoDestinazione];
    }

    /**
     * Metodo per ricavare l'albero di un grafo da una sorgente
     * @param nodoSorgente il nodo di partenza
     * @return l'albero creato dalla visita
     */
    public GraphInterface getTree(int nodoSorgente) {
        this.visitaGrafo(nodoSorgente);
        return this.albero;
    }

    /**
     * Metodo per controllare la presenza di cicli
     * @implNote Se il vicino è scoperto, ma non è il padre del nodo attuale, ci troviamo in
     * un ciclo, ad esempio un ciclo 1 -> 2 -> 3 -> 1 viene scoperto 1, ha come vicino
     * il 2, 2 viene quindi scoperto e ha come vicino 3, a questo punto vediamo che l'unico
     * vicino di 3 è 1, quindi il nodo vicino oltre al 2 che è padre di 3 è 1 che non è padre,
     * abbiamo quindi un ciclo.
     * @return se il grafo ha un ciclo
     */
    public boolean hasUndirectedCycle() {
        for (int nodo = 0; nodo < this.grafo.getOrder(); nodo++) {
            if (!this.nodiScoperti[nodo]) {
                this.visitaGrafo(nodo);
            }
        }
        return this.hasCycle;
    }

    /**
     * Metodo per controllare in un grafo orientato c'è un ciclo
     * @return se il grafo orientato ha un ciclo
     */
    public boolean hasDirCycle() {
        boolean[] stack = new boolean[this.grafo.getOrder()]; // Per tracciare i nodi nello stack della DFS

        for (int nodo = 0; nodo < this.grafo.getOrder(); nodo++) {
            if (!this.nodiScoperti[nodo]) {
                if (hasDirCycleImpl(nodo, stack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasDirCycleImpl(int nodoSorgente, boolean[] stack) {
        this.nodiScoperti[nodoSorgente] = true;
        stack[nodoSorgente] = true;

        for (int nodo : this.grafo.getNeighbors(nodoSorgente)) {
            if (!this.nodiScoperti[nodo]) {
                if (hasDirCycleImpl(nodo, stack)) {
                    return true;
                }
            } else if (stack[nodo]) {
                return true;
            }
        }

        stack[nodoSorgente] = false;
        return false;
    }

    /**
     * Restituisce la lista dei nodi che terminano in ordine nell'algoritmo
     * @param nodoSorgente nodo da cui partire
     * @return lista di nodi in ordine di terminazione
     */
    public ArrayList<Integer> getNodesInOrderPostVisit(int nodoSorgente) {
        this.visitaGrafo(nodoSorgente);
        return this.nodiScopertiOrdineFineVisita;
    }

    /**
     * Metodo per trovare il cammino più corto da sorgente a una destinazione
     * @param nodoSorgente il nodo da cui parte il cammino
     * @param nodoDestinazione il nodo in cui termina
     * @return il cammino per arrivare
     */
    public ArrayList<Edge> getShortestPath(int nodoSorgente, int nodoDestinazione) {
        ArrayList<Edge> camminoCorto = new ArrayList<>();

        if (!this.nodiScoperti[nodoDestinazione]) {
            // throw new IllegalArgumentException("La destinazione non e' stata scoperta");
            return null;
        }

        int nodoCorrente = nodoDestinazione;
        while (nodoCorrente != nodoSorgente) {
            int nodoPadreCorrente = this.nodiPadre[nodoCorrente];
            camminoCorto.add(new Edge(nodoPadreCorrente, nodoCorrente));
            nodoCorrente = nodoPadreCorrente;
        }

        // Bisogna però invertire il cammino per averlo da sorgente -> destinazione
        Collections.reverse(camminoCorto);

        return camminoCorto;
    }

    private void init() {
        this.albero = this.grafo.create();

        this.nodiScopertiOrdine = new ArrayList<>();
        this.nodiScopertiOrdineFineVisita = new ArrayList<>();

        this.nodiScoperti = new boolean[this.grafo.getOrder()];
        this.distanzaNodi = new int[this.grafo.getOrder()];
        this.nodiPadre = new int[this.grafo.getOrder()];

        Arrays.fill(this.nodiScoperti, false);
        Arrays.fill(this.distanzaNodi, -1);
        Arrays.fill(this.nodiPadre, -1);

        this.hasCycle = false;
    }
}
