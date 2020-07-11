package sokoban.model;


import sokoban.controller.EventListener;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;

public class Model {
    GameObjects gameObjects;
    int currentLevel = 1;
    LevelLoader levelLoader;

    public Model() throws IOException, URISyntaxException {
        final Path levels = getPath();
        levelLoader = new LevelLoader(levels);
    }

    private Path getPath() throws URISyntaxException, IOException {
        final URI uri = ClassLoader.getSystemResource("sokoban/res/levels.txt").toURI();
        if ("jar".equals(uri.getScheme())) {
            for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
                if (provider.getScheme().equalsIgnoreCase("jar")) {
                    try {
                        provider.getFileSystem(uri);
                    } catch (FileSystemNotFoundException e) {
                        provider.newFileSystem(uri, Collections.emptyMap());
                    }
                }
            }
        }
        return Paths.get(uri);
    }

    public EventListener eventListener;
    public static final int BOARD_CELL_SIZE = 20;

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }
        moveIt(player, direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall w : gameObjects.getWalls()) {
            if (gameObject.isCollision(w, direction))
                return true;
        }
        return false;
    }
    //public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
    //        for (Wall w : gameObjects.getWalls()) {
    //            return gameObject.isCollision(w, direction);
    //        }
    //        return false;
    //    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        CollisionObject player = gameObjects.getPlayer();
        //15.2.1. Return true if the player can't move in the specified direction
        for (Box box : gameObjects.getBoxes()) {
            boolean hasBox = player.isCollision(box, direction);
            if (hasBox) {
                // cell is occupied by a box with a wall behind it :return true
                if (checkWallCollision(box, direction))
                    return true;
                // cell is occupied by a box with at box behind it :return true
                for (Box b : gameObjects.getBoxes()) {
                    boolean hasBoxBehindBox = box.isCollision(b, direction);
                    if (hasBoxBehindBox)
                        return true;
                }
                moveIt(box, direction);
            }
        }

        return false;

    }

    public void checkCompletion() {
        int countOccupied = 0;
        for (StorageLocation location : gameObjects.storageLocations) {
            for (Box box : gameObjects.boxes) {
                if (location.getX() == box.getX() && location.getY() == box.getY()) {
                    countOccupied++;
                }
            }
        }

        if (countOccupied == gameObjects.getStorageLocations().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    private void moveIt(GameObject gameObject, Direction direction) {
        switch (direction) {
            case UP:
                gameObject.setY(gameObject.getY() - BOARD_CELL_SIZE);
                break;
            case DOWN:
                gameObject.setY(gameObject.getY() + BOARD_CELL_SIZE);
                break;
            case LEFT:
                gameObject.setX(gameObject.getX() - BOARD_CELL_SIZE);
                break;
            case RIGHT:
                gameObject.setX(gameObject.getX() + BOARD_CELL_SIZE);
                break;
        }
    }
}
