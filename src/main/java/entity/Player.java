package entity;

import controller.Controller;
import core.Position;
import core.Size;
import gfx.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MovingEntity {

    private final Sprite sprite;
    public Player(Controller controller, Sprite sprite, Position position, Size size, int canvasWidth, int canvasHeight) {
        super(controller, position, size, canvasWidth, canvasHeight);
        this.sprite = sprite;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public Image getSprite() {
        Image spriteImage = sprite.getSprite();
        BufferedImage image = new BufferedImage(
                size.getWidth(),
                size.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.addRenderingHints(
                new RenderingHints(
                        RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY
                )
        );
        graphics2D.drawImage(spriteImage, 0 ,0, size.getWidth(), size.getHeight(), null);
        graphics2D.dispose();
        return image;
    }
}
