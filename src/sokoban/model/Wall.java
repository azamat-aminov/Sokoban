package sokoban.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        int leftTopX = getX() - getWidth() / 2;
        int leftTopY = getY() - getHeight() / 2;
        graphics.drawRect(leftTopX, leftTopY, 16, 16);
        graphics.fillRect(leftTopX, leftTopY, 16, 16);
    }
}
