package org.example.controller;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.view.LoginView;
import org.example.view.TabelaView;

public class LoginController {

    private final LoginView view;
    private Stage stage;

    public LoginController(LoginView view, Stage stage)  {
        this.view = view;
        this.stage = stage;

        initListeners();
    }

    public void initListeners() {

        view.getBtSair().setCancelButton(true);
        view.getBtSair().setOnAction(e -> {
            Platform.exit();
        });

        view.getBtEnter().setDefaultButton(true);
        view.getBtEnter().setOnAction(e -> {
            if (view.getTxUsuario().getText().isBlank()
                    || view.getPassSenha().getText().isBlank()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ATENÇÃO");
                alert.setHeaderText("Nenhum campo pode estar vazio");
                alert.setContentText("Tente novamente");
                alert.showAndWait();

            } else if (view.getTxUsuario().getText().equals("entaoth")
                    && view.getPassSenha().getText().equals("senha")) {

                TabelaView viewTable = new TabelaView();
                new TabelaController(viewTable, stage);

                stage.setScene(viewTable.getScene());

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setHeaderText("Usuário ou Senha estão incorretos");
                alert.setContentText("Tente novamente.");
                alert.showAndWait();

            }

        });
    }

}
