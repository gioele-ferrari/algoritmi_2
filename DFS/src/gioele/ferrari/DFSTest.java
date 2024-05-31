package gioele.ferrari;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class DFSTest {
    @Test
    void testCreate() {
        GraphInterface grafo = new DirectedGraph(3);
        DFS dfsTest = new DFS(grafo);
        assertNotNull(dfsTest);
    }

    @Test
    @Timeout(value = 500)
    void testScoperti() {
        GraphInterface grafo = new DirectedGraph ("3;0 1;1 2;2 0");
        DFS dfsTest = new DFS(grafo);
        assertNotNull(dfsTest.getAlbero(0));
    }

    @Test
    void testNumeroArchi() {
        GraphInterface grafo = new UndirectedGraph("4; 0 1; 0 2; 2 3; 1 3");
        DFS dfsTest = new DFS(grafo);
        assertEquals(3, dfsTest.getAlbero(0).getEdgeNum());
    }

    @Test
    void testArchiDFS() {
        GraphInterface grafo = new UndirectedGraph("4; 0 1; 0 2; 2 3; 1 3");
        DFS dfsTest = new DFS(grafo);
        assertFalse(dfsTest.getAlbero(2).hasEdge(2, 3));
        assertTrue(dfsTest.getAlbero(2).hasEdge(1, 3));
    }

    @Test
    void testInitAlbero() {
        GraphInterface grafo = new UndirectedGraph("4; 0 1; 0 2; 2 3; 1 3");
        DFS dfsTest = new DFS(grafo);
        assertEquals(3, dfsTest.getAlbero(2).getEdgeNum());
        assertEquals(3, dfsTest.getAlbero(1).getEdgeNum());
    }

    @Test
    public void testEccezione() {
        GraphInterface grafo = new UndirectedGraph("4; 0 1; 0 2; 2 3; 1 3");
        DFS dfsTest = new DFS(grafo);
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            dfsTest.getAlbero(-3);
        });
    }
}