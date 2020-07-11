package sokoban.model;


import java.awt.*;

public class Player extends CollisionObject implements Movable {


    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
//        graphics.fillOval(10, 10, 50, 50);
//        graphics.setColor(Color.MAGENTA);
        int leftTopX = getX() - getWidth()/2;
        int leftTopY = getY() - getHeight()/2;
        graphics.drawOval(leftTopX, leftTopY, 16, 16);
        graphics.fillOval(leftTopX, leftTopY, 16, 16);
//        graphics.fillOval(this.getX(), this.getY(), getWidth(), getHeight());
    }
}
