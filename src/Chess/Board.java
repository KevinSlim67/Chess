package Chess;

import Chess.Pieces.Piece;
import Chess.Tiles.BrownTile;
import Chess.Tiles.WhiteTile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel {
    public static JPanel[][] chessCase = new JPanel[8][8];
    int width;
    int height;

    public Board(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(8, 8, 0, 0));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(border);
        this.setVisible(true);

        for (int i = 0; i < chessCase.length; i++) {
            for (int j = 0; j < chessCase[i].length; j++) {
                chessCase[i][j] = new JPanel();
                chessCase[i][j].setBorder(border);
                chessCase[i][j].setOpaque(false);
            }
        }

        //makes checkerboard pattern
        for (int i = 0; i < chessCase.length; i++) {
            for (int j = 0; j < chessCase[i].length; j++) {
                if (((i + 1) + (j + 1)) % 2 == 0) {
                    chessCase[i][j] = new WhiteTile();
                } else {
                    chessCase[i][j] = new BrownTile();
                }
                this.add(chessCase[i][j]);
            }
        }
    }

    static MouseAdapter[][] clickCase = new MouseAdapter[8][8];

    public static void highlight(int[] x, int[] y, Piece piece) {
        Border border = BorderFactory.createLineBorder(new Color(0x94dccb), 2);

        for (int i = 0; i < x.length; i++) {
            chessCase[x[i]][y[i]].setBorder(border);
            chessCase[x[i]][y[i]].repaint();

            setClickCase(x[i], y[i], piece);
            chessCase[x[i]][y[i]].addMouseListener(getClickCase(x[i], y[i]));
        }
    }

    public static void unHighlightAll() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessCase[i][j].setBorder(null);
                chessCase[i][j].removeMouseListener(getClickCase(i, j)); //the mouse listener is not being removed after
                chessCase[i][j].repaint();                              // this function is being executed
            }
        }
    }

    private static void setClickCase(int x, int y, Piece piece) {
        chessCase[x][y].removeMouseListener(getClickCase(x,y));
        clickCase[x][y] = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Board.setPiece(piece, x, y);
                unHighlightAll();
                }
        };
    }

    private static MouseAdapter getClickCase(int x, int y) {
        return clickCase[x][y];
    }

    public static void setPiece(JButton b, int x, int y) {
        chessCase[x][y].add(b, 0);
    }

    public static JPanel getCase(int x, int y) {
        return chessCase[x][y];
    }

    public static void removePiece(JButton b, int i, int j) {
        chessCase[i][j].remove(b);
        chessCase[i][j].repaint();

    }
}






