package paint;

/**
 * Created by Demirion on 08.05.2015.
 */

import java.awt.*;
import java.io.Serializable;

public class Circ implements GraphObject, Serializable {
    private int x1, y1, x2, y2;
    private Color c;
    boolean fill;

    public Circ(int x1, int y1, int x2, int y2, Color c, boolean fill) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.c = c;
        this.fill = fill;
    }

    @Override
    public void paint(Graphics2D g2d, boolean tmp) {
        g2d.setPaint(c);

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

        if (fill == true) {
            if (x2 < 0 && y2 < 0) {
                g2d.fillOval(x1 + x2, y1 + y2, 0 - x2, 0 - y2);
            }
            if (x2 < 0 && y2 > 0) {
                g2d.fillOval(x1 + x2, y1, 0 - x2, 0 + y2);
            }
            if (x2 > 0 && y2 < 0) {
                g2d.fillOval(x1, y1 + y2, 0 + x2, 0 - y2);
            }
            if (x2 > 0 && y2 > 0) {
                g2d.fillOval(x1, y1, x2, y2);
            }
        }

        if (x2 < 0 && y2 < 0) {
            g2d.drawOval(x1 + x2, y1 + y2, 0 - x2, 0 - y2);
        }
        if (x2 < 0 && y2 > 0) {
            g2d.drawOval(x1 + x2, y1, 0 - x2, 0 + y2);
        }
        if (x2 > 0 && y2 < 0) {
            g2d.drawOval(x1, y1 + y2, 0 + x2, 0 - y2);
        }
        if (x2 > 0 && y2 > 0) {
            g2d.drawOval(x1, y1, x2, y2);
        }
    }

    public void setP2(int x, int y) {
        this.x2 = x - x1;
        this.y2 = y - y1;
    }
}
