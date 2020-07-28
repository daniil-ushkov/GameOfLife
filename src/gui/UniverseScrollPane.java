package gui;

import javax.swing.*;
import java.awt.*;

public class UniverseScrollPane extends JScrollPane {
    public UniverseScrollPane(Component universePanel) {
        super(universePanel);
        setPreferredSize(new Dimension(900, 600));
        getVerticalScrollBar().setUnitIncrement(10);
        getHorizontalScrollBar().setUnitIncrement(10);
    }
}
