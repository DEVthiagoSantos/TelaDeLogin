package org.example;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.view.LoginView;

public class MainApp extends Application {

    LoginView view = new LoginView();

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        Image imgIcon = new Image(
                getClass().getResourceAsStream("/icone.png"));

        new LoginController(view, stage);
        stage.setScene(view.getScene());
        stage.setResizable(false);
        stage.setTitle("TableApp");
        stage.getIcons().add(imgIcon);
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {

        launch(args);
    }
}
