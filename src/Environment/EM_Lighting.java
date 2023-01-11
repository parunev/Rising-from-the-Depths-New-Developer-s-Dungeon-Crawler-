package Environment;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EM_Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;
    public int dayCounter;
    public float filterAlpha = 0f;

    // DAY STATE
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;

    public EM_Lighting(GamePanel gp){
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource(){
        // Create a buffered image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        if (gp.player.currentLight == null){
            g2.setColor(new Color(0,0, 0, 240));
        } else { // player has equipped a lighting item

            // Get the center x and y of the light circle
            int centerX = gp.player.screenX + (gp.tileSize)/2;
            int centerY = gp.player.screenY + (gp.tileSize)/2;

            // Create a gradation effect within the light circle
            // Sizes of the arrays determines the number of divided levels of the gradation
            // so increasing this number makes you create more detailed gradation data
            Color[] color = new Color[5];
            float[] fraction = new float[5];

            // ADJUSTABLE
            color[0] = new Color(0,0,0.01f,0f);
            color[1] = new Color(0,0,0.01f,0.25f);
            color[2] = new Color(0,0,0.01f,0.5f);
            color[3] = new Color(0,0,0.01f,0.75f);
            color[4] = new Color(0,0,0.01f,0.98f);

            // ADJUSTABLE
            // These numbers indicate the distance from the center of the light circle
            fraction[0] = 0f; // center
            fraction[1] = 0.25f;
            fraction[2] = 0.5f;
            fraction[3] = 0.75f;
            fraction[4] = 1f; // edge

            // Create a gradation paint settings for the light circle
            // Circular gradation paint data
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);

            // Set the gradient data on g2
            g2.setPaint(gPaint);
        }

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }

    public void resetDay(){
        dayState = day;
        filterAlpha = 0f;
    }

    public void update(){
        if (gp.player.lightUpdated){
            setLightSource();
            gp.player.lightUpdated = false;
        }

        // Check the state of the day
        if (dayState == day){
            dayCounter++;
            if (dayCounter > 3600){ // adjustable - 600 means 10sec ,1200 means 20sec, 1800 means 30sec etc.
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk){
            filterAlpha += 0.0001f; // adjustable - how fast the effect occurs
            if (filterAlpha > 1f){
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if (dayState == night){
            dayCounter++;

            if (dayCounter > 3600){ // adjustable - 3600/one min
                dayState = dawn;
                dayCounter = 0;
            }
        }
        if (dayState == dawn){
            filterAlpha -= 0.0001f; // adjustable

            if (filterAlpha < 0){
                filterAlpha = 0f;
                dayState = day;
            }
        }
    }

    public void draw(Graphics2D g2){

        if (gp.currentArea == gp.outside){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        }

        if (gp.currentArea == gp.outside || gp.currentArea == gp.dungeon){
            g2.drawImage(darknessFilter,0,0,null);
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
        String situation = switch (dayState) {
            case day -> "Day";
            case dusk -> "Dusk";
            case night -> "Night";
            case dawn -> "Dawn";
            default -> "";
        };

        g2.setColor(Color.BLACK);
        g2.setFont(gp.ui.maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
        g2.drawString(situation, 800, 500);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
        g2.drawString(situation, 800 + 3, 500 + 3);
    }
}
