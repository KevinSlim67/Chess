package Chess.Pieces;

import Chess.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WhitePawn extends Piece implements ActionListener {

    public WhitePawn() {
        super("white_pawn.png");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            Board.unHighlightAll(); //unhighlights old piece's movement when clicking on a new one
            if (!Board.getClickedCase(currentX, currentY)) {
                if (currentX != 6) { //2 possible movements if piece's x = 6, otherwise, only one possible movement
                    this.highlight(currentX - 1, currentY, this);
                } else {
                    this.highlight(currentX - 1, currentY, this);
                    this.highlight(currentX - 2, currentY, this);
                }
                if (currentX > 1) {
                    for (int i = currentX - 1; i >= currentX - 2; i--) {
                        hasCollision(i, currentY);
                    }
                }
                detectKill(currentX, currentY, this);
                Board.setClickedCase(currentX, currentY, true);
            } else {
                unDetectKill(currentX, currentY);
                Board.setClickedCase(currentX, currentY, false);
            }
        }
    }

    @Override
    public void hasCollision(int x, int y) {
        if (Board.hasPiece[x][y]) {
            for (int i = x; i >= 0; i--) {
                this.unHighlight(i, y);
            }
        }
    }


    @Override
    public void detectKill(int x, int y, Piece piece) {
        try {
            if (Board.hasPiece[x - 1][y - 1]) {
                highlight(x - 1, y - 1, this);
                Board.getPiece(x - 1, y - 1).setEnabled(false);
                Board.getPiece(x - 1, y - 1).addMouseListener(Board.clickMouseListener[x - 1][y - 1]);
            }
            if (Board.hasPiece[x - 1][y + 1]) {
                highlight(x - 1, y + 1, this);
                Board.getPiece(x - 1, y + 1).setEnabled(false);
                Board.getPiece(x - 1, y + 1).addMouseListener(Board.clickMouseListener[x - 1][y + 1]);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void unDetectKill(int x, int y) {
        try {
            if (Board.hasPiece[x - 1][y - 1]) {
                unHighlight(x - 1, y - 1);
                Board.getPiece(x - 1, y - 1).setEnabled(true);
            }
            if (Board.hasPiece[x - 1][y + 1]) {
                unHighlight(x - 1, y + 1);
                Board.getPiece(x - 1, y + 1).setEnabled(true);
            }
        } catch (Exception e) {
        }
    }
}

