package Window;

import java.awt.*;

/**
 * Класс, хранящий в себе все параметры визуализатора
 */
public class Par_s {
    // Размеры полей
    final static Dimension WINDOW_SIZE = new Dimension(941, 550);
    final static Dimension CREATE_GRAPH_PANEL_SIZE = new Dimension(300,200);
    final static Dimension SIZE_OF_INPUT_FIELD = new Dimension(25,19);
    final static Dimension SIZE_OF_GRAPH_FIELD = new Dimension(600,500);

    // Цвета
    final static Color TEXT_COLOR = Color.BLACK;
    final static Color BUTTENS_BORDER = new Color(8, 99, 21);
    final static Color BACKGROUND = new Color(225, 219, 180);
    final static Color BASE_EDGE_COLOR = new Color(201, 199, 92);
    final static Color EDGE_LINE_COLOR = new Color(225, 219, 180);
    final static Color EDGE_CIRKLE_LINE_COLOR = new Color(0, 0, 0);
    final static Color CREATE_BUTTONS_BG = new Color(92, 140, 111);
    final static Color RESULT_EDGE_COLOR = new Color(177, 166, 204);
    final static Color BASE_VERTEX_COLOR = new Color(146, 210, 255);
    final static Color CIRCLE_BORDERLINE_COLOR = new Color(0, 0, 0);
    final static Color GRAPH_FIELD_BORDER = new Color(225, 219, 180);
    final static Color RESULT_VERTEX_COLOR = new Color(125, 168, 229);
    final static Color GRAPH_FIELD_BACKGROUND = new Color(36, 88, 36);

    // Целочисленные константы
    final static int VERTEX_R = 25;
    final static int TEXT_SIZE = 14;
    final static int EDJE_CIRKLE_R = 28;
    final static int VERTEX_D = VERTEX_R*2;

    // Размеры линий
    final static BasicStroke EDGE_LINE_THIKNESS = new BasicStroke(1);
    final static BasicStroke EDGE_CIRKLE_LINE_THKNESS = new BasicStroke(2);
}
