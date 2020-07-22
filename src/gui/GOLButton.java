package gui;

import javax.swing.*;
import java.awt.*;

public class GOLButton extends JButton {
    public GOLButton(String text, Color foregroundColor) {
        super(text);
        setPreferredSize(new Dimension(80, 40));
        setColorScheme(foregroundColor);
    }

    public GOLButton() {
        super();
        setColorScheme(Color.WHITE);
    }

    private void setColorScheme(Color foregroundColor) {
        setBackground(Color.BLACK);
        setForeground(foregroundColor);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
