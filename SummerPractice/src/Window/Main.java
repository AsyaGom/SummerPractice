package Window;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    ///////
    public static void main(String[] args) {
        new Main();
    }

    Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setTitle("Алгоритм Яника-Прима-Дейкстры");      //Имя окна
        setSize(941, 550);                 //Размер окна

        setBackground( new Color(10,10,10));
        add( new MainWindow(), BorderLayout.WEST );  // Размещение панельки с кнопками и изображением графа слева
        setBackground( new Color(10,10,10));


        setVisible(true);
    }
}
