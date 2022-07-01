package entity;

import core.Position;
import core.Size;

import java.awt.*;

public abstract class GameObject {
    private final Rectangle bounds;
    protected Position position;
    protected Size size;
    protected int canvasWidth, canvasHeight;

    public GameObject(Position position, Size size, int canvasWidth, int canvasHeight) {
        this.position = position;
        this.size = size;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        bounds = new Rectangle(position.intX(), position.intY(), size.getWidth(), size.getHeight());
    }

    public abstract void update();
    public abstract Image getSprite();

    public Position getPosition() {
        return position;
    }

    public Rectangle getBounds(){
        bounds.x = position.intX();
        bounds.y = position.intY();
        return bounds;
    }
}

