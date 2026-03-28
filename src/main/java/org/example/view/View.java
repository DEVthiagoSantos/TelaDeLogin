package org.example.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.exceptions.AppException;

public class View {

    public static Stage show(Scene scene,
                     String title,
                     boolean realizable) throws AppException {

        Stage stage1 = new Stage();

        String css = View.class.getResource("/style.css")
                .toExternalForm();
        Image imgIcon = new Image(
                View.class.getResourceAsStream("/icone.png"));

        scene.getStylesheets().add(css);
        stage1.setScene(scene);
        stage1.setResizable(realizable);
        stage1.setTitle(title);
        stage1.getIcons().add(imgIcon);
        stage1.show();

        return stage1;
    }
}
