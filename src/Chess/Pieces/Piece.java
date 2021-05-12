package Chess.Pieces;

import Chess.Board;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Piece extends JButton {
    int currentX;
    int currentY;
    boolean clicked = false;

    public Piece(String piecePath, JPanel panel) {
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
        Board.setAllClickedCaseFalse();
        Border border = BorderFactory.createLineBorder(new Color(0x94dccb), 2);
        Board.getCase(x, y).setBorder(border);
        Board.getCase(x, y).repaint();
        movement(x, y, piece); //responsible for moving the piece to the selected highlighted case
        Board.getCase(x, y).addMouseListener(Board.getClickCase(x, y)); //needs to stay
    }

    public static void unHighlight(int x, int y) {
        Board.getCase(x, y).setBorder(null);
        Board.getCase(x, y).removeMouseListener(Board.getClickCase(x, y));
        Board.getCase(x, y).repaint();
    }


    public void movement(int curX, int curY, Piece piece) {
        Board.getCase(curX, curY).removeMouseListener(Board.clickCase[curX][curY]);
        Board.clickCase[curX][curY] = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //prevents having to click twice a case you clicked on once, then switched highlights from
                //also solves the bug of having to click twice after moving a piece
                Board.setAllClickedCaseFalse();
                piece.setPiece(piece, curX, curY);
                setCurrentX(curX);
                setCurrentY(curY);
                Board.unHighlightAll(); //gets rid of extra highlights onc you've picked the one you want
            }
        };
    }

    public void hasCollision(int x, int y) {
    }

    public void setPiece(JButton b, int x, int y) {
        Board.getCase(x, y).add(b, 0);
        Board.hasPiece[x][y] = true;
        this.setCurrentX(x);
        this.setCurrentY(y);
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

