package Chess.Pieces;

import Chess.Board;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Piece extends JButton {
    int[] possibleX;
    int[] possibleY;
    public int currentX;
    public int currentY;

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

    public void highlight(int x, int y, Piece piece) {
        Border border = BorderFactory.createLineBorder(new Color(0x94dccb), 2);

        Board.getCase(x, y).setBorder(border);
        Board.getCase(x, y).repaint();
        movement(x, y, piece);
        Board.getCase(x, y).addMouseListener(Board.getClickCase(x, y));

    }

    public void movement(int curX, int curY, Piece piece) {
    }

    public void setPiece(JButton b, int x, int y) {
        Board.chessCase[x][y].add(b, 0);
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentX(int x) {
        currentX = x;
    }

    public void setCurrentY(int y) {
        currentY = y;
    }
}


