public class Zaino {
    private int[] value;
    private int[] volume;
    private int capacita;
    private int[][] Val;

    public Zaino(int[] value, int[] volume, int capacita) {
        this.value = value;
        this.volume = volume;
        this.capacita = capacita;
        this.Val = new int[value.length + 1][capacita + 1];
    }

    public void zainoImpl() {
        for (int cap = 0; cap <= this.capacita; cap++) {
            this.Val[0][cap] = 0; // Stiamo riempiendo la prima riga di 0
        }

        // Per ogni oggetto
        for (int numOgg = 1; numOgg <= this.value.length; numOgg++) {
            // Prendiamo il suo id
            int idOgg = numOgg - 1;
            // Per ogni capacità
            for (int cap = 0; cap <= this.capacita; cap++) {
                // Svogliamo il controllo
                if (this.volume[idOgg] <= cap) {
                    // Sceglie la soluzione più vantaggiosa
                    this.Val[numOgg][cap] =
                            Math.max(
                                this.Val[numOgg - 1][cap-this.volume[idOgg]] + this.value[idOgg],
                                this.Val[numOgg - 1][cap]);
                }
                else {
                    this.Val[numOgg][cap] = this.Val[numOgg - 1][cap];
                }
            }
        }
    }

    public int getMaxVal() {
        this.zainoImpl();
        return this.Val[this.value.length][this.capacita];
    }
}
