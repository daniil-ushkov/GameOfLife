package gui;

import java.awt.*;
import java.awt.event.ActionListener;

public class SettingPanel extends GOLPanel {

    private SpeedSlider speedSlider;
    private static final GridBagConstraints constraints = new GridBagConstraints();
    static {
        constraints.gridwidth = GridBagConstraints.REMAINDER;
    }

    public SettingPanel(UniversePanel universePanel) {
        super();
        setLayout(new GridBagLayout());
        addLabel("Control");
        addButtonWithForegroundColor("START", Color.GREEN,
                e -> universePanel.start(() -> speedSlider.getValue()));
        addButtonWithForegroundColor("STOP", Color.RED, e -> universePanel.stop());
        speedSlider = new SpeedSlider(100, 500);
        add(speedSlider);
        addButton("NEXT", e -> universePanel.next());
        addButton("PREV", e -> universePanel.prev());
        addButton("CLEAR", e -> universePanel.clear());
        addLabel("Scale");
        addButton("+", e -> universePanel.bigger());
        addButton("-", e -> universePanel.smaller());
    }

    @Override
    public Component add(Component component) {
        super.add(component, constraints);
        return component;
    }

    private void addLabel(String text) {
        GOLLabel label = new GOLLabel(text);
        add(label);
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
