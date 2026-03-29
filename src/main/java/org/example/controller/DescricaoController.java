package org.example.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.example.exceptions.AppException;
import org.example.exceptions.DatabaseException;
import org.example.exceptions.InvalidUserException;
import org.example.service.UserService;
import org.example.view.AtualizarView;
import org.example.view.DescricaoView;

public class DescricaoController {

    private DescricaoView view;
    private UserService userService;

    public DescricaoController(DescricaoView view,
                               UserService userService) {
        this.view = view;
        this.userService = userService;

        initListeners();
    }

    public void initListeners() {

        view.getBtUpdate().setOnAction(e -> {
            try {
                AtualizarView atualizarView = new AtualizarView(new Stage(), view);
                atualizarView.show(view.getStage());
                new AtualizarController(view, atualizarView, userService);
            } catch (AppException ex) {
                throw new RuntimeException(ex);
            }

        });

        view.getBtDelete().setOnAction(e -> {

            if (!confirmDelete()) return;

            try {

                userService.deleteUser(view.getUser().getIdUser());
                view.getStage().close();

            } catch (InvalidUserException ex) {
                mapAlert(Alert.AlertType.INFORMATION,
                        "ATENÇÃO",
                        "Houve um problema com ID desse usuário",
                        ex.getMessage());
            } catch (DatabaseException ex) {
                mapAlert(Alert.AlertType.ERROR,
                        "ERRO",
                        "Houve um problema com banco de dados",
                        ex.getMessage());
            }
        });
    }

    private boolean confirmDelete() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ATENÇÃO");
        alert.setHeaderText("Você está prestes a deletar um usuário");
        alert.setContentText("Ele será apagado sem chance de volta.");

        return alert.showAndWait()
                .filter(filt ->
                    filt == ButtonType.OK
                ).isPresent();
    }

    public void mapAlert(Alert.AlertType type,
                         String title,
                         String header,
                         String content) {

        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
        });
    }
}
