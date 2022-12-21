package Tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10]; // means how much tiles we will import (have) in our game. Like grass,water,sand,cobble etc.
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/Resources/Maps/world01.txt");
    }

    public void getTileImage(){

        // index, image name, collision
        setup(0,"grass", false);
        setup(1,"wall", true);
        setup(2,"water", true);
        setup(3,"earth", false);
        setup(4,"tree", true);
        setup(5,"sand", false);
    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/Tiles/"+ imageName +".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
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

                    mapTileNumber[col][row] = num; // we store the extracted number in the mapTileNum[][]
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
            int tileNum = mapTileNumber[worldCol][worldRow]; // extract a tile number which is stored in mapTileNum[0][0]

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

                // doesn't need to scale the images during the game-loop any more
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}