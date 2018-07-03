package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Граф, представляемый списком смежности
 */
public class AdjacenyList extends Graph {



    private HashMap<Integer, HashMap<Integer, Integer>> adjacenyList = new HashMap<Integer, HashMap<Integer, Integer>>();

    @Override
    public boolean addV() {
        return true;
    }

    @Override
    protected boolean addV(int v) {
        return false;
    }

    @Override
    public boolean addE(Edge e) {
        return false;
    }

    @Override
    public boolean checkV(int v) {
        return adjacenyList.containsKey(v);
    }

    @Override
    public boolean checkE(int v1, int v2) {
        return false;
    }

    @Override
    public Edge getMinE(int v) {
        return new Edge(v, v, 0);
    }
}

