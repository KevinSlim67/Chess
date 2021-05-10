package Chess.Pieces;

import Chess.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WhitePawn extends Piece implements ActionListener {

    public WhitePawn(int x, int y) {
        super("white_pawn.png", Board.getCase(x, y), 2);
        possibleX = new int[]{x - 1, x - 2};
        possibleY = new int[]{y, y};


        this.addActionListener(this);
    }

    int clicked = 0;
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this) {
            if (clicked % 2 == 0) {
                for (int i = 0; i < 2; i++) {
                    Board.highlight(possibleX, possibleY, this);
                }
            } else {
                    Board.unHighlightAll();

            }
            clicked++;
        }
    }
}

