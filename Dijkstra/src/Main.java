import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DirectedGraph grafo = new DirectedGraph("7; 0 2 1; 0 3 5; 2 3 3 2 4 7; 3 4 1; 3 1 1; 1 5 5; 4 1 3; 4 5 9; 5 6 8 ");
        Voli voli = new Voli(grafo);

        int tempoMinimo = voli.tempoMinimo(0, 4);
        System.out.println("Tempo minimo da 0 a 4: " + tempoMinimo);

        int scaloMinimo = voli.scali(0, 5);
        System.out.println("Scali da 0 a 4: " + scaloMinimo);

        int tempo = voli.tempo(0, 4);
        System.out.println("Tempo da 0 a 4: " + tempo);

        ArrayList<Edge> tratta = voli.trattaVeloce(0, 4);
        for (Edge e : tratta) {
            System.out.println(e);
        }

        ArrayList<Integer> elenco = voli.elencoScali(3, 6);
        for (Integer i : elenco) {
            System.out.println(i);
        }
    }
}
