package sokoban.view;

import sokoban.controller.Controller;
import sokoban.controller.EventListener;
import sokoban.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private final Controller controller;
    private Board board;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void setEventListener(EventListener eventListener) {
        board.setEventListener(eventListener);
    }

    public void init() {
        board = new Board(this);
        add(board);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
        setVisible(true);
    }

    public void update() {
        board.repaint();
    }

    public void completed(int level) {
        this.update();
        JOptionPane.showMessageDialog(board, level + " - level completed");
        controller.startNextLevel();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }
}