package Chess.Pieces;

import Chess.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WhitePawn extends Piece implements ActionListener {

    public WhitePawn(int x, int y) {
        super("white_pawn.png", Board.getCase(x, y));
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this) {
            Board.unHighlightAll(); //unhighlights old piece's movement when clicking on a new one
            if (!Board.clickedCase[currentX][currentY]) {
                if (currentX != 6) { //2 possible movements if piece's x = 6, otherwise, only one possible movement
                    this.highlight(currentX - 1, currentY, this);
                } else {
                    this.highlight(currentX - 1, currentY, this);
                    this.highlight(currentX - 2, currentY, this);
                }
                if (currentX != 1) {
                    for (int i = currentX - 1; i >= currentX - 2; i--) {
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
            for (int i = x; i >= 0; i--) {
                this.unHighlight(i, y);
            }
        }
    }
}


