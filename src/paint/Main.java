package paint;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        //f.setUndecorated(true);
        f.setLocation(800, 300);
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Computergrafik - Paint");
        f.setVisible(true);
    }
}
