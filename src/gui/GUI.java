package gui;

public class GUI extends GOLFrame {

    public GUI(int rows, int columns) {
        super();
        UniversePanel universePanel = new UniversePanel(rows, columns);
        SettingPanel settingPanel = new SettingPanel(universePanel);
        getContentPane().add(universePanel);
        getContentPane().add(settingPanel);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
