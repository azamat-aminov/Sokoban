package sokoban.model;

import java.util.HashSet;
import java.util.Set;

public class GameObjects {
    Set<Wall> walls;
    Set<Box> boxes;
    Set<StorageLocation> storageLocations;
    Player player;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<StorageLocation> storageLocations, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.storageLocations = storageLocations;
        this.player = player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> gameObjects = new HashSet<>();
        gameObjects.addAll(walls);
        gameObjects.addAll(boxes);
        gameObjects.addAll(storageLocations);
        gameObjects.add(player);
        return gameObjects;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<StorageLocation> getStorageLocations() {
        return storageLocations;
    }

    public Player getPlayer() {
        return player;
    }
}
