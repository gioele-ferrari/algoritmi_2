import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class Main {
    public static void main(String[] args) {
        UndirectedGraph grafo = new UndirectedGraph("6; 4 1; 4 3; 1 3; 1 0; 3 2; 3 0; 0 5; 5 2; 0 2");
        InterNetwork network = new InterNetwork(grafo);
        System.out.println(network.numberOfHops(3,1));
        System.out.println(network.numberOfHops(5,4));
        System.out.println(network.numberOfHops(4,2));
        System.out.println(network.numberOfHops(1,1));
        System.out.println(network.numberOfHops(1,6));
        System.out.println(network.numberOfHops(7,-3));
    }
}
