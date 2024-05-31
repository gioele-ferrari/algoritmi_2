public class Main {
    public static void main(String[] args) {
        int capacita = 3;
        int[] val = new int[]{10, 9, 1};
        int[] vol = new int[]{1, 3, 2};

        Zaino zaino = new Zaino(val, vol, capacita);
        System.out.println(zaino.getMaxVal());
    }
}