package Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static JFrame window;
    public static void main(String[] args) throws IOException, FontFormatException {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rising from the Depths: New Developer's Dungeon Crawler");
        new Main().setIcon();

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();

        // causes this window to be sized to fit the preferred size
        // and layouts of its subcomponents (=Main.GamePanel)
        window.pack();

        //not specify the location of the window, the window will be displayed at the center of the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

    public void setIcon(){
        ImageIcon icon = new ImageIcon("/Resources/Player/boy_down_1.png");
        window.setIconImage(icon.getImage());
    }
}