package Chess;

import Chess.Pieces.Piece;
import Chess.Pieces.WhiteKing;
import Chess.Pieces.WhitePawn;

public class Set extends Board {

    public Set(int width, int height) {
        super(width, height);

        WhiteKing whiteKing = new WhiteKing();
        whiteKing.setPiece(whiteKing,4, 4);

        WhitePawn[] whitePawn = new WhitePawn[8];
        for (int i = 0; i < 8; i++) {
            whitePawn[i] = new WhitePawn(6, i);
            whitePawn[i].setPiece(whitePawn[i], 6, i);
            whitePawn[i].setCurrentX(6);
            whitePawn[i].setCurrentY(i);
        }


    }
}

