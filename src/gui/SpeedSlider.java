package gui;

import javax.swing.*;
import java.awt.*;

public class SpeedSlider extends JSlider {
    public SpeedSlider(int min, int max) {
        super(min, max);
        setBackground(Color.BLACK);
        setInverted(true);
        setPreferredSize(new Dimension(80, 40));
    }
}
