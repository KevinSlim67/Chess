package Chess;

import Chess.Board.BoardBackground;
import Chess.Board.Set;
import Chess.Board.TurnIndicator;
import Chess.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    JPanel[] panel = new JPanel[4];
    public static TurnIndicator turnIndicator;
    JPanel mainPanel;
    public Frame(int width, int height, String name) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x341c00));
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setSize(width, height);
        this.setTitle(name);
        this.setVisible(true);

        mainPanel = new BoardBackground();
        mainPanel.setLayout(new BorderLayout());

        for (int i = 0; i < 4; i++) {
            panel[i] = new JPanel();
            panel[i].setOpaque(false);
            panel[i].setPreferredSize(new Dimension(150, 150));
            panel[i].setVisible(true);
        }

        turnIndicator = new TurnIndicator();
        panel[0].add(turnIndicator);

        //west border
        panel[3].setLayout(new FlowLayout());

        mainPanel.add(panel[0], BorderLayout.NORTH);
        mainPanel.add(panel[1], BorderLayout.SOUTH);
        mainPanel.add(panel[2], BorderLayout.EAST);
        mainPanel.add(panel[3], BorderLayout.WEST);

        mainPanel.add(new Set(20, 20), BorderLayout.CENTER);
        mainPanel.setVisible(true);

        this.add(mainPanel);
        this.setVisible(true);
    }

    public TurnIndicator getTurnIndicator() {
        return turnIndicator;
    }

    public void addPanelWest(Piece piece) {
        panel[3].add(piece);
        panel[3].repaint();
    }

    public void addPanelEast(Piece piece){
        panel[2].add(piece);
        panel[2].repaint();
    }

}
