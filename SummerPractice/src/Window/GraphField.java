package Window;

import Algorithm.Algorithm;
import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static Window.PAR_S.*;


public class GraphField extends JPanel {
    int maxV = 0;
    Algorithm algorithm;
    HashMap<Integer, ActiveVertex> points = new HashMap<Integer, ActiveVertex>();

    private int emptyPoint(){
        int i;
        for(i=0; points.containsKey(i); i++){};
        return i;
    }

    GraphField(Algorithm algorithm) {

        setLayout(null);
        setPreferredSize( SIZE_OF_GRAPH_FIELD );    //Размер рамки
        this.algorithm = algorithm;

        fitchaForFastCreateGraphFromFile();
    }


    public void addV() {

        if (algorithm.getStartFlag()) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: запрещено добавлять вершины пока алгоритм работает",
                    "Ошибка добавления вершины",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int n = emptyPoint();
        if (n > maxV){maxV = n;}
        algorithm.getBase().addV(n);

        points.put(n, new ActiveVertex(this, n));
        add(points.get(n));

    }
    public void addE(Graph.Edge edge){
        try {
            algorithm.getBase().addE(edge);
        }
        catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: " + exception.getLocalizedMessage(),
                    "Ошибка добавления ребра",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        repaint();
    }

    public void removeV(Graph.Vertex v) {

        if (algorithm.getBase().kolEinV(v.v) > 0) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: удаляемая вершина имеет инцидентные рёбра",
                    "Ошибка удаления вершины",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            algorithm.getBase().removeV(v);
            remove(points.get(v.v));
            points.remove(v.v);
        }
        catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: " + exception.getLocalizedMessage(),
                    "Ошибка удаления вершины",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        repaint();
    }
    public void removeE(Graph.Edge edge) {
        try {
            algorithm.getBase().removeE(edge);
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: " + exception.getLocalizedMessage(),
                    "Ошибка удаления ребра",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {

        g.setColor( GRAPH_FIELD_BACKGROUND );
        g.fillRect(0,0,100000,100000);

        drawGraph(g);

        g.setColor( GRAPH_FIELD_BORDER );                // Цвет рамки
        ((Graphics2D)g).setStroke(new BasicStroke(4));  // Толщина рамки
        g.drawRect( 0, 0,
                     SIZE_OF_GRAPH_FIELD.width,
                     SIZE_OF_GRAPH_FIELD.height);        // Нарисовать рамку

        g.drawString("KolV= " + algorithm.getBase().getKolV() , 10,15);
        g.drawString("KolE= " + algorithm.getBase().getKolE() , 10,25);
    }

    // Отрисовка графа
    private void drawGraph(Graphics g) {
        Graph.Edge edge;

        ActiveVertex i;
        int av = 0;
        for (int w=0; w < points.size(); w++) {

            for ( ; !points.containsKey(av); av++) {};
            i = points.get(av);
            av++;

            boolean inRes = algorithm.getResult().checkV(i.v)!=null ? true : false;

            for (int j = i.v; j < maxV+1; j++) {
                if ( ( edge = algorithm.getBase().checkE(i.v,j)) != null ) {
                    Color color;
                    if (inRes && (algorithm.getResult().checkE(i.v,j)!= null) ) color = RESULT_EDGE_COLOR;
                    else color = BASE_EDGE_COLOR;
                    drawEdge(g, edge, color);
                }
                g.setColor(inRes ? RESULT_VERTEX_COLOR : BASE_VERTEX_COLOR);
                drawVertex(g, i.v);
            }
        }
    }

    //Отрисовка ребра
    private void drawEdge(Graphics g, Graph.Edge edge, Color color){

        Point v1 = new Point(points.get(edge.v1).point.x, points.get(edge.v1).point.y);
        Point v2 = new Point(points.get(edge.v2).point.x, points.get(edge.v2).point.y);

        ((Graphics2D)g).setStroke( EDGE_LINE_THIKNESS );  // Устанавливаем толщину ребра

        g.setColor( EDGE_LINE_COLOR );
        g.drawLine(v1.x, v1.y, v2.x, v2.y);

        int x = (v1.x+v2.x)/2;
        int y = (v1.y+v2.y)/2;

        g.setColor(color);
        g.fillOval(x-14, y-14, EDJE_CIRKLE_R, EDJE_CIRKLE_R);

        ((Graphics2D)g).setStroke( EDGE_CIRKLE_LINE_THKNESS);
        g.setColor( EDGE_CIRKLE_LINE_COLOR );
        g.drawOval(x-14, y-14,  EDJE_CIRKLE_R, EDJE_CIRKLE_R);

        drawInt(g, x, y, edge.weight);
    }

    // Отрисовка вершины
    private void drawVertex(Graphics g, int v) {
        drawCircle(g, points.get(v).point.x,  points.get(v).point.y, VERTEX_R);
        drawInt(g, points.get(v).point.x, points.get(v).point.y, v);
    }

    // Пишет text в точку (x,y)
    private void drawInt(Graphics g, int x, int y, int text) {
        g.setColor(TEXT_COLOR);
        Font font = new Font("Default", Font.PLAIN, TEXT_SIZE);  //Шрифт

        g.setFont(font);

        FontMetrics fm = g.getFontMetrics(font);

        g.drawString(Integer.toString(text),
                x-fm.stringWidth(Integer.toString(text))/2,
                y+fm.getAscent()/2);
    }



    private void drawCircle(Graphics g, int cX, int cY, int rad) {
        g.fillOval(cX-rad, cY-rad, rad*2, rad*2);

        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.setColor( CIRCLE_BORDERLINE_COLOR );
        g.drawOval(cX-rad, cY-rad, rad*2, rad*2);
    }



    public void clear() {
        if (!algorithm.getStartFlag()) points = new HashMap<Integer, ActiveVertex>();
        algorithm.clear();
    }


    //Фича: найдя файл начнёт строить граф из него
    private void fitchaForFastCreateGraphFromFile()  {
        try (FileInputStream file = new FileInputStream("FastGraph.txt");) {
            Scanner scanner = new Scanner(file);

            int kolV = scanner.nextInt();

            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

            for (int i=0; i < kolV; i++) {
                map.put( scanner.nextInt(), i);
                addV();
            }

            while (scanner.hasNext()) {
                int v1 = scanner.nextInt();
                int v2 = scanner.nextInt();
                int weight = scanner.nextInt();

                addE(new Graph.Edge(map.get(v1).intValue(), map.get(v2).intValue(), weight));
            }
        } catch (IOException e) { }
    }
}
