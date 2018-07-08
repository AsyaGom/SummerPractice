package Window;

import Algorithm.Algorithm;
import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class GraphField extends JPanel {
    Algorithm algorithm;
    ArrayList<ActiveVertex> points = new ArrayList<ActiveVertex>();

    GraphField(Algorithm algorithm) {

        setLayout(null);
        setPreferredSize( Par_s.SIZE_OF_GRAPH_FIELD);    //Размер рамки
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

        algorithm.getBase().addV(algorithm.getBase().getKolV());
        points.add( new ActiveVertex(this, points.size()));

        add(points.get(points.size()-1));
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


    @Override
    public void paint(Graphics g) {

        g.setColor( Par_s.GRAPH_FIELD_BACKGROUND);
        g.fillRect(0,0,100000,100000);

        drawGraph(g);

        g.setColor( Par_s.GRAPH_FIELD_BORDER);                // Цвет рамки
        ((Graphics2D)g).setStroke(new BasicStroke(4));  // Толщина рамки
        g.drawRect( 0, 0,
                     Par_s.SIZE_OF_GRAPH_FIELD.width,
                     Par_s.SIZE_OF_GRAPH_FIELD.height);        // Нарисовать рамку

        g.drawString("KolV= " + algorithm.getBase().getKolV() , 10,15);
        g.drawString("KolE= " + algorithm.getBase().getKolE() , 10,25);
    }

    // Отрисовка графа
    private void drawGraph(Graphics g) {
        Graph.Edge edge;

        for (ActiveVertex i: points) {

            boolean inRes = algorithm.getResult().checkV(i.v)!=null ? true : false;

            for(int j=i.v; j < points.size(); j++){
                if ( ( edge = algorithm.getBase().checkE(i.v,j)) != null ) {
                    Color color;
                    if (inRes && (algorithm.getResult().checkE(i.v,j)!= null) ) color = Par_s.RESULT_EDGE_COLOR;
                    else color = Par_s.BASE_EDGE_COLOR;
                    drawEdge(g, edge, color);
                }
                g.setColor(inRes ? Par_s.RESULT_VERTEX_COLOR : Par_s.BASE_VERTEX_COLOR);
                drawVertex(g, i.v);
            }
        }
    }

    //Отрисовка ребра
    private void drawEdge(Graphics g, Graph.Edge edge, Color color){

        Point v1 = new Point(points.get(edge.v1).point.x, points.get(edge.v1).point.y);
        Point v2 = new Point(points.get(edge.v2).point.x, points.get(edge.v2).point.y);

        ((Graphics2D)g).setStroke(Par_s.EDGE_LINE_THIKNESS);  // Устанавливаем толщину ребра

        g.setColor( Par_s.EDGE_LINE_COLOR );
        g.drawLine(v1.x, v1.y, v2.x, v2.y);

        int x = (v1.x+v2.x)/2;
        int y = (v1.y+v2.y)/2;

        g.setColor(color);
        g.fillOval(x-14, y-14, Par_s.EDJE_CIRKLE_R,Par_s.EDJE_CIRKLE_R);

        ((Graphics2D)g).setStroke(Par_s.EDGE_CIRKLE_LINE_THKNESS);
        g.setColor(Par_s.EDGE_CIRKLE_LINE_COLOR);
        g.drawOval(x-14, y-14, Par_s.EDJE_CIRKLE_R,Par_s.EDJE_CIRKLE_R);

        drawInt(g, x, y, edge.weight);
    }

    // Отрисовка вершины
    private void drawVertex(Graphics g, int v) {
        drawCircle(g, points.get(v).point.x,  points.get(v).point.y, Par_s.VERTEX_R);
        drawInt(g, points.get(v).point.x, points.get(v).point.y, v);
    }

    // Пишет text в точку (x,y)
    private void drawInt(Graphics g, int x, int y, int text) {
        g.setColor(Par_s.TEXT_COLOR);
        Font font = new Font("Default", Font.PLAIN, Par_s.TEXT_SIZE);  //Шрифт

        g.setFont(font);

        FontMetrics fm = g.getFontMetrics(font);

        g.drawString(Integer.toString(text),
                x-fm.stringWidth(Integer.toString(text))/2,
                y+fm.getAscent()/2);
    }



    private void drawCircle(Graphics g, int cX, int cY, int rad) {
        g.fillOval(cX-rad, cY-rad, rad*2, rad*2);

        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.setColor(Par_s.CIRCLE_BORDERLINE_COLOR);
        g.drawOval(cX-rad, cY-rad, rad*2, rad*2);
    }



    public void clear() {
        if (!algorithm.getStartFlag()) points = new ArrayList<ActiveVertex>();
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
