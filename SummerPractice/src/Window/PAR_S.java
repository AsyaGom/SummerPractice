package Window;

import java.awt.*;

/**
 * Класс, хранящий в себе все параметры визуализатора
 */
public class PAR_S {
    // Размеры полей
    final static Dimension WINDOW_SIZE = new Dimension(941, 550);
    final static Dimension CREATE_GRAPH_PANEL_SIZE = new Dimension(300,200);
    final static Dimension SIZE_OF_INPUT_FIELD = new Dimension(25,19);
    final static Dimension SIZE_OF_GRAPH_FIELD = new Dimension(600,500);

    // Цвета
    final static Color TEXT_COLOR = Color.BLACK;
    final static Color BUTTENS_BORDER = new Color(16, 77, 80);
    final static Color BACKGROUND = new Color(225, 219, 180);
    final static Color BASE_EDGE_COLOR = new Color(241, 237, 226);
    final static Color EDGE_LINE_COLOR = new Color(225, 219, 180);
    final static Color EDGE_CIRKLE_LINE_COLOR = new Color(0, 0, 0);
    final static Color CREATE_BUTTONS_BG = new Color(127, 186, 189);
    final static Color RESULT_EDGE_COLOR = new Color(203, 138, 82);
    final static Color BASE_VERTEX_COLOR = new Color(201, 158, 123);
    final static Color CIRCLE_BORDERLINE_COLOR = new Color(0, 0, 0);
    final static Color GRAPH_FIELD_BORDER = new Color(225, 219, 180);
    final static Color RESULT_VERTEX_COLOR = new Color(163, 98, 80);
    final static Color GRAPH_FIELD_BACKGROUND = new Color(50, 119, 123);

    // Целочисленные константы
    final static int VERTEX_R = 25;
    final static int TEXT_SIZE = 14;
    final static int EDJE_CIRKLE_R = 28;
    final static int VERTEX_D = VERTEX_R*2;

    // Размеры линий
    final static BasicStroke EDGE_LINE_THIKNESS = new BasicStroke(1);
    final static BasicStroke EDGE_CIRKLE_LINE_THKNESS = new BasicStroke(2);
}
