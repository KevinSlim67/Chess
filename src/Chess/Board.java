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
    public static boolean[][] hasPiece = new boolean[8][8];
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

    public static MouseAdapter[][] clickCase = new MouseAdapter[8][8];


    public static void unHighlightAll() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessCase[i][j].setBorder(null);
                chessCase[i][j].removeMouseListener(getClickCase(i, j));
                chessCase[i][j].repaint();
            }
        }
    }

    public static void setClickCase(int x, int y, Piece piece) {
        piece.movement(piece.currentX, piece.currentY, piece);
    }

    public static MouseAdapter getClickCase(int x, int y) {
        return clickCase[x][y];
    }

    public static JPanel getCase(int x, int y) {
        return chessCase[x][y];
    }

    public static void removePiece(JButton b, int i, int j) {
        chessCase[i][j].remove(b);
        chessCase[i][j].repaint();

    }
}






