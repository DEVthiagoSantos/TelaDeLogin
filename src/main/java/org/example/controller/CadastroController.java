package org.example.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.example.exceptions.*;
import org.example.service.UserService;
import org.example.view.CadastroView;

public class CadastroController {

    private final CadastroView view;
    private final UserService user;

    private Alert alert;

    public CadastroController(CadastroView view,
                              UserService user) {
        this.view = view;
        this.user = user;

        initListeners();
    }

    public void initListeners() {

        view.getBtCadastrar().setDefaultButton(true);
        view.getBtCadastrar().setOnAction(e -> {
            if (view.getTxUsuario().getText().isBlank()
                    || view.getTxEmail().getText().isBlank()) {

                alertMap(Alert.AlertType.INFORMATION,
                        "ATENÇÃO",
                        "Possivelmente você esqueceu algum " +
                                "campo.",
                        "Verifique email e usuário, e tente novamente.");

            } else {
                try {

                    user.createUser(view.getTxUsuario().getText(),
                            view.getTxEmail().getText());
                    view.getStage().close();

                } catch (ValidationException validation) {

                    alertMap(Alert.AlertType.INFORMATION,
                                "ATENÇÃO",
                                "Dados inválidos",
                                validation.getMessage());

                } catch (DatabaseException ex) {

                    alertMap(Alert.AlertType.INFORMATION,
                            "ATENÇÃO",
                            "Um possível erro interno aconteceu" +
                                    " com banco de dados.",
                            ex.getMessage());
                }
            }
        });
        view.getBtSair().setCancelButton(true);
        view.getBtSair().setOnAction(e -> {
            view.getStage().close();
        });
    }

    public void alertMap(Alert.AlertType type,
                         String title,
                         String header,
                         String content) {

        Platform.runLater(() -> {

            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }
}
