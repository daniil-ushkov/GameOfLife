package gui;

import java.awt.*;
import java.awt.event.ActionListener;

public class SettingPanel extends GOLPanel {
    public SettingPanel(UniversePanel universePanel) {
        super();
        setLayout(new GridLayout(2, 1));
        addButton("NEXT", e -> universePanel.next());
        addButton("CLEAR", e -> universePanel.clear());
    }

    private void addButton(String name, ActionListener actionListener) {
        GOLButton nextButton = new GOLButton(name);
        nextButton.addActionListener(actionListener);
        add(nextButton);
    }
}
