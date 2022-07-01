package entity;

import controller.Controller;
import core.Movement;
import core.Position;
import core.Size;

import java.awt.*;

public class MovingEntity extends GameObject{
    private final Controller controller;
    private final Movement movement;

    public MovingEntity(Controller controller, Position position, Size size, int canvasWidth, int canvasHeight) {
        super(position, size, canvasWidth, canvasHeight);
        this.controller = controller;
        this.movement = new Movement(2);
    }

    @Override
    public void update() {
        movement.update(controller);
        position.apply(movement);
    }

    @Override
    public Image getSprite() {
        return null;
    }
}
