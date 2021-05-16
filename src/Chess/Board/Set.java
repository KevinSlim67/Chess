package Chess.Board;

import Chess.Board.Board;
import Chess.Pieces.BlackPawn;
import Chess.Pieces.WhiteKing;
import Chess.Pieces.WhitePawn;

public class Set extends Board {

    public Set(int width, int height) {
        super(width, height);

        WhiteKing whiteKing = new WhiteKing();
        whiteKing.setPiece(4, 4);

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



    }
}

