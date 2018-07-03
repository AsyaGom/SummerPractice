package Graph;

/**
 * Абстрактный Граф
 */
public abstract class Graph {

    int kolV = 0;
    int kolE = 0;

    abstract protected boolean addV();
    abstract protected boolean addV(int v);
    abstract public boolean addE(Edge e);
    abstract public boolean checkV(int v);
    abstract public boolean checkE(int v1, int v2);
    abstract public Edge getMinE(int v);


    public int getKolE() {return kolE;}
    public int getKolV() {return kolV;}

    public static class Edge {
        public Edge (int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        public int v1;
        public int v2;
        public int weight;
    }
}
