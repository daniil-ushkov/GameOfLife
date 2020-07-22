package gui;

import java.awt.*;
import java.awt.event.ActionListener;

public class SettingPanel extends GOLPanel {

    public SettingPanel(UniversePanel universePanel) {
        super();
        setLayout(new GridLayout(5, 1));
        addButtonWithForegroundColor("START", Color.GREEN, e -> universePanel.start());
        addButtonWithForegroundColor("STOP", Color.RED, e -> universePanel.stop());
        addButton("NEXT", e -> universePanel.next());
        addButton("PREV", e -> universePanel.prev());
        addButton("CLEAR", e -> universePanel.clear());
    }

    private void addButton(String name, ActionListener actionListener) {
        addButtonWithForegroundColor(name, Color.WHITE, actionListener);
    }

    private void addButtonWithForegroundColor(String name, Color foregroundColor, ActionListener actionListener) {
        GOLButton button = new GOLButton(name, foregroundColor);
        button.addActionListener(actionListener);
        add(button);
    }
}
