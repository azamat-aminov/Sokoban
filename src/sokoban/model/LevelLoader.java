package sokoban.model;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        int currentLevel = level % 60 == 0 ? 60 : level % 60;
        List<String> levelInfo = new ArrayList<>();
        String stringLevel = "Maze: " + currentLevel;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(levels)))) {
            String line = "";
            while (!(line.equals(stringLevel))) {
                line = reader.readLine();
            }
            for (int i = 0; i < 7; i++) {
                line = reader.readLine();
            }
            while (!"".equals(line)) {
                levelInfo.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<StorageLocation> storageLocations = new HashSet<>();
        Player player = null;
        for (int i = 0; i < levelInfo.size(); i++) {
            String str = levelInfo.get(i);
            for (int j = 0; j < str.length(); j++) {
                int x = (j == 0) ? Model.BOARD_CELL_SIZE / 2 : (Model.BOARD_CELL_SIZE / 2) + j * Model.BOARD_CELL_SIZE;
                int y = (i == 0) ? Model.BOARD_CELL_SIZE / 2 : (Model.BOARD_CELL_SIZE / 2) + i * Model.BOARD_CELL_SIZE;
                char symbol = str.charAt(j);
                switch (symbol) {
                    case 'X':
                        walls.add(new Wall(x, y));
                        break;
                    case '*':
                        boxes.add(new Box(x, y));
                        break;
                    case '.':
                        storageLocations.add(new StorageLocation(x, y));
                        break;
                    case '&':
                        storageLocations.add(new StorageLocation(x, y));
                        boxes.add(new Box(x, y));
                        break;
                    case '@':
                        player = new Player(x, y);
                        break;
                }
            }
        }
        return new GameObjects(walls, boxes, storageLocations, player);
    }
}