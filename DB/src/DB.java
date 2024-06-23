import it.uniupo.algoTools.MaxHeap;

public class DB {
    private int[] dim;
    private double[] time;
    private double[] dosi;

    public DB(int[] dim, double[] time) {
        this.dim = dim;
        this.time = time;
        this.dosi = new double[dim.length];
    }

    private void zainoFrazionario(double memSpace) {
        MaxHeap<Integer, Double> maxHeap = new MaxHeap<>();

        for (int i = 0; i < this.dim.length; i++) {
            maxHeap.add(i, this.time[i] / this.dim[i]);
        }

        while (memSpace > 0 && !maxHeap.isEmpty()) {
            int m = maxHeap.extractMax();
            if (this.dim[m] <= memSpace) {
                this.dosi[m] = 1;
                memSpace -= this.dim[m];
            } else {
                this.dosi[m] = memSpace / this.dim[m];
                memSpace = 0;
            }
        }
    }

    public double timeToRebuild(int memSpace) {
        this.zainoFrazionario(memSpace);
        double result = 0;

        for (int i = 0; i < this.dim.length; i++) {
            result += (1 - this.dosi[i]) * this.time[i];
        }

        return result;
    }
}
