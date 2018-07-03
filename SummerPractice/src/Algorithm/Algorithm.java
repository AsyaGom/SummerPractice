package Algorithm;

import Graph.Graph;

public abstract class Algorithm {
    protected Graph base;          // исходный граф
    protected Graph result;        // Результирующий граф

    abstract public void GO();

    public Graph getBase() {
        return base;
    }
    public Graph getResult() {
        return result;
    }

}
