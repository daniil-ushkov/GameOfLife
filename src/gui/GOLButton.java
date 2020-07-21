package gui;

import javax.swing.*;
import java.awt.*;

public class GOLButton extends JButton {
    public GOLButton(String text) {
        super(text);
        setPreferredSize(new Dimension(80, 40));
        setColorScheme();
    }

    public GOLButton() {
        super();
        setColorScheme();
    }

    private void setColorScheme() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
