package Chess.Pieces;

import Chess.Board;

import javax.swing.*;
import java.awt.*;

public class Piece extends JButton {
    int[] possibleX;
    int[] possibleY;
    public Piece(String piecePath, JPanel panel, int numberOfPossibleMoves) {
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setFocusable(false);
        this.setOpaque(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setIcon(new ImageIcon(new ImageIcon("assets\\pieces\\" + piecePath).
                getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
    }
}

