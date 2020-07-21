package gui;

import javax.swing.*;
import java.awt.*;

public class GOLFrame extends JFrame {
    public GOLFrame() {
        super("Game of life");
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

    }
}
