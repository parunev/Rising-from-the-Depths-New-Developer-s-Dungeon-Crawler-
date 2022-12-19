package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNumber;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10]; // means how much tiles we will import (have) in our game. Like grass,water,sand,cobble etc.
        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/resources/Maps/map01.txt");
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Tiles/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Tiles/water.png")));

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

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine(); //reads a line of text

                while (col < gp.maxScreenCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]); // use col as an index for number[] array

                    mapTileNumber[col][row] = num; // we store the extracted number in the mapTileNum[][]
                    col++; // continue this until everything in numbers[] is stored in the mapTileNum[][]
                }

                if (col == gp.maxScreenCol){
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            //the map data has been stored in the mapTileNum[][]
            int tileNum = mapTileNumber[col][row]; // extract a tile number which is stored in mapTileNum[0][0]

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
