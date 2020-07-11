package sokoban.controller;


import sokoban.model.Direction;
import sokoban.model.GameObjects;
import sokoban.model.Model;
import sokoban.view.View;

import java.io.IOException;
import java.net.URISyntaxException;

public class Controller implements EventListener {
    View view;
    Model model;


    public Controller() throws IOException, URISyntaxException {
        model = new Model();
        model.restart();
        model.setEventListener(this);
        view = new View(this);
        view.init();
        view.setEventListener(this);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        new Controller();

    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}
