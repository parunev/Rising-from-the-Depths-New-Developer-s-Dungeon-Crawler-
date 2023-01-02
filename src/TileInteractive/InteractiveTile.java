package TileInteractive;

import Entity.Entity;
import Main.GamePanel;

public class InteractiveTile extends Entity {
    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

    }

    // OVERRIDE METHODS
    public boolean isCorrectItem(Entity entity){return false;}
    public void playSe(){}
    public InteractiveTile getDestroyedForm(){return null;}
    public void update(){
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 20){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}
