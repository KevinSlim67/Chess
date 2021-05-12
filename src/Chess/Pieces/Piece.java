package Chess.Pieces;

import Chess.Board;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class Piece extends JButton {
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

    public static void unHighlight(int x, int y) {
        Board.getCase(x, y).setBorder(null);
        Board.getCase(x, y).removeMouseListener(Board.getClickCase(x, y));
        Board.getCase(x, y).repaint();
    }


    public void movement(int curX, int curY, Piece piece) {
    }

    public void hasCollision(int x, int y) {
    }

    public void setPiece(JButton b, int x, int y) {
        Board.getCase(x, y).add(b, 0);
        Board.hasPiece[x][y] = true;
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


