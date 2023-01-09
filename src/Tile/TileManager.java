package Tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNumber;
    boolean drawPath = true;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;

        // READ TILE DATA FILE
        InputStream is = getClass().getResourceAsStream("/Resources/Maps/tiledata.txt");
        assert is != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // GETTING TILE NAME AND COLLISION INFO FROM FILE
        String line;

        while ((line = br.readLine()) != null){
            fileNames.add(line);
            collisionStatus.add(br.readLine());
        }
        br.close();

        tile = new Tile[fileNames.size()]; // means how much tiles we will import (have) in our game. Like grass,water,sand,cobble etc.
        getTileImage();

        // GET THE maxWorldCol & Row
        is = getClass().getResourceAsStream("/Resources/Maps/worldmap.txt");
        assert is != null;
        br = new BufferedReader(new InputStreamReader(is));

        String line2 = br.readLine();
        String[] maxTile = line2.split(" ");

        gp.maxWorldCol = maxTile.length;
        gp.maxWorldRow = maxTile.length;

        mapTileNumber = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        br.close();

        loadMap("/Resources/Maps/worldmap.txt",0);
        loadMap("/Resources/Maps/indoor01.txt",1);
    }

    public void getTileImage(){
        for (int i = 0; i < fileNames.size() ; i++) {

            String fileName;
            boolean collision;

            // Get a file name
            fileName = fileNames.get(i);

            // Get a collision status
            collision = collisionStatus.get(i).equals("true");

            setup(i, fileName, collision);
        }
 
    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Tiles/"+ imageName)));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is))); // reading the content of the text file

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine(); //reads a line of text

                while (col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]); // use col as an index for number[] array

                    mapTileNumber[map][col][row] = num; // we store the extracted number in the mapTileNum[][]
                    col++; // continue this until everything in numbers[] is stored in the mapTileNum[][]
                }

                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception ignored){

        }
    }

    public void draw(Graphics2D g2){

        //automating the process of drawing tiles
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            //the map data has been stored in the mapTileNum[][]
            int tileNum = mapTileNumber[gp.currentMap][worldCol][worldRow]; // extract a tile number which is stored in mapTileNum[0][0]

            //offsets - differences
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // as long as a tile is in this boundary, we draw it
            // boosts the game performance
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                // doesn't need to scale the images during the game-loop anymore
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

        // Drawing the nodes in the pathList
        // DEBUG - Whole if-statement can be commented
        if (drawPath){
            g2.setColor(new Color(255,0,0,25));

            for (int i = 0; i < gp.pFinder.pathList.size() ; i++) {
                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
}