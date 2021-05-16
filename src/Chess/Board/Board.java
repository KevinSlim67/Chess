package Chess.Board;

import Chess.Main;
import Chess.Pieces.Piece;
import Chess.Tiles.BrownTile;
import Chess.Tiles.WhiteTile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel {
    private static final JPanel[][] chessCase = new JPanel[8][8];
    public static boolean[][] hasPiece = new boolean[8][8]; //don't make private
    public static MouseAdapter[][] clickMouseListener = new MouseAdapter[8][8]; //don't make private
    public static boolean isWhiteTurn;

    public Board(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(8, 8, 0, 0));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(border);
        this.setVisible(true);

        //generates all 64 cases, each one them as panel to store things
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
                this.add(chessCase[i][j], Integer.valueOf(1));
            }
        }
        isWhiteTurn = true;
    }


    //clickedCase methods--------------------------------------------------------
    private static final boolean[][] clickedCase = new boolean[8][8];

    public static void setClickedCase(int x, int y, boolean value) {
        clickedCase[x][y] = value;
    }

    public static boolean getClickedCase(int x, int y) {
        return !clickedCase[x][y]; //dunno why, but compiler gives a warning that it can be inverted
    }

    public static void setAllClickedCaseFalse() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                clickedCase[i][j] = false;
            }
        }
    }
    //----------------------------------------------------------------------------

    public static void unHighlightAll() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    getPiece(i, j).setEnabled(true);
                    getPiece(i, j).removeMouseListener(Board.clickMouseListener[i][j]);
                } catch (Exception e) {
                }
                getCase(i, j).setBorder(null);
                getCase(i, j).removeMouseListener(Board.clickMouseListener[i][j]);
                getCase(i, j).repaint();
            }
        }
    }

    public static void removePiece(int x, int y) {
        if (Board.hasPiece[x][y]) {
            getPiece(x, y).setEnabled(true);
            getPiece(x, y).actionPerformedActive = false; //make's clicking the button not do anything
            getPiece(x, y).removeMouseListener(clickMouseListener[x][y]);

            if (getPiece(x, y).team == 'w') {
                Main.frame.addPanelWest(Board.getPiece(x, y));
            } else {
                Main.frame.addPanelEast(Board.getPiece(x, y));
            }

            Board.hasPiece[x][y] = false; //registers that case at (x,y) doesn't have a piece anymore
            getCase(x, y).repaint();
        }
    }


    public static Piece getPiece(int x, int y) {
        Component component = getCase(x, y).getComponent(0);
        if (component instanceof Piece) { //checks if its really a piece
            return (Piece) component; //casts to Piece
        }
        return null; //if it's not a piece, don't do anything
    }


    public static JPanel getCase(int x, int y) {
        return chessCase[x][y];
    }
}








