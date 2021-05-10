package Chess.Tiles;

import javax.swing.*;
import java.awt.*;

public class WhiteTile extends JPanel {
    public WhiteTile() {
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Image backgroundImage = new ImageIcon("assets\\light_wooden_background.jpg").getImage();
        g2D.drawImage(backgroundImage, 0, 0, null);
    }
}

