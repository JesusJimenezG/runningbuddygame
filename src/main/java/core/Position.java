package core;

public class Position {
    private double x;
    private double y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int intX(){
        return (int)Math.round(x);
    }
    public int intY(){
        return (int)Math.round(y);
    }
    public void apply(Movement movement) {
        Vector2D vector = movement.getVector();

        if (x > 0 && x < 750){
            x += vector.getX();
        } else if (vector.getX() < 0 && x >= 740){
            x += vector.getX();
        } else if (vector.getX() > 0 && x <= 0) {
            x += vector.getX();
        }

        if (y > 0 && y < 540){
            y += vector.getY();
        }else if (vector.getY() < 0 && y >= 540){
            y += vector.getY();
        } else if (vector.getY() > 0 && y <= 0) {
            y += vector.getY();
        }
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
