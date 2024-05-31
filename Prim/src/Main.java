import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class Main {
    public static void main(String[] args) {
        UndirectedGraph grafo = new UndirectedGraph("5; 0 1 1; 0 2 2; 1 3 5; 2 4 7; 3 4 4; 2 3 6");
        GraphInterface alberoMST;

        Prim algoritmoPrim = new Prim(grafo);

        alberoMST = algoritmoPrim.getMST();
        for (int nodo = 0; nodo < alberoMST.getOrder(); nodo++) {
            for (Edge e : alberoMST.getOutEdges(nodo)) {
                System.out.print(e);
            }
            System.out.println();
        }

        System.out.println(algoritmoPrim.getCost());
    }
}