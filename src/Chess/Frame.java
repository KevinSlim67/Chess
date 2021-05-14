package Chess;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(int width, int height, String name) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x8c551d));
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setSize(width, height);
        this.setTitle(name);
        this.setVisible(true);

        JPanel[] panel = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            panel[i] = new JPanel();
            panel[i].setOpaque(false);
            panel[i].setPreferredSize(new Dimension(100, 100));
            panel[i].setVisible(true);
        }

        this.add(panel[0], BorderLayout.NORTH);
        this.add(panel[1], BorderLayout.SOUTH);
        this.add(panel[2], BorderLayout.EAST);
        this.add(panel[3], BorderLayout.WEST);

        this.add(new Set(20, 20), BorderLayout.CENTER);
        this.setVisible(true);

    }
}
