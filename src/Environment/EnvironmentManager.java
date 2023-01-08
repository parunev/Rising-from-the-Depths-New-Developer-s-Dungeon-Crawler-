package Environment;

import Main.GamePanel;

import java.awt.*;

// This class handles all the environment filters such as Lighting, Rain, Fog etc.
public class EnvironmentManager {
    GamePanel gp;
    EM_Lighting lighting;

    public EnvironmentManager(GamePanel gp){
        this.gp = gp;
    }

    public void setUp(){
        lighting = new EM_Lighting(gp);
    }

    public void update(){
        lighting.update();
    }

    public void draw(Graphics2D g2){
        lighting.draw(g2);
    }
}
