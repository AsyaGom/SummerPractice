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
    public boolean addV(int v) {
        if (adjacenyList.containsKey(v)) throw new RuntimeException("Такая вершина уже есть");

        adjacenyList.put(v, new HashMap<Integer, Integer>());
        kolV++;
        return true;
    }

    @Override
    public boolean addE(Edge e) {

        if ( !adjacenyList.containsKey(e.v1) ) throw new RuntimeException("Вершина "+e.v1+" не существует");
        if ( !adjacenyList.containsKey(e.v2) ) throw new RuntimeException("Вершина "+e.v2+" не существует");
        if ( adjacenyList.get(e.v1).containsKey(e.v2)) throw new RuntimeException("Данное ребро уже существует");

        adjacenyList.get(e.v1).put(e.v2, e.weight);
        adjacenyList.get(e.v2).put(e.v1, e.weight);

        kolE++;
        return true;
    }

    @Override
    public boolean checkV(int v) {
        return adjacenyList.containsKey(v);
    }

	public Edge checkE(int v1, int v2) {
        if (checkV(v1) && checkV(v2)) {
            Integer i = adjacenyList.get(v1).get(v2);

            return i==null ? null : new Edge(v1,v2,i.intValue());
        }
        return null;
    }
	
    @Override
    public Edge getMinE(int v) {
        int minW = Integer.MAX_VALUE;
        int v2 = v;

        for(Map.Entry<Integer,Integer> vertex: adjacenyList.get(v).entrySet() ) {
            if (vertex.getValue().intValue() < minW) {
                minW = vertex.getValue().intValue();
                v2 = vertex.getKey().intValue();
            }
        }
        return new Edge(v, v2, minW);
    }
}
