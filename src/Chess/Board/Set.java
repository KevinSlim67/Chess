package Chess.Board;

import Chess.Board.Board;
import Chess.Pieces.*;

public class Set extends Board {

    public WhitePawn[] whitePawn = new WhitePawn[8];
    public BlackPawn[] blackPawn = new BlackPawn[8];

    public WhiteRook whiteRook1 = new WhiteRook();
    public WhiteRook whiteRook2 = new WhiteRook();
    public BlackRook blackRook1 = new BlackRook();
    public BlackRook blackRook2 = new BlackRook();

    public WhiteBishop whiteBishop1 = new WhiteBishop();
    public WhiteBishop whiteBishop2 = new WhiteBishop();
    public BlackBishop blackBishop1 = new BlackBishop();
    public BlackBishop blackBishop2 = new BlackBishop();

    public WhiteKnight whiteKnight1 = new WhiteKnight();
    public WhiteKnight whiteKnight2 = new WhiteKnight();
    public BlackKnight blackKnight1 = new BlackKnight();
    public BlackKnight blackKnight2 = new BlackKnight();

    public WhiteQueen whiteQueen = new WhiteQueen();
    public BlackQueen blackQueen = new BlackQueen();

    public WhiteKing whiteKing = new WhiteKing();
    public BlackKing blackKing = new BlackKing();

    public Set(int width, int height) {
        super(width, height);

        //queens
        whiteQueen.setPiece(7, 4);
        blackQueen.setPiece(0, 4);

        //pawns
        for (int i = 0; i < 8; i++) {
            whitePawn[i] = new WhitePawn();
            whitePawn[i].setPiece(6, i);
        }

        for (int i = 0; i < 8; i++) {
            blackPawn[i] = new BlackPawn();
            blackPawn[i].setPiece(1, i);
        }

        //rooks
        whiteRook1.setPiece(7, 0);
        whiteRook2.setPiece(7, 7);

        blackRook1.setPiece(0, 0);
        blackRook2.setPiece(0, 7);

        //bishops
        whiteBishop1.setPiece(7, 2);
        whiteBishop2.setPiece(7, 5);

        blackBishop1.setPiece(0, 2);
        blackBishop2.setPiece(0, 5);

        //knights
        whiteKnight1.setPiece(7, 1);
        whiteKnight2.setPiece(7, 6);

        blackKnight1.setPiece(0, 1);
        blackKnight2.setPiece(0, 6);

        //kings
        whiteKing.setPiece(7, 3);
        blackKing.setPiece(0, 3);

    }
}

