package gioele.ferrari;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;

public class Main {
    public static void main(String[] args) {
        GraphInterface grafo = new DirectedGraph("5; 0 1; 2 1; 2 0; 2 3; 3 4; 4 2");
        SoftwareSystem dfs = new SoftwareSystem(grafo);
        System.out.println(dfs.hasCycle());
    }
}
