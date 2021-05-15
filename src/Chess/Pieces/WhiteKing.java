package Chess.Pieces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WhiteKing extends Piece implements ActionListener {
    public WhiteKing() {
        super("white_king.png");
        team = 'w';
        this.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            System.out.println("Hello");
        }
    }
}
