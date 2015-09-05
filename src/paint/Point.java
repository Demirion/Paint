package paint;

/**
 * Created by Demirion on 08.05.2015.
 */

import java.awt.*;
import java.io.Serializable;

public class Point implements GraphObject, Serializable {
    private int x1, y1, x2, y2;
    private Color c;

    public Point(int x1, int y1, int x2, int y2, Color c) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.c = c;
    }

    @Override
    public void paint(Graphics2D g2d, boolean tmp) {
        g2d.setColor(c);
        g2d.fillOval(x2-3, y2-3, 3, 3);
    }

    public void setP2(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }
}