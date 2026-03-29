package org.example.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.exceptions.AppException;

public class View {

    public static void show(Stage stage, Scene scene,
                     String title,
                     boolean realizable) throws AppException {

        mapShow(stage, scene, title, realizable);
        stage.show();
    }

    public static void showAndWait(Stage stage, Scene scene,
                             String title,
                             boolean realizable) throws AppException {

        mapShow(stage, scene, title, realizable);
        stage.showAndWait();
    }

    public static Stage mapShow(Stage stage1,
                                Scene scene,
                                String title,
                                boolean realizable) {

        String css = View.class.getResource("/style.css")
                .toExternalForm();
        Image imgIcon = new Image(
                View.class.getResourceAsStream("/icone.png"));

        scene.getStylesheets().add(css);
        stage1.setScene(scene);
        stage1.setResizable(realizable);
        stage1.setTitle(title);
        stage1.getIcons().add(imgIcon);

        return stage1;
    }
}
