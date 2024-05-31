import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtletaTest {
    @Test
    public void testSceltaAtleta() {
        int[] atleta1 = new int[]{2, 3, 4, 5, 1, 7};
        int[] atleta2 = new int[]{2, 3, 4, 5, 1, 10};

        // Vincita atleta 2
        Atleta sceltaAtleta1 = new Atleta(6);
        assertEquals(2, sceltaAtleta1.scelta(atleta1, atleta2));

        // Vincita atleta 1
        int[] atleta3 = new int[]{2, 3, 4, 5, 1, 10};
        int[] atleta4 = new int[]{2, 3, 4, 5, 1, 7};

        Atleta sceltaAtleta2 = new Atleta(6);
        assertEquals(1, sceltaAtleta2.scelta(atleta3, atleta4));

        // Patta
        int[] atleta5 = new int[]{2, 3, 4, 5, 1, 7};
        int[] atleta6 = new int[]{2, 3, 4, 5, 1, 7};

        Atleta sceltaAtleta3 = new Atleta(6);
        System.out.println(sceltaAtleta3.scelta(atleta5, atleta6));
    }

}