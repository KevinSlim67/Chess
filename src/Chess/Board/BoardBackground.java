package Chess.Board;

import javax.swing.*;
import java.awt.*;

public class BoardBackground extends JPanel {
    public BoardBackground() {
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Image backgroundImage = new ImageIcon("assets\\dark_wooden_background.jpg").getImage();
        g2D.drawImage(backgroundImage, 0, 0, null);
    }
}

