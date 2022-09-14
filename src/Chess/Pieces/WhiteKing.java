package Chess.Pieces;

import Chess.Board.Board;
import Chess.Main;

public class WhiteKing extends Piece {
    private BlackRook blackRook1;
    private BlackRook blackRook2;
    private BlackBishop blackBishop1;
    private BlackBishop blackBishop2;
    private BlackKnight blackKnight1;
    private BlackKnight blackKnight2;
    private BlackQueen blackQueen;
    private BlackKing blackKing;
    private BlackPawn[] blackPawn = new BlackPawn[8];

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
            detectForbiddenMoves();
            hasCollision(x, y);
            Board.setClickedCase(x, y, true);
            hasCollision(x, y);
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

    @Override
    public void hasCollision(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.enemyHasMove[i][j]) {
                    unHighlight(i, j);
                }
            }
        }
    }

    public void detectForbiddenMoves() {
        blackRook1.possibleRookMoves(blackRook1.getCurrentX(), blackRook1.getCurrentY());
        blackRook2.possibleRookMoves(blackRook2.getCurrentX(), blackRook2.getCurrentY());
        blackBishop1.possibleBishopMoves(blackBishop1.getCurrentX(), blackBishop1.getCurrentY());
        blackBishop2.possibleBishopMoves(blackBishop2.getCurrentX(), blackBishop2.getCurrentY());
        blackKnight1.possibleKnightMoves(blackKnight1.getCurrentX(), blackKnight1.getCurrentY());
        blackKnight2.possibleKnightMoves(blackKnight2.getCurrentX(), blackKnight2.getCurrentY());
        blackKing.possibleKingMoves(blackKing.getCurrentX(), blackKing.getCurrentY());
        blackQueen.possibleQueenMoves(blackQueen.getCurrentX(), blackQueen.getCurrentY());
        for (int i = 0; i < 8; i++) {
            blackPawn[i].possibleWhitePawnMoves(blackPawn[i].getCurrentX(), blackPawn[i].getCurrentY());
        }

    }

    public void initialize() {
        blackRook1 = Main.frame.setBoard.blackRook1;
        blackRook2 = Main.frame.setBoard.blackRook2;
        blackBishop1 = Main.frame.setBoard.blackBishop1;
        blackBishop2 = Main.frame.setBoard.blackBishop2;
        blackKnight1 = Main.frame.setBoard.blackKnight1;
        blackKnight2 = Main.frame.setBoard.blackKnight2;
        blackQueen = Main.frame.setBoard.blackQueen;
        blackKing = Main.frame.setBoard.blackKing;
        for (int i = 0; i < 8; i++) {
            blackPawn[i] = Main.frame.setBoard.blackPawn[i];
        }
    }
}


