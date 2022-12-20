package Main;

import Entity.Player;
import Obj.SuperObject;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

//works as game screen
public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 default size of the player character, NPCs and map tiles
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 16x3 (48x48) pixels on the screen. Pretty common for retro games.

    // 16 tiles horizontally, 12 tiles vertically 4:3 ratio
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    // SYSTEM
    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread; //it keeps your program running until you stop it

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10]; // Slots for objects


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of this class (JPanel)
        this.setBackground(Color.black);

        // if set true, all the drawings from this component will be done in an offscreen painting buffer
        // enabling this can improve game's rendering performance
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // with this, this Main.GamePanel can be "focused" to receive key inputs
    }

    // created this method, so we can add other setup stuff in the future
    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }

    // passing this(Main.GamePanel) to this thread constructor, instantiate
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); // automatically calls the run method
    }

    //Game-Loop, core of our game
    @Override
    public void run() {

        // the allocated time for single loop is 0.016
        double drawInterval = 1000000000/FPS; // we can draw the screen 60times per second
        double nextDrawTime = System.nanoTime() + drawInterval; // returns the current value of the running JVM high resolution time source in nanoseconds;

        // as long as this gameThread exists, it repeats the process that is written inside this brackets
        while (gameThread != null){

            // UPDATE: update information such as character position
            update();

            // DRAW: draw the screen with the updated information
            repaint();

            // sleep method, delta method could be used too
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // sleep accepts milliseconds and not nano

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // pause the game loop
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // If the FPS is 30, the program does this 30 times per seconds and so on
        }
    }

    public void update(){
        player.update();
    }

    //Graphics - a class that has many functions to draw objects on screen
    // Imagine this is your paintbrush
    public void paintComponent(Graphics g){
        super.paintComponent(g); // parent class of this class (JPanel)

        // Extends the Graphics class to provide more sophisticated control over
        // geometry, coordinate transformations, color management, and text layout
        Graphics2D g2 = (Graphics2D) g;

        //TILE
        tileM.draw(g2); // first tiles, it's like a layout

        //OBJECT
        for (SuperObject superObject : obj) {
            if (superObject != null) { // otherwise we might get NullPoint error
                superObject.draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose(); // dispose of this graphics context and release any system resources that it is using
    }

    // Loop makes the music play non-stop
    public void playMusic(int i){
        sound.setFile(i); // we set the file we want to play
        sound.play(); // we play
        sound.loop(); // we loop
    }

    // Stops the music at all
    public void stopMusic(){
        sound.stop();
    }

    // Play sound effect
    // Usually the sound effect is very short so we don't call the loop here
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
