package Chess.Pieces;

import Chess.Board.Board;
import Chess.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Piece extends JButton implements ActionListener {
    protected int currentX;
    protected int currentY;
    public char team;
    public boolean actionPerformedActive;

    public Piece(String piecePath) {
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setFocusable(false);
        this.setOpaque(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setIcon(new ImageIcon(new ImageIcon("assets\\pieces\\" + piecePath).
                getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        actionPerformedActive = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((Board.isWhiteTurn && team == 'w') || (!Board.isWhiteTurn && team == 'b')) {
            if (e.getSource() == this) {
                if (actionPerformedActive) {
                    possibleMoves(currentX, currentY);
                }
            }
        }
    }


    //highlight methods------------------------------------------------------------------------------
    public void highlight(int x, int y) {
        Board.setAllClickedCaseFalse(); //avoids having to click twice on a piece after clicking on another one
        Border border = BorderFactory.createLineBorder(new Color(0x94dccb), 2);
        Board.getCase(x, y).setBorder(border);
        Board.getCase(x, y).repaint();

        if (Board.hasPiece[x][y]) {
            kill(x, y, this); //kills the piece at (x, y)

        } else {
            movement(x, y, this); //responsible for moving the piece to the case at (x,y)
        }
        Board.getCase(x, y).addMouseListener(Board.clickMouseListener[x][y]); //needs to stay
    }

    public static void unHighlight(int x, int y) {
        if (Board.hasPiece[x][y]) {
            Board.getCase(x, y).setBorder(null);
            Board.getCase(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
            Board.getPiece(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
            Board.getCase(x, y).repaint();
        }
    }
    //-----------------------------------------------------------------------------------------------


    public void movement(int x, int y, Piece piece) {
        Board.getCase(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
        Board.clickMouseListener[x][y] = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //prevents having to click twice a case you clicked on once, then that you switched highlights from
                //also solves the bug of having to click twice after moving a piece
                Board.setAllClickedCaseFalse();

                //shows on the terminal what movement just happened
                System.out.println("moved " + piece.getClass().getSimpleName() +
                        " from (" + currentX + " , " + currentY + "), to (" + x + " , " + y + ")");

                Board.hasPiece[currentX][currentY] = false;
                Board.isWhiteTurn = !Board.isWhiteTurn; //changes player turns
                Main.frame.getTurnIndicator().currentTurn(); //shows which player's turn it is
                piece.setPiece(x, y);
                Board.unHighlightAll(); //gets rid of extra highlights once you've picked the one you want
                // Board.isWhiteTurn = !Board.isWhiteTurn;

            }
        };
    }

    public void kill(int x, int y, Piece piece) {
        movement(x, y, piece);
        Board.getCase(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
        try {
            Board.getPiece(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
        } catch (Exception e) {
        }
        Board.clickMouseListener[x][y] = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //prevents having to click twice a case you clicked on once, then that you switched highlights from
                //also solves the bug of having to click twice after moving a piece
                Board.setAllClickedCaseFalse();

                //shows on the terminal what piece got killed, and by which piece it got killed
                System.out.println("killed " + Board.getPiece(x, y).getClass().getSimpleName() +
                        " at (" + x + " , " + y + ") by " + piece.getClass().getSimpleName() + " at (" +
                        currentX + " , " + currentY + ")");

                Board.hasPiece[currentX][currentY] = false;
                Board.isWhiteTurn = !Board.isWhiteTurn; //changes player turns
                Main.frame.getTurnIndicator().currentTurn(); //shows which player's turn it is
                Board.removePiece(x, y);
                piece.setPiece(x, y);
                Board.unHighlightAll(); //gets rid of extra highlights once you've picked the one you want
            }
        };
    }

    public void hasCollision(int x, int y) {
    }

    public void possibleMoves(int x, int y) {

    }

    public void detectKill(int x, int y, Piece piece) {
    }

    public void unDetectKill(int x, int y) {
    }

    public void setPiece(int x, int y) {
        Board.getCase(x, y).add(this, 0); //adds piece to case at (x,y)
        Board.hasPiece[x][y] = true; //registers that case at (x,y) now has a piece
        this.setCurrentX(x); //sets piece's current X to the new case's X
        this.setCurrentY(y); //sets piece's current Y to the new case's Y
    }

    //set X,Y methods--------------------------------------------------------
    public void setCurrentX(int x) {
        currentX = x;
    }

    public void setCurrentY(int y) {
        currentY = y;
    }

    //------------------------------------------------------------------------


}


