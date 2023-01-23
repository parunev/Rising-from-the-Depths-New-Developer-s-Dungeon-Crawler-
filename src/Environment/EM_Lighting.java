package Environment;

import Main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class EM_Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;
    public float filterAlpha = 0f;

    public EM_Lighting(GamePanel gp, int circleSize){
        this.gp = gp;

        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        Area screenArea = new Area(new Rectangle2D.Double(0,0,gp.screenWidth, gp.screenHeight));

        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;

        double x = centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        Area lightArea = new Area(circleShape);

        screenArea.subtract(lightArea);

        Color[] color = new Color[5];
        float[] fraction = new float[5];

        color[0] = new Color(0,0,0,0f);
        color[1] = new Color(0,0,0,0.25f);
        color[2] = new Color(0,0,0,0.5f);
        color[3] = new Color(0,0,0,0.75f);
        color[4] = new Color(0,0,0,0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1f;

        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);

        g2.setPaint(gPaint);

        g2.fill(lightArea);

        g2.fill(screenArea);

        g2.dispose();
    }

    public void update(){
        if (gp.player.lightUpdated){
            gp.player.lightUpdated = false;
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(darknessFilter,0,0,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
