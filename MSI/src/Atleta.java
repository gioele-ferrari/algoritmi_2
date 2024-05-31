public class Atleta {
    public int[] discipline;
    public Atleta(int numeroDiscipline) {
        this.discipline = new int[numeroDiscipline];
    }

    public int scelta(int[] rendAtleta1, int[] rendAtleta2) {
        if (rendAtleta1.length > this.discipline.length || rendAtleta2.length > this.discipline.length) {
            throw new IllegalArgumentException("troppe discipline rispetto a quelle impostate");
        }
        MSI atleta1 = new MSI(rendAtleta1);
        MSI atleta2 = new MSI(rendAtleta2);

        if (atleta1.getMaxVal() == atleta2.getMaxVal()) return 0;

        return atleta1.getMaxVal() > atleta2.getMaxVal() ? 1 : 2;
    }
}
