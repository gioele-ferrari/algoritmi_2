import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MSITest {
    @Test
    public void testMaxVal() {
        MSI msi = new MSI(new int[]{2, 3, 4, 5, 1, 7});
        assertEquals(15, msi.getMaxVal());
        MSI msi2 = new MSI(new int[]{1});
        assertEquals(1, msi2.getMaxVal());
    }

    @Test
    public void testOptSol() {
        List<Integer> listOptSol1 = Arrays.asList(1, 3, 5);
        MSI msi = new MSI(new int[]{2, 3, 4, 5, 1, 7});
        assertEquals(listOptSol1, msi.getOptSol());

        List<Integer> listOptSol2 = List.of(0);
        MSI msi2 = new MSI(new int[]{1});
        assertEquals(listOptSol2, msi2.getOptSol());
    }
}