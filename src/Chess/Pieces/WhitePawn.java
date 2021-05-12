package Chess.Pieces;

import Chess.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WhitePawn extends Piece implements ActionListener {

    public WhitePawn(int x, int y) {
        super("white_pawn.png", Board.getCase(x, y), 2);
        possibleX = new int[]{currentX - 1, currentX - 2};
        possibleY = new int[]{currentY, currentY};


        this.addActionListener(this);
    }

    int clicked = 0;


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this) {
            if (clicked % 2 == 0) {
                if (currentX != 6) {
                    this.highlight(currentX - 1, currentY, this);
                } else {
                    this.highlight(currentX - 1, currentY, this);
                    this.highlight(currentX - 2, currentY, this);
                }
                for (int i = currentX - 1; i >= currentX - 4; i--) {
                    hasCollision(i, currentY);
                }
            } else {
                Board.unHighlightAll();

            }
            clicked++;
        }
    }

    @Override
    public void movement(int curX, int curY, Piece piece) {
        Board.getCase(curX, curY).removeMouseListener(Board.clickCase[curX][curY]);
        Board.clickCase[curX][curY] = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                piece.setPiece(piece, curX, curY);
                setCurrentX(curX);
                setCurrentY(curY);
                Board.unHighlightAll();
            }
        };
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


