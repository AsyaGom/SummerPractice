package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class ActiveVertex extends JPanel implements MouseListener, MouseMotionListener {

    JPanel parent;
    Point point;
    final int v;

    private Point mouse = new Point();

    private boolean flagCanMove = false;


    ActiveVertex( JPanel parent, int v ) {
        this.parent = parent;
        this.v = v;

        Random random = new Random();
        point = new Point(random.nextInt(600- Par_s.VERTEX_D)+ Par_s.VERTEX_R,
                          random.nextInt(500- Par_s.VERTEX_D)+ Par_s.VERTEX_R);

        setSize(new Dimension(Par_s.VERTEX_D, Par_s.VERTEX_D));
        setLocation(point.x- Par_s.VERTEX_R, point.y- Par_s.VERTEX_R);

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

            int x = point.x + dx;
            if (x- Par_s.VERTEX_R < 0) point.x = Par_s.VERTEX_R;
            else if (x+ Par_s.VERTEX_R>600) point.x = 600- Par_s.VERTEX_R;
                    else point.x = x;

            int y = point.y + dy;
            if (y- Par_s.VERTEX_R < 0) point.y = Par_s.VERTEX_R;
            else if (y+ Par_s.VERTEX_R>500) point.y = 500- Par_s.VERTEX_R;
                    else point.y = y;

            setLocation(point.x- Par_s.VERTEX_R, point.y- Par_s.VERTEX_R);
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
