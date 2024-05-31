import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Vincita atleta 2
        int[] atleta1 = new int[]{2, 3, 4, 5, 1, 7};
        int[] atleta2 = new int[]{2, 3, 4, 5, 1, 10};

        Atleta sceltaAtleta1 = new Atleta(6);
        System.out.println(sceltaAtleta1.scelta(atleta1, atleta2));

        // Vincita atleta 1
        int[] atleta3 = new int[]{2, 3, 4, 5, 1, 10};
        int[] atleta4 = new int[]{2, 3, 4, 5, 1, 7};

        Atleta sceltaAtleta2 = new Atleta(6);
        System.out.println(sceltaAtleta2.scelta(atleta3, atleta4));

        // Patta
        int[] atleta5 = new int[]{2, 3, 4, 5, 1, 7};
        int[] atleta6 = new int[]{2, 3, 4, 5, 1, 7};

        Atleta sceltaAtleta3 = new Atleta(6);
        System.out.println(sceltaAtleta3.scelta(atleta5, atleta6));

        // Con Exception, la catturiamo per non far crashare il programma
        try {
            Atleta sceltaAtleta4 = new Atleta(5);
            System.out.println(sceltaAtleta4.scelta(atleta5, atleta6));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        int[] atleta7 = new int[]{1, 30, 2};
        MSI msi = new MSI(atleta7);
        System.out.println(msi.getMaxVal());
        System.out.println(msi.getOptSol());
    }
}