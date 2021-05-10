package Chess;

import Chess.Pieces.WhiteKing;
import Chess.Pieces.WhitePawn;

public class Set extends Board {

    public Set(int width, int height) {
        super(width, height);

        //setPiece(new WhiteKing(), 7, 3);

        for(int i = 0; i < 8; i++){
            setPiece(new WhitePawn(6, i), 6, i);
        }


    }
}

