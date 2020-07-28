package gui;

import javax.swing.*;
import java.awt.*;

public class GOLLabel extends JLabel {
    public GOLLabel(String text) {
        super(text, SwingConstants.CENTER);
        setPreferredSize(new Dimension(80, 40));
        setForeground(Color.CYAN);
    }
}
