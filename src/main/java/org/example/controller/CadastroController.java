package org.example.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.example.service.UserService;
import org.example.view.CadastroView;

import java.sql.SQLException;

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

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ATENÇÃO!");
                alert.setHeaderText("Atenção! As informações" +
                        " não pode estar vazias");
                alert.setContentText("Verifique as informações e tente novamente.");
                alert.showAndWait();

            } else {
                try {

                    user.createUser(view.getTxUsuario().getText(),
                            view.getTxEmail().getText());
                    view.getStage().close();

                } catch (SQLException ex) {
                    ex.printStackTrace();

                    Platform.runLater(() -> {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR!");
                        alert.setHeaderText("Um possível erro aconteceu" +
                                " com banco de dados");
                        alert.setContentText("Tente novamente mais tarde.");
                        alert.showAndWait();

                    });

                } catch (RuntimeException r) {

                    r.printStackTrace();

                    Platform.runLater(() -> {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR!");
                        alert.setHeaderText("Um possível erro aconteceu" +
                                " com nome de usuário ou email");
                        alert.setContentText("Verifique o nome de usuário" +
                                " e o email e tente novamente.");
                        alert.showAndWait();

                    });
                }
            }
        });
        view.getBtSair().setCancelButton(true);
        view.getBtSair().setOnAction(e -> {
            view.getStage().close();
        });
    }
}
