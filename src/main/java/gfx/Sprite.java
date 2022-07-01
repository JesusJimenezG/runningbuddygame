package gfx;

import java.awt.*;

public class Sprite {
    private Image imageSprite;

    public Sprite() {}

    public void addSprite(Image sprite){
        this.imageSprite = sprite;
    }

    public Image getSprite() {
        return imageSprite;
    }
}

