package Chess;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String args[]) {
        final int NUM = 650;
        int width = NUM; int height = NUM;
        Frame frame = new Frame(width, height, "Chess");
        frame.setLayout(new BorderLayout(0,0));

        JPanel[] panel = new JPanel[4];
        for (int i = 0; i < 4 ; i++) {
            panel[i] = new JPanel();
            panel[i].setOpaque(false);
            panel[i].setPreferredSize(new Dimension(100,100));
            panel[i].setVisible(true);
        }

        frame.add(panel[0], BorderLayout.NORTH);
        frame.add(panel[1], BorderLayout.SOUTH);
        frame.add(panel[2], BorderLayout.EAST);
        frame.add(panel[3], BorderLayout.WEST);

        frame.add(new Set(20, 20), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
