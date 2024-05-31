package algo.gioeleferrari;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {
    @Test
    public void testCreate(){
        GraphInterface grafoTest = new DirectedGraph(3);
        BFS bfsTest = new BFS(grafoTest);
        assertNotNull(bfsTest);
    }

    @Test
    @Timeout(value = 500)
    public void testScoperti() {
        GraphInterface grafoTest = new DirectedGraph("3; 0 1; 1 2; 2 0");
        BFS bfsTest = new BFS(grafoTest);
        assertNotNull(bfsTest.getNodesInOrderVisit(0));
    }

    @Test
    public void testNumeroNodiVisitati() {
        GraphInterface grafoTest = new UndirectedGraph("4; 0 2; 0 1; 2 3; 1 3");
        BFS bfsTest = new BFS(grafoTest);
        assertEquals(4,bfsTest.getNodesInOrderVisit(2).size());
    }

    @Test
    public void testBFSOrder() {
        GraphInterface grafoTest = new UndirectedGraph("4; 0 2; 0 1; 2 3; 1 3");
        BFS bfsTest = new BFS(grafoTest);
        assertTrue(bfsTest.getNodesInOrderVisit(0).get(2) == 2 || bfsTest.getNodesInOrderVisit(0).get(2) == 1);
    }

    @Test
    public void testInitNumeroNodiVisitati() {
        GraphInterface grafoTest = new UndirectedGraph ("4; 0 2; 0 1; 2 3; 1 3");
        BFS bfsTest = new BFS(grafoTest);

        int numeroNodi = bfsTest.getNodesInOrderVisit(0).size();
        assertEquals(4, numeroNodi);

        numeroNodi = bfsTest.getNodesInOrderVisit(0).size();
        assertEquals(4, numeroNodi);
    }

    @Test
    public void testInitOrdineNodi() {
        GraphInterface grafoTest = new UndirectedGraph("4; 0 2; 0 1; 2 3; 1 3");
        BFS bfsTest = new BFS(grafoTest);

        assertTrue(bfsTest.getNodesInOrderVisit(2).get(2) != 1);
        assertTrue(bfsTest.getNodesInOrderVisit(1).get(2) != 2);
        assertTrue(bfsTest.getNodesInOrderVisit(0).get(2) != 2);
    }

    @Test
    public void testHasCycleUndirected() {
        GraphInterface grafoTest_1 = new UndirectedGraph("1;");
        GraphInterface grafoTest_2 = new UndirectedGraph("2; 0 1");
        GraphInterface grafoTest_3 = new UndirectedGraph("3; 0 1; 1 2; 2 0");
        GraphInterface grafoTest_4 = new UndirectedGraph("4; 1 0; 0 2; 2 3; 3 0");

        BFS bfsTest_1 = new BFS(grafoTest_1);
        BFS bfsTest_2 = new BFS(grafoTest_2);
        BFS bfsTest_3 = new BFS(grafoTest_3);
        BFS bfsTest_4 = new BFS(grafoTest_4);

        assertFalse(bfsTest_1.hasUndirectedCycle());
        assertFalse(bfsTest_2.hasUndirectedCycle());
        assertTrue(bfsTest_3.hasUndirectedCycle());
        assertTrue(bfsTest_4.hasUndirectedCycle());
    }

    @Test
    public void testHasCycleDirected() {
        GraphInterface grafoTest = new DirectedGraph("5; 0 4; 4 1; 4 2; 4 3; 2 3");
        BFS bfsTest = new BFS(grafoTest);

        assertTrue(bfsTest.hasDirCycle());
    }

    @Test
    public void testOrdineFineVisita() {
        GraphInterface grafoTest_1 = new UndirectedGraph("4; 3 2; 3 1; 2 0; 1 0");
        GraphInterface grafoTest_2 = new DirectedGraph("4; 3 2; 3 1; 2 0; 1 0");

        BFS bfsTest_1 = new BFS(grafoTest_1);
        BFS bfsTest_2 = new BFS(grafoTest_2);

        assertEquals(3, bfsTest_1.getNodesInOrderPostVisit(3).getFirst());
        assertEquals(3, bfsTest_2.getNodesInOrderPostVisit(3).getFirst());
    }
}