package Window;

import Algorithm.*;
import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class MainWindow extends JPanel {

    private Algorithm algorithm;
    private GraphField graphField;
    ///////////////////////////////

    public MainWindow() {
        algorithm = new APD(new AdjacenyList(), new AdjacenyList());


        setBackground(Par_s.BACKGROUND);    //Установим цвет заднего фона

        add( createButtons() );
        graphField = new GraphField( (algorithm));
        add(graphField);


    }


    private JPanel createButtons() {
        JPanel panel = new JPanel( );
        Box box = Box.createVerticalBox();
        panel.setBackground(Par_s.BUTTENS_BORDER);

        box.add(createGraphButtons());
        box.add(createAlgorithmButtons());

        panel.add(box, BorderLayout.SOUTH);
        return panel;
    }


    // Создание панельки с кнопками, управляющими алгоритмом
    private JPanel createAlgorithmButtons() {
        JPanel algorithmButtons = new JPanel( new GridLayout(7, 1) );

        algorithmButtons.add(new JButton(new AbstractAction("Старт") {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAlgorithm();
            }
        }));
        algorithmButtons.add(Box.createVerticalStrut(5));
        algorithmButtons.add(new JButton(new AbstractAction("Следующий шаг") {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextStep();
            }
        }));
        algorithmButtons.add(Box.createVerticalStrut(5));
        algorithmButtons.add(new JButton(new AbstractAction("Результат") {
            @Override
            public void actionPerformed(ActionEvent e) {
                result();
            }
        }));
        algorithmButtons.add(Box.createVerticalStrut(5));
        algorithmButtons.add(new JButton(new AbstractAction("Очистка") {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        }));


        return algorithmButtons;
    }

    // Создание панельки с кнопками, управляющими созданием графа
    private JPanel createGraphButtons() {
        JPanel graphButtons = new JPanel();
        graphButtons.setBackground(Par_s.CREATE_BUTTONS_BG);

        graphButtons.add(createAddEdge());
        graphButtons.add(createAddVertex());

        graphButtons.setPreferredSize( Par_s.CREATE_GRAPH_PANEL_SIZE);

        return graphButtons;
    }

    // Создание панельки, отвечающей за добавление ребра
    private JPanel createAddEdge(){

        JTextField vertexName1 = new JTextField(); vertexName1.setPreferredSize( Par_s.SIZE_OF_INPUT_FIELD );
        JTextField vertexName2 = new JTextField(); vertexName2.setPreferredSize( Par_s.SIZE_OF_INPUT_FIELD );
        JTextField edgeWeight  = new JTextField();  edgeWeight.setPreferredSize( Par_s.SIZE_OF_INPUT_FIELD );

        JButton addEdjeButton = new JButton(new AbstractAction("Добавить ребро") {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEdge(vertexName1, vertexName2, edgeWeight);
            }
        });

        JPanel addEdge = new JPanel( new GridLayout(5,1) );

        addEdge.add( Box.createVerticalStrut(1));
        addEdge.add(addEdjeButton);
        addEdge.add( glueParametrs(new JLabel("Верш.1"),vertexName1 ) );
        addEdge.add( glueParametrs(new JLabel("Верш.2"),vertexName2 ) );
        addEdge.add( glueParametrs(new JLabel("Вес"   ), edgeWeight ) );

        return addEdge;
    }

    // Склейка окошшка для ввода и текста
    private JPanel glueParametrs(JLabel f1, JTextField f2) {
        JPanel parametr = new JPanel();
        parametr.add(f1);
        parametr.add(f2);
        return parametr;
    }

    //Создание кнопки, добавляющей в граф вершин
    private JButton createAddVertex() {
        return new JButton(new AbstractAction("Добавить вершину") {
            @Override
            public void actionPerformed(ActionEvent e) { addVertex(); }
        });
    }

    ///////////////////
    //////////////////
    // Функции кнопок
    private void startAlgorithm() {
        // Не стартуй алгоритм, когда он уже запущен
        if (algorithm.getStartFlag()) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: алгоритм уже запущен",
                    "Ошибка старта алгоритма",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int check = algorithm.getBase().getKolV();

        if (check < 2 ){
            JOptionPane.showMessageDialog(this,
                    "Ошибка: для запуска алгоритма нужен граф, состоящий " +
                            "минимум из 2-х вершин",
                    "Ошибка старта алгоритма",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i=0; i < check; i++) {
            if ( algorithm.getBase().kolEinV(i) == 0) {
                JOptionPane.showMessageDialog(this,
                        "Ошибка: у каждой вершины должно быть минимум по 1-му ребру",
                        "Ошибка старта алгоритма",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        algorithm.start();
        repaint();
    }

    private void nextStep() {
        if (!algorithm.getStartFlag()) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: Чтобы сделать шаг, запустите алгоритм",
                    "Ошибка алгоритма",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!algorithm.step() ) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: алгоритм уже закончил работу",
                    "Ошибка алгоритма",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        repaint();
    }
    private void result() {
        if (!algorithm.getStartFlag()) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: Чтобы получить результат работы алгоритма, сперва запустите алгоритм",
                    "Ошибка алгоритма",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                algorithm.result(),
                "Результат работы алгоритма",
                JOptionPane.ERROR_MESSAGE);

        repaint();
    }
    private void clearAll() {
        graphField.clear();
        repaint();
    }

    private void createEdge( JTextField vertexName1, JTextField vertexName2, JTextField edgeWeight) {
        if (algorithm.getStartFlag()) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: запрещено добавлять ребра пока алгоритм работает",
                    "Ошибка добавления ребра",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }


        Graph.Edge edge = new Graph.Edge(-1,-1,-1);
        // Считывание параметров добавляемого ребра
        try {
            edge.v1 = Integer.parseInt(vertexName1.getText());
            edge.v2 = Integer.parseInt(vertexName2.getText());
            edge.weight = Integer.parseInt(edgeWeight.getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: концы и вес ребра должны задаваться натуральными числами",
                    "Ошибка добавления ребра",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (edge.v1==edge.v2) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: нельзя создавать петли",
                    "Ошибка добавления ребра",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (  edge.weight <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка: Вес ребра должен задаваться натуральным числом",
                    "Ошибка добавления ребра",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        graphField.addE(edge);

    }

    private void addVertex(){
        graphField.addV();
        repaint();
    }
}
