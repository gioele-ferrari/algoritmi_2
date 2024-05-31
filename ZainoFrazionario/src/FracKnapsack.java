import it.uniupo.algoTools.MaxHeap;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class FracKnapsack {
    private double capacita; // capacità dello zaino
    private double[] volume; // volume di ogni materiale
    private double[] valore; // valore dei materiali

    private double[] dose;
    private double[] quantita;

    public FracKnapsack(double capacita, double[] volume, double[] valore) {
        if (volume.length != valore.length) {
            throw new IllegalArgumentException("le due lunghezze differiscono!");
        }
        this.capacita = capacita;
        this.volume = volume;
        this.valore = valore;

        this.dose = new double[volume.length];
        this.quantita = new double[volume.length];
    }

    private double fracKnapsackImpl() {
        double valoreTotale = 0;
        double spazioRimasto = this.capacita;

        MaxHeap<Integer, Double> heap = new MaxHeap<>();

        Arrays.fill(this.dose, 0);
        Arrays.fill(this.quantita, 0);

        for (int i = 0; i < this.volume.length; i++) {
            heap.add(i, this.valore[i] / this.volume[i]);
        }

        while (spazioRimasto > 0 && !heap.isEmpty()) {
            int m = heap.extractMax();
            if (spazioRimasto >= this.volume[m]) {
                this.dose[m] = 1;
                this.quantita[m] = this.volume[m];
                valoreTotale += this.valore[m];
            }
            else {
                this.dose[m] = spazioRimasto / this.volume[m];
                this.quantita[m] = spazioRimasto;
                valoreTotale += (this.valore[m] * this.dose[m]);
            }
            spazioRimasto -= this.quantita[m];
        }

        return valoreTotale;
    }

    public double maxVal() {
        return this.fracKnapsackImpl();
    }

    public double maxValAppross() {
        double valoreTotaleAprross = 0;
        this.fracKnapsackImpl();
        for (int i = 0; i < this.quantita.length; i++) {
            if (this.dose(i) == 1.0) {
                valoreTotaleAprross += this.valore[i];
            }
        }
        return valoreTotaleAprross;
    }

    public double dose(int i) {
        this.fracKnapsackImpl();
        return this.dose[i];
    }

    public boolean more(int i, int j) {
        return this.quantita[i] > this.quantita[j];
    }
}

