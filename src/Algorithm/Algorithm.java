package Algorithm;

import Graph.Graph;

public abstract class Algorithm {
    protected boolean startFlag = false;
    protected Graph base;          // Исходный граф
    protected Graph result;        // Результирующий граф

    abstract public void start();
    abstract public boolean step();
    abstract public String result();

    abstract public void clear();

    public Graph getBase()     {
        return base;
    }
    public Graph getResult() {
        return result;
    }


    public boolean getStartFlag() {return startFlag;}

}
