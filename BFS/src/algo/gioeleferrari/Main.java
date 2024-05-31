package algo.gioeleferrari;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GraphInterface grafo = new DirectedGraph("4; 2 0; 1 0; 3 2; 3 1");
        BFS bfs = new BFS(grafo);

        ArrayList<Integer> listaNodiVisitati = bfs.getNodesInOrderVisit(3);
        for (int nodo = 0; nodo < grafo.getOrder(); nodo++) {
            System.out.println(listaNodiVisitati.get(nodo));
        }

        ArrayList<Integer> listaNodiTerminati = bfs.getNodesInOrderPostVisit(3);
        for (int nodo = 0; nodo < grafo.getOrder(); nodo++) {
            System.out.println(listaNodiTerminati.get(nodo));
        }

        ArrayList<Edge> camminoBreve = bfs.getShortestPath(3, 0);
        for (Edge edge : camminoBreve) {
            System.out.println(edge);
        }
    }
}
