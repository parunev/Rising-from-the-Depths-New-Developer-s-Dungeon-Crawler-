package Environment;

import Main.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    GamePanel gp;
    public EM_Lighting lighting;

    public EnvironmentManager(GamePanel gp){
        this.gp = gp;
    }

    public void setUp(){
        lighting = new EM_Lighting(gp, 450);
    }

    public void update(){
        lighting.update();
    }

    public void draw(Graphics2D g2){
        lighting.draw(g2);
    }
}
