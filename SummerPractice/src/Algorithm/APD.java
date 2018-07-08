package Algorithm;

import Graph.Graph;

import java.util.ArrayList;
import java.util.Map;

public class APD extends Algorithm {

    private int weight;
    private ArrayList<Integer> baseV;
    private ArrayList<Integer> resultV;

    public APD(Graph base, Graph result){
        this.base = base;
        this.result = result;
    }


    @Override
    public void start() {
        baseV = base.getVertexes();
        resultV = new ArrayList<Integer>();
        resultV.add(baseV.get(0));
        result.addV(baseV.get(0));

        startFlag = true;
        weight = 0;
    }

    @Override
    public boolean step() {

        if (base.getKolV() != result.getKolV()) {
            Graph.Edge minE = new Graph.Edge(0, 0, Integer.MAX_VALUE);

            for (int i : resultV) {
                Graph.Edge minE_i;// = base.getMinE(i);
                Graph.Vertex v_i = base.checkV(i);

                // Поиск минимального ребра от i-й вершины "во-вне"
                for(Map.Entry<Integer,Integer> j: v_i.way.entrySet()) {
                    if ( (result.checkV(j.getKey().intValue())==null) &&                //Если в рез. не содержится j вершина
                            ( j.getValue().intValue() < minE.weight )) {                // И путь меньше уже найденного
                        minE.v1 = i;
                        minE.v2 = j.getKey().intValue();
                        minE.weight = j.getValue().intValue();      // Тогда обновляем минимальное ребро
                    }
                }
            }
            result.addV(minE.v2);
            resultV.add(minE.v2);
            result.addE(minE);
            weight += minE.weight;

            return true;
        }
        return false;
    }

    @Override
    public String result() {
        while (step());
        return "Суммарный вес ребер МОДа = "+weight;
    }

    @Override
    public void clear() {
        if (startFlag) {
            startFlag = false;
            result.clear();
            baseV = null;
        } else {
            base.clear();
        }
    }

}
