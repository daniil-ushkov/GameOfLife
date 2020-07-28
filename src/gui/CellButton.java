package gui;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {
    public static final int CHANGE = 5;
    public static final int MAX_SIZE = 80;
    public static final int MIN_SIZE = 10;

    public CellButton() {
        super();
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(20, 20));
    }

    public void bigger() {
        setPreferredSize(new Dimension(
                Math.min(getPreferredSize().width + CHANGE, MAX_SIZE),
                Math.min(getPreferredSize().height + CHANGE, MAX_SIZE)));
    }

    public void smaller() {
        setPreferredSize(new Dimension(
                Math.max(getPreferredSize().width - CHANGE, MIN_SIZE),
                Math.max(getPreferredSize().height - CHANGE, MIN_SIZE)));
    }
}
