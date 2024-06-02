import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GraphInterface grafo = new DirectedGraph("4; 3 1; 1 2; 0 2; 1 0; 3 0; 2 0");
        Kosaraju kosaraju = new Kosaraju(grafo);
        System.out.println(kosaraju.hasCycleImpl());
        System.out.println(kosaraju.topologicalOrder());
    }
}