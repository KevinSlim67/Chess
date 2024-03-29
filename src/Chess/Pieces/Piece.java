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
    protected boolean[][] hasMove = new boolean[8][8];

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
        hasMove[x][y] = true;

        if (Board.hasPiece[x][y]) {
            kill(x, y, this); //kills the piece at (x, y)

        } else {
            movement(x, y, this); //responsible for moving the piece to the case at (x,y)
        }
        Board.getCase(x, y).addMouseListener(Board.clickMouseListener[x][y]); //needs to stay
    }

    public static void unHighlight(int x, int y) {
        if (Board.hasPiece[x][y])
            Board.getPiece(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
        Board.getCase(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
        Board.getCase(x, y).setBorder(null);
        Board.getCase(x, y).repaint();
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
                hasMove[x][y] = false;
                System.out.println("moved " + piece.getClass().getSimpleName() +
                        " from (" + currentX + " , " + currentY + "), to (" + x + " , " + y + ")");

                Board.hasPiece[currentX][currentY] = false;
                Board.isWhiteTurn = !Board.isWhiteTurn; //changes player turns
                //makes sure the blackKing isn't registered as in danger after you move any piece
                Main.frame.setBoard.blackKing.inDanger = false;

                piece.setPiece(x, y);
                Board.unHighlightAll(); //gets rid of extra highlights once you've picked the one you want
                Main.frame.getTurnIndicator().currentTurn(); //updates visually which player's turn it is
                Board.unDetectAllMoves();
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
                Main.frame.getTurnIndicator().numberOfTurns = 0; //resets the number when a kill happens to avoid draw
                //prevents having to click twice a case you clicked on once, then that you switched highlights from
                //also solves the bug of having to click twice after moving a piece
                Board.setAllClickedCaseFalse();

                //shows on the terminal what piece got killed, and by which piece it got killed
                System.out.println("killed " + Board.getPiece(x, y).getClass().getSimpleName() +
                        " at (" + x + " , " + y + ") by " + piece.getClass().getSimpleName() + " at (" +
                        currentX + " , " + currentY + ")");

                Board.hasPiece[currentX][currentY] = false;
                Board.isWhiteTurn = !Board.isWhiteTurn; //changes player turns
                Main.frame.getTurnIndicator().currentTurn(); //updates visually which player's turn it is
                Board.removePiece(x, y);
                piece.setPiece(x, y);
                Board.unHighlightAll(); //gets rid of extra highlights once you've picked the one you want
            }
        };
    }

    //gameplay elements overwritten by children classes methods------------------------------------------
    public void hasCollision(int x, int y) {
    }

    public void possibleMoves(int x, int y) {
    }

    public void detectKill(int x, int y) {
    }

    public void unDetectKill(int x, int y) {
    }
    //---------------------------------------------------------------------------------

    public void setPiece(int x, int y) {
        Board.getCase(x, y).add(this, 0); //adds piece to case at (x,y)
        Board.hasPiece[x][y] = true; //registers that case at (x,y) now has a piece
        this.setCurrentX(x); //sets piece's current X to the new case's X
        this.setCurrentY(y); //sets piece's current Y to the new case's Y
    }

    //set X,Y methods------------------------------------------------------------------
    public void setCurrentX(int x) {
        currentX = x;
    }

    public void setCurrentY(int y) {
        currentY = y;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }
    //---------------------------------------------------------------------------------

    public void rookMoves(int x, int y) {
        //highlights the moves that are vertical and horizontal to the piece
        //Vertical moves---------------------------------------------------------
        for (int i = x + 1; i < 8; i++) {
            if (Board.hasPiece[i][y]) {
                detectKill(i, y);
                break;
            }
            highlight(i, y);
        }

        for (int i = x - 1; i >= 0; i--) {
            if (Board.hasPiece[i][y]) {
                detectKill(i, y);
                break;
            }
            highlight(i, y);
        }
        //------------------------------------------------------------------------

        //Horizontal moves--------------------------------------------------------
        for (int i = y + 1; i < 8; i++) {
            if (Board.hasPiece[x][i]) {
                detectKill(x, i);
                break;
            }
            highlight(x, i);
        }

        for (int i = y - 1; i >= 0; i--) {
            if (Board.hasPiece[x][i]) {
                detectKill(x, i);
                break;
            }
            highlight(x, i);
        }
    }

    public void bishopMoves(int x, int y) {
        //highlights the moves that are diagonal to the piece
        //Left diagonal---------------------------------------------------------------
        int j = y + 1;
        for (int i = x + 1; i < 8; i++) {
            if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
                if (Board.hasPiece[i][j]) {
                    detectKill(i, j);
                    break;
                } else {
                    highlight(i, j);
                    j++;
                }
            }
        }

        j = y - 1;
        for (int i = x - 1; i >= 0; i--) {
            if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
                if (Board.hasPiece[i][j]) {
                    detectKill(i, j);
                    break;
                } else {
                    highlight(i, j);
                    j--;
                }
            }
        }
        //-------------------------------------------------------------------------

        //Right diagonal-----------------------------------------------------------
        j = y - 1;
        for (int i = x + 1; i < 8; i++) {
            if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
                if (Board.hasPiece[i][j]) {
                    detectKill(i, j);
                    break;
                } else {
                    highlight(i, j);
                    j--;
                }
            }
        }

        j = y + 1;
        for (int i = x - 1; i >= 0; i--) {
            if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
                if (Board.hasPiece[i][j]) {
                    detectKill(i, j);
                    break;
                } else {
                    highlight(i, j);
                    j++;
                }
            }
        }
        //---------------------------------------------------------------------------
    }

    public void knightMoves(int x, int y) {
        //highlights the moves that are 2 cases in other direction, and one case in the other of that piece
        int[] xMoves = {x + 2, x + 2, x - 2, x - 2, x + 1, x + 1, x - 1, x - 1};
        int[] yMoves = {y + 1, y - 1, y + 1, y - 1, y + 2, y - 2, y + 2, y - 2};
        for (int i = 0; i < xMoves.length; i++)
            if ((xMoves[i] >= 0 && xMoves[i] < 8) && (yMoves[i] >= 0 && yMoves[i] < 8)) {
                if (Board.hasPiece[xMoves[i]][yMoves[i]])
                    detectKill(xMoves[i], yMoves[i]);
                else {
                    highlight(xMoves[i], yMoves[i]);
                }
            }
    }

    public void kingMoves(int x, int y) {
        //highlights the cases all around the piece
        int[] xMoves = {x + 1, x + 1, x + 1, x - 1, x - 1, x - 1, x, x};
        int[] yMoves = {y - 1, y, y + 1, y - 1, y, y + 1, y - 1, y + 1};
        for (int i = 0; i < xMoves.length; i++)
            if ((xMoves[i] >= 0 && xMoves[i] < 8) && (yMoves[i] >= 0 && yMoves[i] < 8)) {
                if (Board.hasPiece[xMoves[i]][yMoves[i]])
                    detectKill(xMoves[i], yMoves[i]);
                else {
                    highlight(xMoves[i], yMoves[i]);
                }
            }
    }

    public void possibleRookMoves(int x, int y) {
        //Vertical moves---------------------------------------------------------
        for (int i = x + 1; i < 8; i++) {
            Board.enemyHasMove[i][y] = true;


            if (Board.hasPiece[i][y]) {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            Board.enemyHasMove[i][y] = true;
            if (Board.hasPiece[i][y]) {
                break;
            }
        }
        //------------------------------------------------------------------------

        //Horizontal moves--------------------------------------------------------
        for (int i = y + 1; i < 8; i++) {
            Board.enemyHasMove[x][i] = true;
            if (Board.hasPiece[x][i]) {
                break;
            }
        }

        for (int i = y - 1; i >= 0; i--) {
            Board.enemyHasMove[x][i] = true;
            if (Board.hasPiece[x][i]) {
                break;
            }
        }
        //------------------------------------------------------------------------
    }

    public void possibleBishopMoves(int x, int y) {
        //Left diagonal---------------------------------------------------------------
        int j = y + 1;
        for (int i = x + 1; i < 8; i++) {
            if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
                Board.enemyHasMove[i][j] = true;
                if (Board.hasPiece[i][j]) {
                    break;
                } else {
                    j++;
                }
            }
        }

        j = y - 1;
        for (int i = x - 1; i >= 0; i--) {
            if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
                Board.enemyHasMove[i][j] = true;
                if (Board.hasPiece[i][j]) {
                    break;
                } else {
                    j--;
                }
            }
        }
        //-------------------------------------------------------------------------

        //Right diagonal-----------------------------------------------------------
        j = y - 1;
        for (int i = x + 1; i < 8; i++) {
            if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
                Board.enemyHasMove[i][j] = true;
                if (Board.hasPiece[i][j]) {
                    break;
                } else {
                    j--;
                }
            }
        }

        j = y + 1;
        for (int i = x - 1; i >= 0; i--) {
            if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
                Board.enemyHasMove[i][j] = true;
                if (Board.hasPiece[i][j]) {
                    break;
                } else {
                    j++;
                }
            }
        }
        //---------------------------------------------------------------------------
    }

    public void possibleKnightMoves(int x, int y) {
        //highlights the moves that are 2 cases in other direction, and one case in the other of that piece
        int[] xMoves = {x + 2, x + 2, x - 2, x - 2, x + 1, x + 1, x - 1, x - 1};
        int[] yMoves = {y + 1, y - 1, y + 1, y - 1, y + 2, y - 2, y + 2, y - 2};
        for (int i = 0; i < xMoves.length; i++)
            if ((xMoves[i] >= 0 && xMoves[i] < 8) && (yMoves[i] >= 0 && yMoves[i] < 8)) {
                Board.enemyHasMove[xMoves[i]][yMoves[i]] = true;
            }
    }

    public void possibleKingMoves(int x, int y) {
        //highlights the cases all around the piece
        int[] xMoves = {x + 1, x + 1, x + 1, x - 1, x - 1, x - 1, x, x};
        int[] yMoves = {y - 1, y, y + 1, y - 1, y, y + 1, y - 1, y + 1};
        for (int i = 0; i < xMoves.length; i++)
            if ((xMoves[i] >= 0 && xMoves[i] < 8) && (yMoves[i] >= 0 && yMoves[i] < 8)) {
                Board.enemyHasMove[xMoves[i]][yMoves[i]] = true;
            }
    }

    public void possibleQueenMoves(int x, int y) {
        possibleRookMoves(x, y);
        possibleBishopMoves(x, y);
    }

    public void possibleWhitePawnMoves(int x, int y) {
        try {
            Board.enemyHasMove[x - 1][y - 1] = true;
        } catch (Exception e) {
        }
        try {
            Board.enemyHasMove[x - 1][y + 1] = true;
        } catch (Exception e) {
        }
    }
}








