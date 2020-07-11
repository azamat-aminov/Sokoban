package sokoban.model;


import java.awt.*;

public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
//        graphics.setColor(Color.ORANGE);
//        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        graphics.setColor(Color.ORANGE);
        int leftTopX = getX() - getWidth() / 2;
        int leftTopY = getY() - getHeight() / 2;
        graphics.drawRoundRect(leftTopX, leftTopY, 16, 16, 2, 2);
        graphics.fillRoundRect(leftTopX, leftTopY, 16, 16, 2, 2);
//        graphics.drawRect(this.getX(), this.getY(), getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
