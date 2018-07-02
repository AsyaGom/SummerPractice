package Window;

import Algorithm.Algorithm;
import Graph.Graph;

import javax.swing.*;

public class MainWindow extends JPanel {

    private Graph graph;
    private Algorithm algorithm;
    ///////////////////////////////

    public MainWindow() {
        add( createButtons() );
    }

    private JPanel createButtons() {
        JPanel panel = new JPanel(  );

        return panel;
    }
}