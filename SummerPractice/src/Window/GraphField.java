package Window;

import Algorithm.Algorithm;
import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class GraphField extends JPanel {
    int VSize = 50;
    Algorithm algorithm;
    ArrayList<Point> points = new ArrayList<Point>();

    GraphField(Algorithm algorithm) {
        setPreferredSize( new Dimension(600,500));    //Размер рамки
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

        Random random = new Random();

        points.add(new Point(random.nextInt(600-VSize), random.nextInt(500-VSize)));

        //add(new ActiveVertex(points.get(points.size()-1)));
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

        g.setColor( new Color(255, 68, 67));
        g.fillRect(0,0,1000,1000);

        drawGraph(g);

        g.setColor( new Color(0, 0, 0));             // Цвет рамки
        ((Graphics2D)g).setStroke(new BasicStroke(4));  // Толщина рамки
        g.drawRect( 0, 0, 600, 500);        // Нарисовать рамку

        g.drawString("KolV= " + algorithm.getBase().getKolV() , 10,15);
        g.drawString("KolE= " + algorithm.getBase().getKolE() , 10,25);

        //repaint();
    }

    // Отрисовка графа
    private void drawGraph(Graphics g) {
        Graph.Edge edge;

        Color baseV = new Color(81, 115, 204);
        Color resultV = new Color(37, 204, 8);

        Color baseE = new Color(110, 214, 82);
        Color resultE = new Color(177, 166, 204);

        for (int i=0; i<points.size(); i++) {

            boolean inRes = algorithm.getResult().checkV(i)!=null ? true : false;

            for(int j=i; j < points.size(); j++){
                if ( ( edge = algorithm.getBase().checkE(i,j)) != null ) {
                    Color color;
                    if (inRes && (algorithm.getResult().checkE(i,j)!= null) ) color = resultE;
                    else color = baseE;
                    drawEdge(g, edge, color);
                }
                g.setColor(inRes ? resultV : baseV);
                drawVertex(g, i);
            }
        }
    }

    //Отрисовка ребра
    private void drawEdge(Graphics g, Graph.Edge edge, Color color){

        Point v1 = new Point(points.get(edge.v1).x+VSize/2, points.get(edge.v1).y+VSize/2);
        Point v2 = new Point(points.get(edge.v2).x+VSize/2, points.get(edge.v2).y+VSize/2);

        ((Graphics2D)g).setStroke(new BasicStroke(1));  // Устанавливаем толщину ребра

        g.setColor( new Color(1,1,1) );
        g.drawLine(v1.x, v1.y, v2.x, v2.y);

        int x = (v1.x+v2.x)/2;
        int y = (v1.y+v2.y)/2;

        g.setColor(color);
        g.fillOval(x-14, y-14, 28,28);

        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.setColor(new Color(0, 0, 0));//g.setColor(new Color(5, 89, 0));
        g.drawOval(x-14, y-14, 28,28);

        drawInt(g, x, y, edge.weight);
    }

    // Отрисовка вершины
    private void drawVertex(Graphics g, int v) {
        drawCircle(g, points.get(v).x+VSize/2,  points.get(v).y+VSize/2, VSize/2);
        drawInt(g, points.get(v).x+VSize/2, points.get(v).y+VSize/2, v);
    }

    // Пишет text в точку (x,y)
    private void drawInt(Graphics g, int x, int y, int text) {
        g.setColor(Color.BLACK);
        Font font = new Font("Default", Font.PLAIN, 14);  //Шрифт

        g.setFont(font);

        FontMetrics fm = g.getFontMetrics(font);

        g.drawString(Integer.toString(text),
                x-fm.stringWidth(Integer.toString(text))/2,
                y+fm.getAscent()/2);
    }



    private void drawCircle(Graphics g, int cX, int cY, int rad) {
        g.fillOval(cX-rad, cY-rad, rad*2, rad*2);

        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.setColor(new Color(0, 0, 0));
        g.drawOval(cX-rad, cY-rad, rad*2, rad*2);
    }



    public void clear() {
        if (!algorithm.getStartFlag()) points = new ArrayList<Point>();
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
