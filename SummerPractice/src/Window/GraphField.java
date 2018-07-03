package Window;

import Algorithm.Algorithm;

import javax.swing.*;
import java.awt.*;

public class GraphField extends JPanel {
    Algorithm algorithm;

    GraphField(Algorithm algorithm) {
        setPreferredSize( new Dimension(600,500));    //Размер рамки
        this.algorithm = algorithm;
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor( new Color(255, 68, 67));
        g.fillRect(0,0,1000,1000);

        g.setColor( new Color(0, 0, 0));             // Цвет рамки
        ((Graphics2D)g).setStroke(new BasicStroke(4));  // Толщина рамки
        g.drawRect( 0, 0, 600, 500);      // Нарисовать рамку
    }
}
