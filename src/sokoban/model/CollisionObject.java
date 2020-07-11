package sokoban.model;


public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }


    public boolean isCollision(GameObject gameObject, Direction direction) {
        int shiftX = 0;
        int shiftY = 0;
        switch (direction) {
            case UP:
                shiftY = shiftY - Model.BOARD_CELL_SIZE;
                break;
            case DOWN:
                shiftY = shiftY + Model.BOARD_CELL_SIZE;
                break;
            case RIGHT:
                shiftX = shiftX + Model.BOARD_CELL_SIZE;
                break;
            case LEFT:
                shiftX = shiftX - Model.BOARD_CELL_SIZE;
                break;
        }
        return (this.getX() + shiftX == gameObject.getX()) && (this.getY() + shiftY == gameObject.getY());
    }
}
