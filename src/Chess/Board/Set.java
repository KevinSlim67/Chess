package Chess.Board;

import Chess.Board.Board;
import Chess.Pieces.*;

public class Set extends Board {

    public Set(int width, int height) {
        super(width, height);

       // WhiteKing whiteKing = new WhiteKing();
       // whiteKing.setPiece(4, 4);

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
        WhiteBishop bishop1 = new WhiteBishop();
        bishop1.setPiece(7,2);
        WhiteBishop bishop2 = new WhiteBishop();
        bishop2.setPiece(7,5);

        BlackBishop blackBishop1 = new BlackBishop();
        blackBishop1.setPiece(0,2);
        BlackBishop blackBishop2 = new BlackBishop();
        blackBishop2.setPiece(0,5);




    }
}

