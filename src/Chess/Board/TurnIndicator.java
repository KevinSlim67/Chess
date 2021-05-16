package Chess.Board;

import Chess.Board.Board;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TurnIndicator extends JLabel {
    private Color white = new Color(0xd6d2ce);
    private Color black = new Color(0x1e1d1c);
    private Color gold = new Color(0xcea544);
    private Color yellow = new Color(0xffd163);
    public TurnIndicator() {
        Border border = BorderFactory.createEtchedBorder(7, yellow,gold);
        this.setBorder(border);
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setPreferredSize(new Dimension(295, 52));
        this.setFont(new Font("Segoe UI Semilight", Font.BOLD, 28));
        this.setOpaque(true);
        this.setVisible(true);

        this.setBackground(white);
        this.setForeground(black);
        this.setText("White Team is playing");
    }

    public void currentTurn() {
        if (Board.isWhiteTurn) {
            this.setBackground(white);
            this.setForeground(black);
            this.setText("White Team is playing");
        } else {
            this.setBackground(black);
            this.setForeground(white);
            this.setText("Black Team is playing");
        }
        this.repaint();
    }
}
