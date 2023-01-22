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
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/Resources/Maps/tiledata.txt");
        assert is != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        while ((line = br.readLine()) != null){
            fileNames.add(line);
            collisionStatus.add(br.readLine());
        }
        br.close();

        tile = new Tile[fileNames.size()];
        getTileImage();

        // GET THE maxWorldCol & Row
        is = getClass().getResourceAsStream("/Resources/Maps/dungeonMapNew.txt");
        assert is != null;
        br = new BufferedReader(new InputStreamReader(is));

        String line2 = br.readLine();
        String[] maxTile = line2.split(" ");

        gp.maxWorldCol = maxTile.length;
        gp.maxWorldRow = maxTile.length;

        mapTileNumber = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        br.close();

        loadMap("/Resources/Maps/dungeonMapNew.txt",0);
        loadMap("/Resources/Maps/dungeonLevel2.txt",1);
        loadMap("/Resources/Maps/dungeonLevel3.txt",2);
        loadMap("/Resources/Maps/dungeonLevel4.txt",3);
        loadMap("/Resources/Maps/dungeonLevel5.txt",4);

    }

    public void getTileImage(){
        for (int i = 0; i < fileNames.size() ; i++) {

            String fileName;
            boolean collision;

            fileName = fileNames.get(i);
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
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[map][col][row] = num;
                    col++;
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

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNumber[gp.currentMap][worldCol][worldRow]; // extract a tile number which is stored in mapTileNum[0][0]

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

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