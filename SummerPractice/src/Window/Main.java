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


        setTitle("�������� �����-�����-��������");      //��� ����
        setSize(941, 550);                 //������ ����

        setBackground( new Color(10,10,10));
        add( new MainWindow(), BorderLayout.WEST );  // ���������� �������� � �������� � ������������ ����� �����
        setBackground( new Color(10,10,10));


        setVisible(true);
    }
}
