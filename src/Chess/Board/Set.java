package Chess.Board;

import Chess.Board.Board;
import Chess.Pieces.*;

public class Set extends Board {

    public Set(int width, int height) {
        super(width, height);

        //kings
       WhiteKing whiteKing = new WhiteKing();
       whiteKing.setPiece(7, 3);
       BlackKing blackKing = new BlackKing();
       blackKing.setPiece(0,3);

       //queens
        WhiteQueen whiteQueen = new WhiteQueen();
        whiteQueen.setPiece(7,4);
        BlackQueen blackQueen = new BlackQueen();
        blackQueen.setPiece(0,4);

        //pawns
        WhitePawn[] whitePawn = new WhitePawn[8];
        for (int i = 0; i < 8; i++) {
            whitePawn[i] = new WhitePawn();
            whitePawn[i].setPiece(6, i);
        }

        BlackPawn[] blackPawn = new BlackPawn[8];
        for (int i = 0; i < 8; i++) {
            blackPawn[i] = new BlackPawn();
            blackPawn[i].setPiece(1, i);
        }

        //rooks
        WhiteRook whiteRook1 = new WhiteRook();
        whiteRook1.setPiece(7,0);
        WhiteRook whiteRook2 = new WhiteRook();
        whiteRook2.setPiece(7,7);

        BlackRook blackRook1 = new BlackRook();
        blackRook1.setPiece(0,0);
        BlackRook blackRook2 = new BlackRook();
        blackRook2.setPiece(0,7);

        //bishops
        WhiteBishop whiteBishop1 = new WhiteBishop();
        whiteBishop1.setPiece(7,2);
        WhiteBishop whiteBishop2 = new WhiteBishop();
        whiteBishop2.setPiece(7,5);

        BlackBishop blackBishop1 = new BlackBishop();
        blackBishop1.setPiece(0,2);
        BlackBishop blackBishop2 = new BlackBishop();
        blackBishop2.setPiece(0,5);

        //knights
        WhiteKnight whiteKnight1 = new WhiteKnight();
        whiteKnight1.setPiece(7,1);
        WhiteKnight whiteKnight2 = new WhiteKnight();
        whiteKnight2.setPiece(7,6);

        BlackKnight blackKnight1 = new BlackKnight();
        blackKnight1.setPiece(0,1);
        BlackKnight blackKnight2 = new BlackKnight();
        blackKnight2.setPiece(0,6);





    }
}

