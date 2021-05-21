package Chess.Pieces;

import Chess.Board.Board;
import Chess.Main;

public class BlackKing extends Piece {
    private WhiteRook whiteRook1;
    private WhiteRook whiteRook2;
    private WhiteBishop whiteBishop1;
    private WhiteBishop whiteBishop2;
    private WhiteKnight whiteKnight1;
    private WhiteKnight whiteKnight2;
    private WhiteQueen whiteQueen;
    private WhiteKing whiteKing;
    private WhitePawn[] whitePawn = new WhitePawn[8];

    public BlackKing() {
        super("black_king.png");
        team = 'b';
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

    @Override
    public void hasCollision(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.hasMove[i][j]) {
                    unHighlight(i, j);
                }
            }
        }
    }

    public void detectForbiddenMoves() {
        whiteRook1.possibleRookMoves(whiteRook1.getCurrentX(), whiteRook1.getCurrentY());
        whiteRook2.possibleRookMoves(whiteRook2.getCurrentX(), whiteRook2.getCurrentY());
        whiteBishop1.possibleBishopMoves(whiteBishop1.getCurrentX(),whiteBishop1.getCurrentY());
        whiteBishop2.possibleBishopMoves(whiteBishop2.getCurrentX(),whiteBishop2.getCurrentY());
        whiteKnight1.possibleKnightMoves(whiteKnight1.getCurrentX(), whiteKnight1.getCurrentY());
        whiteKnight2.possibleKnightMoves(whiteKnight2.getCurrentX(), whiteKnight2.getCurrentY());
        whiteKing.possibleKingMoves(whiteKing.getCurrentX(), whiteKing.getCurrentY());
        whiteQueen.possibleQueenMoves(whiteQueen.getCurrentX(), whiteQueen.getCurrentY());
        for (int i = 0; i < 8; i++) {
            whitePawn[i].possibleWhitePawnMoves(whitePawn[i].getCurrentX(), whitePawn[i].getCurrentY());
        }

    }

    public void initialize() {
        whiteRook1 = Main.frame.setBoard.whiteRook1;
        whiteRook2 = Main.frame.setBoard.whiteRook2;
        whiteBishop1 = Main.frame.setBoard.whiteBishop1;
        whiteBishop2 = Main.frame.setBoard.whiteBishop2;
        whiteKnight1 = Main.frame.setBoard.whiteKnight1;
        whiteKnight2 = Main.frame.setBoard.whiteKnight2;
        whiteQueen = Main.frame.setBoard.whiteQueen;
        whiteKing = Main.frame.setBoard.whiteKing;
        for (int i = 0; i < 8; i++) {
            whitePawn[i] = Main.frame.setBoard.whitePawn[i];
        }
    }
}

