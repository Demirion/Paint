package paint;

import java.awt.*;
import java.io.Serializable;

public class Line implements GraphObject, Serializable {
    private int x1, y1, x2, y2;
    private Color c;

    public Line(int x1, int y1, int x2, int y2, Color c) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.c = c;
    }

    @Override
    public void paint(Graphics2D g2d, boolean tmp) {

        if (tmp)
        {
            Stroke s = new BasicStroke(
                    1.0f,                      // Width
                    BasicStroke.CAP_SQUARE,    // End cap
                    BasicStroke.JOIN_MITER,    // Join style
                    10.0f,                     // Miter limit
                    new float[]{1.0f, 3.0f}, // Dash pattern
                    0.0f);                     // Dash phase

            g2d.setStroke(s);
        }

        g2d.setColor(c);
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void setP2(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }
}
