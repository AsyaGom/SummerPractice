package Window;

import Algorithm.Algorithm;
import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class GraphField extends JPanel {
    int VSize = 50;
    Algorithm algorithm;
    ArrayList<Point> points = new ArrayList<Point>();

    GraphField(Algorithm algorithm) {
        setPreferredSize( new Dimension(600,500));    //Размер рамки
        this.algorithm = algorithm;
    }

	
    public void addV() {
        Random random = new Random();

        points.add(new Point(random.nextInt(600-VSize), random.nextInt(500-VSize)));

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

    }

    // Отрисовка графа
    private void drawGraph(Graphics g) {
        Graph.Edge edge;
        for (int i=0; i<algorithm.getBase().getKolV(); i++) {
            for(int j=i; j < algorithm.getBase().getKolV(); j++){
                if ( ( edge = algorithm.getBase().checkE(i,j)) != null ) {
                    drawEdge( g, edge);
                }
                drawVertex(g, i);
            }
        }
    }

    //Отрисовка ребра
    private void drawEdge(Graphics g, Graph.Edge edge){
        Color color = new Color(1,1,1);

        Point v1 = new Point(points.get(edge.v1).x+VSize/2, points.get(edge.v1).y+VSize/2);
        Point v2 = new Point(points.get(edge.v2).x+VSize/2, points.get(edge.v2).y+VSize/2);

        ((Graphics2D)g).setStroke(new BasicStroke(1));  // Устанавливаем толщину ребра

        g.setColor( color );
        g.drawLine(v1.x, v1.y, v2.x, v2.y);

        int x = (v1.x+v2.x)/2;
        int y = (v1.y+v2.y)/2;

        g.setColor(new Color(110, 214, 82));
        g.fillOval(x-14, y-14, 28,28);

        drawInt(g, x, y, edge.weight);
    }

    // Отрисовка вершины
    private void drawVertex(Graphics g, int v) {
        g.setColor(new Color(61, 61, 204));
        g.drawOval(points.get(v).x, points.get(v).y, VSize, VSize);

        drawInt(g, points.get(v).x+VSize/2, points.get(v).y+VSize/2, v);
    }

    // Пишет text в точку (x,y)
    private void drawInt(Graphics g, int x, int y, int text) {
        g.setColor(Color.BLACK);
        Font font = new Font("Default", Font.PLAIN, 14);  //Шрифт
        g.setFont(font);
        g.drawString(Integer.toString(text), x, y);
    }
}
