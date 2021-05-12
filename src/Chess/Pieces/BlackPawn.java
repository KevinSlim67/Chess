package Chess.Pieces;

import Chess.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlackPawn extends Piece implements ActionListener {

    public BlackPawn(int x, int y) {
        super("black_pawn.png", Board.getCase(x, y));
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this) {
            Board.unHighlightAll();
            if (!Board.clickedCase[currentX][currentY]) {
                if (currentX != 1) {
                    this.highlight(currentX + 1, currentY, this);
                } else {
                    this.highlight(currentX + 1, currentY, this);
                    this.highlight(currentX + 2, currentY, this);
                }
                if (currentX != 7) {
                    for (int i = currentX + 1; i < currentX + 2; i++) {
                        hasCollision(i, currentY);
                    }
                }
                Board.clickedCase[currentX][currentY] = true;
            } else {
                Board.unHighlightAll();
                Board.clickedCase[currentX][currentY] = false;
            }

        }
    }


    @Override
    public void hasCollision(int x, int y) {
        if (Board.hasPiece[x][y]) {
            for (int i = x; i < 8; i++) {
                this.unHighlight(i, y);
            }
        }
    }
}


