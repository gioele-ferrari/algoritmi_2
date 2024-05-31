public class Main {
    public static void main(String[] args) {
        double[] vol = new double[]{4, 3, 4};
        double[] val = new double[]{5, 3, 1};
        int capacita = 10;
        FracKnapsack zaino = new FracKnapsack(capacita, vol, val);
        System.out.println(zaino.maxVal());
        for (int i = 0; i < vol.length; i++) {
            System.out.print(zaino.dose(i) + " ");
        }
        System.out.println("\n" + zaino.maxValAppross());
    }
}