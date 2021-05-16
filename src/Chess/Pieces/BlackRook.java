package Chess.Pieces;

import Chess.Board.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackRook extends Piece implements ActionListener {
    public BlackRook() {
        super("black_rook.png");
        team = 'b';
        this.addActionListener(this);
    }


    public void possibleMoves(int x, int y) {
        Board.unHighlightAll(); //unhighlights old piece's movement when clicking on a new one
        if (Board.getClickedCase(x, y)) {
            //Vertical moves---------------------------------------------------------
            for (int i = x + 1; i < 8; i++) {
                if (Board.hasPiece[i][y]) {
                    detectKill(i, y, this);
                    break;
                }
                highlight(i, y);
            }

            for (int i = x - 1; i >= 0; i--) {
                if (Board.hasPiece[i][y]) {
                    detectKill(i, y, this);
                    break;
                }
                highlight(i, y);
            }
            //------------------------------------------------------------------------

            //Horizontal moves--------------------------------------------------------
            for (int i = y + 1; i < 8; i++) {
                if (Board.hasPiece[x][i]) {
                    detectKill(x, i, this);
                    break;
                }
                highlight(x, i);
            }

            for (int i = y - 1; i >= 0; i--) {
                if (Board.hasPiece[x][i]) {
                    detectKill(x, i, this);
                    break;
                }
                highlight(x, i);
            }
            //-------------------------------------------------------------------------
            detectKill(x, y, this);
            Board.setClickedCase(x, y, true);
        } else {
            unDetectKill(x, y);
            Board.setClickedCase(x, y, false);
        }
    }

    @Override
    public void detectKill(int x, int y, Piece piece) {

        if (Board.hasPiece[x][y] && Board.getPiece(x, y).team == 'w') {
            highlight(x, y);
            Board.getPiece(x, y).setEnabled(false);
            Board.getPiece(x, y).addMouseListener(Board.clickMouseListener[x][y]);
        }
    }

    @Override
    public void unDetectKill(int x, int y) {

        if (Board.hasPiece[x][y] && Board.getPiece(x, y).team == 'w') {
            unHighlight(x, y);
            Board.getPiece(x, y).setEnabled(true);
            Board.getPiece(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
        }
    }

}
