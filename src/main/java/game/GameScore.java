package game;

import entity.GameObject;
import entity.Obstacle;
import entity.Player;

import java.util.List;

public class GameScore {
    private int gameScore;
    private boolean isBottomValid = false, isTopValid = true;

    public GameScore() {
        gameScore = 0;
    }

    public void addScore(int score){
        gameScore += score;
    }

    public void decreaseScore(int score){
        gameScore -= score;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void updateScore(List<GameObject> gameObjects) {
        Player player = (Player) gameObjects.get(0);

        for (int i = 1; i < gameObjects.size(); i++) {
            Obstacle obstacle = (Obstacle) gameObjects.get(i);
            if(obstacle.getBounds().intersects(player.getBounds())){
                decreaseScore(10);
                player.getPosition().setX(380);
                player.getPosition().setY(500);
                isTopValid = true;
                isBottomValid = false;
            }
        }

        if (player.getPosition().intY() <= 0){
            if (isTopValid){
                addScore(30);
                isTopValid = false;
                isBottomValid = true;
            }
        }

        if(player.getPosition().intY() >= 540){
            if (isBottomValid){
                addScore(30);
                isBottomValid = false;
                isTopValid = true;
            }
        }
    }

    public void setScore(int score) {
        gameScore = score;
    }
}
