package sokoban.model;

import java.awt.*;

public class StorageLocation extends GameObject {
    public StorageLocation(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
//        graphics.setColor(Color.RED);
//        graphics.drawOval(50, 50, 300, 300);
        graphics.setColor(Color.RED);
        int leftTopX = getX() - getWidth() / 2;
        int leftTopY = getY() - getHeight() / 2;
        graphics.drawOval(leftTopX, leftTopY, getWidth(), getHeight());
//        graphics.drawOval(this.getX(), this.getY(), getWidth(), getHeight());
    }
}
