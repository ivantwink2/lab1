package main;

import model.CanvasModel;
import presenter.CanvasPresenter;
import view.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CanvasModel model = new CanvasModel(1024, 1024);
            MainView view = new MainView();
            new CanvasPresenter(model, view);
            view.setVisible(true);
        });
    }
}
