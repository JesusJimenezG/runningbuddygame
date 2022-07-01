package game;

import controller.PlayerController;
import core.Constants;
import core.Position;
import core.Size;
import display.Display;
import entity.GameObject;
import entity.Obstacle;
import entity.Player;
import gfx.SpriteLibrary;
import input.Input;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements Constants {
    private boolean playing;
    private final Display display;
    private final List<GameObject> gameObjects;
    private final GameScore gameScore;

    public Game(int width, int height) {
        Input input = new Input();
        this.display = new Display(width, height, this, input);
        gameObjects = new ArrayList<>();
        SpriteLibrary spriteLibrary = new SpriteLibrary();
        gameObjects.add(
                new Player(
                        new PlayerController(input), spriteLibrary.getUnits().get("player"),
                        new Position(PLAYER_START_X_POS, PLAYER_START_Y_POS),
                        new Size(PLAYER_WIDTH, PLAYER_HEIGHT),
                        display.getWidth(),
                        display.getHeight()
                )
        );
        gameObjects.add(
                new Obstacle(
                        OBSTACLE_DEFAULT_MOVEMENT_SPEED,
                        true,
                        display.getWidth(),
                        display.getHeight(),
                        Color.GREEN,
                        new Position(GREEN_START_X_POS, GREEN_START_Y_POS),
                        new Size(OBSTACLE_WIDTH, OBSTACLE_HEIGHT)
                )
        );
        gameObjects.add(
                new Obstacle(
                        OBSTACLE_DEFAULT_MOVEMENT_SPEED + 1,
                        true,
                        display.getWidth(),
                        display.getHeight(),
                        Color.BLUE,
                        new Position(BLUE_START_X_POS, BLUE_START_Y_POS),
                        new Size(OBSTACLE_WIDTH, OBSTACLE_HEIGHT)
                )
        );
        gameObjects.add(
                new Obstacle(
                        OBSTACLE_DEFAULT_MOVEMENT_SPEED + 3,
                        false,
                        display.getWidth(),
                        display.getHeight(),
                        Color.RED,
                        new Position(RED_START_X_POS, RED_START_Y_POS),
                        new Size(OBSTACLE_WIDTH, OBSTACLE_HEIGHT)
                )
        );
        gameObjects.add(
                new Obstacle(
                        OBSTACLE_DEFAULT_MOVEMENT_SPEED + 2,
                        false,
                        display.getWidth(),
                        display.getHeight(),
                        Color.YELLOW,
                        new Position(YELLOW_START_X_POS, YELLOW_START_Y_POS),
                        new Size(OBSTACLE_WIDTH, OBSTACLE_HEIGHT)
                )
        );
        gameScore = new GameScore();
    }

    public void update(){
        gameObjects.forEach(GameObject::update);
        gameScore.updateScore(gameObjects);
        display.update(gameScore.getGameScore());
    }

    public void render(){
        display.render();
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setIsPlaying(boolean isPlaying) {
        playing = isPlaying;
    }

    public void reset() {
        setIsPlaying(false);
        gameObjects.get(0).getPosition().setX(PLAYER_START_X_POS);
        gameObjects.get(0).getPosition().setY(PLAYER_START_Y_POS);
        gameObjects.get(1).getPosition().setX(GREEN_START_X_POS);
        gameObjects.get(1).getPosition().setY(GREEN_START_Y_POS);
        gameObjects.get(2).getPosition().setX(BLUE_START_X_POS);
        gameObjects.get(2).getPosition().setY(BLUE_START_Y_POS);
        gameObjects.get(3).getPosition().setX(RED_START_X_POS);
        gameObjects.get(3).getPosition().setY(RED_START_Y_POS);
        gameObjects.get(4).getPosition().setX(YELLOW_START_X_POS);
        gameObjects.get(4).getPosition().setY(YELLOW_START_Y_POS);
        gameScore.setScore(0);
    }
}

