package Chess;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static Frame frame;
    public static void main(String args[]) {
        final int width = 650;
        final int height = 650;
        frame = new Frame(width, height, "Chess");
        frame.setLayout(new BorderLayout(0, 0));
    }
}
