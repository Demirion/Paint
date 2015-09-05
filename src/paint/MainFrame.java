package paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {

    // Komponenten
    Container cp; // Contentpane
    PaintPanel p = new PaintPanel();
    private ActionEvent e;

    public void InitMainFrame() {
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(p, BorderLayout.CENTER);
        MainFrame.point.setSelected(true);
    }

    public MainFrame() {
        InitMainFrame();
        InitMenu();
        this.setJMenuBar(bar);
    }

    // Menü
    static JMenuBar bar = new JMenuBar();
    JMenu datei = new JMenu("Datei");
    JMenu edit = new JMenu("Bearbeiten");
    JMenu set = new JMenu("Einstellungen");
    JMenu draw = new JMenu("Zeichnen");
    JMenu help = new JMenu("Hilfe");
    JMenuItem open = new JMenuItem("Öffnen");
    JMenuItem save = new JMenuItem("Speichern");
    JMenuItem close = new JMenuItem("Schließen");
    JMenuItem exit = new JMenuItem("Beenden");
    JMenuItem undo = new JMenuItem("Rückgängig");
    JMenuItem redo = new JMenuItem("Wiederherstellen");
    JMenuItem drawc = new JMenuItem("Zeichenfarbe");
    JMenuItem fillc = new JMenuItem("Füllfarbe");
    JCheckBoxMenuItem fill = new JCheckBoxMenuItem("Füllen");
    static JRadioButtonMenuItem point = new JRadioButtonMenuItem("Punkt");
    static JRadioButtonMenuItem line = new JRadioButtonMenuItem("Linie");
    static JRadioButtonMenuItem rect = new JRadioButtonMenuItem("Rechteck");
    static JRadioButtonMenuItem circ = new JRadioButtonMenuItem("Kreis");
    JMenuItem info = new JMenuItem("Info");
    //JColorChooser colorChooser = new JColorChooser();
    ButtonGroup group = new ButtonGroup();

    static Color color = Color.BLACK;
    static Color fColor = Color.BLACK;
    static boolean fMode = false;

    static ArrayList<GraphObject> listtmp = new ArrayList<GraphObject>();
    static int tmp = 0;

    private void InitMenu() {
        datei.add(open);
        open.addActionListener(this);
        datei.add(save);
        save.addActionListener(this);
        datei.add(close);
        close.addActionListener(this);
        datei.add(exit);
        exit.addActionListener(this);

        edit.add(undo);
        undo.addActionListener(this);
        edit.add(redo);
        redo.addActionListener(this);

        set.add(drawc);
        drawc.addActionListener(this);
        set.add(fillc);
        fillc.addActionListener(this);
        set.add(fill);
        fill.addActionListener(this);

        draw.add(point);
        point.addActionListener(this);
        group.add(point);

        draw.add(line);
        line.addActionListener(this);
        group.add(line);

        draw.add(rect);
        rect.addActionListener(this);
        group.add(rect);

        draw.add(circ);
        circ.addActionListener(this);
        group.add(circ);

        help.add(info);
        info.addActionListener(this);

        bar.add(datei);
        bar.add(edit);
        bar.add(set);
        bar.add(draw);
        bar.add(help);

        drawc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color ctmp = JColorChooser.showDialog(null, "Choose color", color);
                color = ctmp;
            }
        });

        fillc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color ctmp = JColorChooser.showDialog(null, "Choose color", fColor);
                fColor = ctmp;
            }
        });

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PaintPanel.list.size()!=0){
                    listtmp.add(PaintPanel.list.get(PaintPanel.list.size() - 1));
                    PaintPanel.list.remove(PaintPanel.list.size() - 1);
                    tmp = 1;
                } else System.out.println("Ist bereits alles gelöscht!");
                repaint();
            }
        });

        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tmp == 2) {
                    int i = 0;
                    while (listtmp.size() != 0) {
                        PaintPanel.list.add(i, listtmp.get(listtmp.size() - 1));
                        listtmp.remove(listtmp.size() - 1);
                        i++;
                    }
                    tmp = 0;
                } else {
                    if (listtmp.size() != 0) {
                        PaintPanel.list.add(listtmp.get(listtmp.size() - 1));
                        listtmp.remove(listtmp.size() - 1);
                        tmp = 1;
                    } else System.out.println("Bereits alles wiederhergestellt was möglich war!");
                }
                repaint();
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                while (PaintPanel.list.size() != 0) {
                    listtmp.add(i, PaintPanel.list.get(PaintPanel.list.size() - 1));
                    PaintPanel.list.remove(PaintPanel.list.size() - 1);
                    i++;
                }
                tmp = 2;
                while (PaintPanel.list.size() != 0) {
                    PaintPanel.list.remove(PaintPanel.list.size() - 1);
                }
                repaint();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream out = new FileOutputStream("H:/test");
                    ObjectOutputStream oout = new ObjectOutputStream(out);
                    oout.writeObject(PaintPanel.list);
                    oout.close();
                    System.out.println("Gespeichert!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                repaint();
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream in = new FileInputStream("H:/test");
                    ObjectInputStream iin = new ObjectInputStream(in);
                    PaintPanel.list = (ArrayList<GraphObject>) iin.readObject();
                    iin.close();
                    System.out.println("Geladen!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                repaint();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dies ist ein Programm zum Zeichnen :-)", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        if (fill.isSelected()) {
            fMode = true;
        } else {
            fMode = false;
        }
    }
}
