package Chess.Pieces;

import Chess.Board.Board;

public class WhitePawn extends Piece {

    public WhitePawn() {
        super("white_pawn.png");
        team = 'w';
        this.addActionListener(this);
    }

    @Override
    public void hasCollision(int x, int y) {
        if (Board.hasPiece[x][y]) {
            for (int i = x; i >= 0; i--) {
                unHighlight(i, y);
            }
        }
    }

    @Override
    public void possibleMoves(int x, int y) {
        Board.unHighlightAll(); //unhighlights old piece's movement when clicking on a new one
        if (Board.getClickedCase(x, y)) {
            if (x != 6) { //2 possible movements if piece's x = 6, otherwise, only one possible movement
                this.highlight(x - 1, y);
            } else {
                this.highlight(x - 1, y);
                this.highlight(x - 2, y);
            }
            if (x > 1) {
                for (int i = x - 1; i >= x - 2; i--) {
                    hasCollision(i, y);
                }
            }
            detectKill(x, y);
            Board.setClickedCase(x, y, true);
        } else {
            unDetectKill(x, y);
            Board.setClickedCase(x, y, false);
        }
    }

    @Override
    public void detectKill(int x, int y) {
        try {
            if (Board.hasPiece[x - 1][y - 1] && Board.getPiece(x - 1, y - 1).team == 'b') {
                highlight(x - 1, y - 1);
                Board.getPiece(x - 1, y - 1).setEnabled(false);
                Board.getPiece(x - 1, y - 1).addMouseListener(Board.clickMouseListener[x - 1][y - 1]);
            }
        } catch (Exception e) {
        }
        try {
            if (Board.hasPiece[x - 1][y + 1] && Board.getPiece(x - 1, y + 1).team == 'b') {
                highlight(x - 1, y + 1);
                Board.getPiece(x - 1, y + 1).setEnabled(false);
                Board.getPiece(x - 1, y + 1).addMouseListener(Board.clickMouseListener[x - 1][y + 1]);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void unDetectKill(int x, int y) {
        try {
            if (Board.hasPiece[x - 1][y - 1] && Board.getPiece(x - 1, y - 1).team == 'b') {
                unHighlight(x - 1, y - 1);
                Board.getPiece(x - 1, y - 1).setEnabled(true);
            }
        } catch (Exception e) {
        }
        try {
            if (Board.hasPiece[x - 1][y + 1] && Board.getPiece(x - 1, y + 1).team == 'b') {
                unHighlight(x - 1, y + 1);
                Board.getPiece(x - 1, y + 1).setEnabled(true);
            }
        } catch (Exception e) {
        }
    }
}

