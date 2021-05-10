package Chess.Tiles;

import javax.swing.*;
import java.awt.*;

public class BrownTile extends JPanel {
    public BrownTile() {
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Image backgroundImage = new ImageIcon("assets\\wooden_background.jpg").getImage();
        g2D.drawImage(backgroundImage, 0, 0, null);
    }
}

