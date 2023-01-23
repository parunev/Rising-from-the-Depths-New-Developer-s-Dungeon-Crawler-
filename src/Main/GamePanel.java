package Main;

import Ai.Pathfinder;
import Data.SaveLoad;
import Entity.Entity;
import Entity.Player;
import Environment.EnvironmentManager;
import Tile.Map;
import Tile.TileManager;
import TileInteractive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
    public final int maxMap = 10; // we can create 10 different maps - can be adjusted
    public int currentMap = 0; // indicates the current map number

    // FPS
    int FPS = 60;

    // SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public Pathfinder pFinder = new Pathfinder(this);
    public EnvironmentManager eManager = new EnvironmentManager(this);
    Map map = new Map(this);
    SaveLoad saveLoad = new SaveLoad(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity[][] obj = new Entity[maxMap][200];
    public Entity[][] npc = new Entity[maxMap][20];
    public Entity[][] monster = new Entity[maxMap][200];
    public InteractiveTile[][] iTile = new InteractiveTile[maxMap][50];
    public Entity[][] projectile = new Entity[maxMap][20];
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;
    public final int cutsceneState = 11;

    // OTHERS
    public boolean bossBattleOn = false;

    public GamePanel() throws IOException, FontFormatException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTiles();
        eManager.setUp();
        gameState = titleState;
    }

    public void resetGame(boolean restart){
        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultPositions();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();

        if (restart){
            player.setDefaultValues();
            aSetter.setObject();
            aSetter.setInteractiveTiles();
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() throws IOException {
        if (gameState == playState){
            // PLAYER
            player.update();

            // NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }

            // MONSTER
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive && !monster[currentMap][i].dying) {
                        monster[currentMap][i].update();
                    }
                    if (!monster[currentMap][i].alive) {
                        monster[currentMap][i].checkDrop(); // when a monster died we check his drop before we set it null
                        monster[currentMap][i] = null;
                    }
                }
            }

            // PROJECTILE
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].alive) {
                        projectile[currentMap][i].update();
                    }
                    if (!projectile[currentMap][i].alive) {
                        projectile[currentMap][i] = null;
                    }
                }
            }

            // PARTICLES
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive) {
                        particleList.get(i).update();
                    }
                    if (!particleList.get(i).alive) {
                        particleList.remove(i);
                    }
                }
            }

            // INTERACTIVE TILES
            for (int i = 0; i < iTile[1].length ; i++) {
                if (iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
            }

            eManager.update();
        }

        // We don't update player's information while the game is paused
        if (gameState == pauseState){

        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // parent class of this class (JPanel)

        // Extends the Graphics class to provide more sophisticated control over
        // geometry, coordinate transformations, color management, and text layout
        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (keyH.showDebugText){
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if (gameState == titleState){
            try {
                ui.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if(gameState == mapState){ // MAP SCREEN
            map.drawFullMapScreen(g2);
        } else { // OTHERS
            // TILE
            tileM.draw(g2);

            // INTERACTIVE TILES
            for (int i = 0; i < iTile[1].length ; i++) {
                if (iTile[currentMap][i] != null){
                    iTile[currentMap][i].draw(g2);
                }
            }

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc[1].length ; i++) {
                if (npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i < obj[1].length ; i++) {
                if (obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }

            for (int i = 0; i < monster[1].length ; i++) {
                if (monster[currentMap][i] != null){
                    entityList.add(monster[currentMap][i]);
                }
            }

            for (int i = 0; i < projectile[1].length ; i++) {
                if (projectile[currentMap][i] != null){
                    entityList.add(projectile[currentMap][i]);
                }
            }

            for (Entity p : particleList) {
                if (p != null) {
                    entityList.add(p);
                }
            }

            // SORT
            entityList.sort(Comparator.comparingInt((Entity e) -> e.worldY));

            // DRAW ENTITIES
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            // EMPTY ENTITY LIST
            entityList.clear();

            // ENVIRONMENT
            eManager.draw(g2);

            // MINI MAP
            map.drawMiniMap(g2);

            // UI
            try {
                ui.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // CUTSCENE
            csManager.draw(g2);
        }

        // DEBUG
        if (keyH.showDebugText){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.WHITE);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX" + player.worldX, x, y); y += lineHeight;
            g2.drawString("WorldY" + player.worldY, x, y); y += lineHeight;
            g2.drawString("COL" + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
            g2.drawString("ROW" + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
            g2.drawString("Draw time: "+ passed, x, y); y += lineHeight;
            g2.drawString("God Mode: "+ keyH.godModeOn, x, y);
        }

        g2.dispose(); // dispose of this graphics context and release any system resources that it is using
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
    
    public void removeTempEntity(){
        for (int mapNum = 0; mapNum < maxMap; mapNum++) {
            for (int i = 0; i < obj[1].length ; i++) {
                if (obj[mapNum][i] != null && obj[mapNum][i].temp){
                    obj[mapNum][i] = null;
                }
            }
        }
    }
}