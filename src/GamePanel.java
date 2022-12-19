import javax.swing.*;
import java.awt.*;

//works as game screen
public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 default size of the player character, NPCs and map tiles
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 16x3 (48x48) pixels on the screen. Pretty common for retro games.

    // 16 tiles horizontally, 12 tiles vertically 4:3 ratio
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //it keeps your program running until you stop it
    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of this class (JPanel)
        this.setBackground(Color.black);

        // if set true, all the drawings from this component will be done in an offscreen painting buffer
        // enabling this can improve game's rendering performance
        this.setDoubleBuffered(true);
    }

    // passing this(GamePanel) to this thread constructor, instantiate
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); // automatically calls the run method
    }

    @Override
    public void run() {

    }
}
