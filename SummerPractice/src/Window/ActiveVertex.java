package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class ActiveVertex extends JPanel implements MouseListener, MouseMotionListener {

    JPanel parent;
    final int VSIZE = 50;
    Point point;
    final int v;

    private Point mouse = new Point();

    private boolean flagCanMove = false;


    ActiveVertex( JPanel parent, int v ) {
        this.parent = parent;
        this.v = v;

        Random random = new Random();
        point = new Point(random.nextInt(600-VSIZE), random.nextInt(500-VSIZE));


        setSize(new Dimension(VSIZE,VSIZE));
        setLocation(point.x-VSIZE/2, point.y-VSIZE/2);
        setBackground(new Color(0xFFFFFF));

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    // Движение вершины
    @Override
    public void mousePressed(MouseEvent e) {
        flagCanMove = true;
        mouse.x = e.getX();
        mouse.y = e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        flagCanMove = false;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (flagCanMove) {
            int dx = e.getX() - mouse.x;
            int dy = e.getY() - mouse.y;

            point.x += dx;
            point.y += dy;

            setLocation(point.x-VSIZE/2, point.y-VSIZE/2);
            parent.repaint();
        }
    }


    ///////////////////////////////////////////
    ///////////////////////////////////////////
    ///////////////////////////////////////////
    ///////////////////////////////////////////
    // Мусорка безполезных функций
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
