package Chess.Pieces;

import Chess.Board.Board;

public class WhiteKing extends Piece {
    public WhiteKing() {
        super("white_king.png");
        team = 'w';
        this.addActionListener(this);
    }


    public void possibleMoves(int x, int y) {
        Board.unHighlightAll(); //unhighlights old piece's movement when clicking on a new one
        if (Board.getClickedCase(x, y)) {
            kingMoves(x, y);
            detectKill(x, y);
            Board.setClickedCase(x, y, true);
        } else {
            unDetectKill(x, y);
            Board.setClickedCase(x, y, false);
        }
    }

    @Override
    public void detectKill(int x, int y) {

        if (Board.hasPiece[x][y] && Board.getPiece(x, y).team == 'b') {
            highlight(x, y);
            Board.getPiece(x, y).setEnabled(false);
            Board.getPiece(x, y).addMouseListener(Board.clickMouseListener[x][y]);
        }
    }

    @Override
    public void unDetectKill(int x, int y) {

        if (Board.hasPiece[x][y] && Board.getPiece(x, y).team == 'b') {
            unHighlight(x, y);
            Board.getPiece(x, y).setEnabled(true);
            Board.getPiece(x, y).removeMouseListener(Board.clickMouseListener[x][y]);
        }
    }
}

