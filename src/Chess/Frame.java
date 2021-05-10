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

    }
}
