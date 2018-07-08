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
        setSize(Par_s.WINDOW_SIZE);                            //Размер окна

        setResizable(false);

        add( new MainWindow(), BorderLayout.WEST );  // Размещение панельки с кнопками и изображением графа слева

        setVisible(true);
    }
}
