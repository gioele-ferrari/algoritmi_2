import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FracKnapsackTest {
    @Test
    public void testCreate() {
        FracKnapsack zaino = new FracKnapsack(4, new double[]{1, 1, 1, 1}, new double[]{1, 2, 3, 4});
        assertNotNull(zaino);
    }

    @Test
    public void testZainoElemento() {
        FracKnapsack zaino = new FracKnapsack(7, new double[]{3}, new double[]{10});
        assertEquals(zaino.dose(0), 1);
        assertEquals(10, zaino.maxVal());
    }

}