package algo.gioeleferrari;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GraphInterface grafo = new DirectedGraph("3; 0 2; 2 1; 1 2");
        BFS bfs = new BFS(grafo);
        System.out.println(bfs.hasDirCycle());
    }
}
