package entity;

import core.Position;
import core.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends GameObject{

    private final int movementSpeed;
    private final boolean clockwiseDirection;
    private final BufferedImage image;

    public Obstacle(
            int movementSpeed,
            boolean clockwiseDirection,
            int canvasWidth,
            int canvasHeight,
            Color color,
            Position position,
            Size size
    ) {
        super(position, size, canvasWidth, canvasHeight);
        this.movementSpeed = movementSpeed;
        this.clockwiseDirection = clockwiseDirection;

        image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();

        graphics2D.setColor(color);
        graphics2D.fillRect(0,0, size.getWidth(), size.getHeight());
        graphics2D.dispose();
    }

    @Override
    public void update() {
        if (clockwiseDirection){
            if (position.intX() > canvasWidth){
                position = new Position(-size.getWidth(), position.intY());
            } else {
                position = new Position(position.intX() + movementSpeed, position.intY());
            }
        } else {
            if (position.intX() < -size.getWidth()){
                position = new Position( canvasWidth, position.intY());
            } else {
                position = new Position(position.intX() - movementSpeed, position.intY());
            }
        }
    }
    @Override
    public Image getSprite() {
        return image;
    }
}
