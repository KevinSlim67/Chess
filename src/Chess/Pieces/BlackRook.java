package Chess.Pieces;

import Chess.Board.Board;

public class BlackRook extends Piece {
    public BlackRook() {
        super("black_rook.png");
        team = 'b';
        this.addActionListener(this);
    }

    public void possibleMoves(int x, int y) {
        Board.unHighlightAll(); //unhighlights old piece's movement when clicking on a new one
        if (Board.getClickedCase(x, y)) {
            rookMoves(x, y);
            detectKill(x, y);
            Board.setClickedCase(x, y, true);
        } else {
            unDetectKill(x, y);
            Board.setClickedCase(x, y, false);
        }
    }

    @Override
    public void detectKill(int x, int y) {
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
