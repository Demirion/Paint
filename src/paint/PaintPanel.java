package paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class PaintPanel extends JPanel {
    private int mode;
    private Color drawColor, fillColor;
    private boolean fillMode;
    static ArrayList<GraphObject> list = new ArrayList<GraphObject>();
    Point point = null;
    Line line = null;
    Rect rect = null;
    Rect rect2 = null;
    Circ circ = null;
    Circ circ2 = null;

    public PaintPanel() {
        this.setBackground(Color.LIGHT_GRAY);
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mpressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mreleased(e);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent arg0) {
                mdragged(arg0);
            }
        });
    }

    protected void mpressed(MouseEvent e) {
        drawColor = MainFrame.color;
        fillColor = MainFrame.fColor;
        fillMode = MainFrame.fMode;

        if (MainFrame.point.isSelected()) {
            point = new Point(e.getX(), e.getY(), e.getX(), e.getY(), drawColor);
        }

        if (MainFrame.line.isSelected()) {
            line = new Line(e.getX(), e.getY(), e.getX(), e.getY(), drawColor);
        }

        if (MainFrame.rect.isSelected()) {
            if (fillMode == true) {
                rect = new Rect(e.getX(), e.getY(), e.getX(), e.getY(), fillColor, fillMode);
                fillMode = false;
                rect2 = new Rect(e.getX(), e.getY(), e.getX(), e.getY(), drawColor, fillMode);
                fillMode = true;
            } else rect = new Rect(e.getX(), e.getY(), e.getX(), e.getY(), drawColor, fillMode);
        }

        if (MainFrame.circ.isSelected()) {
            if (fillMode == true) {
                circ = new Circ(e.getX(), e.getY(), e.getX(), e.getY(), fillColor, fillMode);
                fillMode = false;
                circ2 = new Circ(e.getX(), e.getY(), e.getX(), e.getY(), drawColor, fillMode);
                fillMode = true;
            } else circ = new Circ(e.getX(), e.getY(), e.getX(), e.getY(), drawColor, fillMode);
        }
        repaint();
    }

    protected void mdragged(MouseEvent arg0) {

        if (MainFrame.point.isSelected()) {
            point.setP2(arg0.getX(), arg0.getY());
        }

        if (MainFrame.line.isSelected()) {
            line.setP2(arg0.getX(), arg0.getY());
        }

        if (MainFrame.rect.isSelected()) {
            if (fillMode == true) {
                rect.setP2(arg0.getX(), arg0.getY());
                rect2.setP2(arg0.getX(), arg0.getY());
            } else
                rect.setP2(arg0.getX(), arg0.getY());
        }

        if (MainFrame.circ.isSelected()) {
            if (fillMode == true) {
                circ.setP2(arg0.getX(), arg0.getY());
                circ2.setP2(arg0.getX(), arg0.getY());
            } else
                circ.setP2(arg0.getX(), arg0.getY());
        }
        repaint();
    }


    protected void mreleased(MouseEvent e) {

        if (MainFrame.point.isSelected()) {
            point.setP2(e.getX(), e.getY());
            list.add(point);
//            Date dt = new Date();
//            Date dtn = new Date();
//            System.out.println(dt.toString());
//            dt.setTime(dt.getTime() + 3000);
//            while (!dt.toString().equals(dtn.toString())) {
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                dtn = new Date();
//            }
//            System.out.println("+ " + dt.toString());
        }

        if (MainFrame.line.isSelected()) {
            line.setP2(e.getX(), e.getY());
            list.add(line);
        }

        if (MainFrame.rect.isSelected()) {
            rect.setP2(e.getX(), e.getY());
            if (fillMode == true) {
                list.add(rect);
                list.add(rect2);
            } else
                list.add(rect);
        }

        if (MainFrame.circ.isSelected()) {
            circ.setP2(e.getX(), e.getY());
            if (fillMode == true) {
                list.add(circ);
                list.add(circ2);
            } else
                list.add(circ);
        }
        if (MainFrame.tmp == 1) {
            while (MainFrame.listtmp.size() != 0) {
                MainFrame.listtmp.remove(MainFrame.listtmp.size() - 1);
            }
            MainFrame.tmp = 0;
        }
        repaint();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (GraphObject o : list)
            o.paint(g2d, false);

        if (MainFrame.point.isSelected()) {
            if (point != null)
                point.paint(g2d, true);
        }

        if (MainFrame.line.isSelected()) {
            if (line != null)
                line.paint(g2d, true);
        }

        if (MainFrame.rect.isSelected()) {
            if (rect != null)
                if (fillMode == true) {
                    rect.paint(g2d, true);
                    rect2.paint(g2d, true);
                } else
                    rect.paint(g2d, true);
        }

        if (MainFrame.circ.isSelected()) {
            if (circ != null)
                if (fillMode == true) {
                    circ.paint(g2d, true);
                    circ2.paint(g2d, true);
                } else
                    circ.paint(g2d, true);
        }
    }
}
