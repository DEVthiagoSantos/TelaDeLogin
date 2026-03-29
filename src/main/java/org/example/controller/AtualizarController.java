package org.example.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.exceptions.DatabaseException;
import org.example.exceptions.InvalidEmailException;
import org.example.exceptions.InvalidUserException;
import org.example.service.UserService;
import org.example.view.AtualizarView;
import org.example.view.DescricaoView;

public class AtualizarController {

    private DescricaoView decView;
    private AtualizarView upView;
    private UserService user;

    public AtualizarController(DescricaoView view,
                               AtualizarView upView,
                               UserService user) {
        this.decView = view;
        this.upView = upView;
        this.user = user;

        initListeners();
    }

    public void initListeners() {

        upView.getBtCancelar().setCancelButton(true);
        upView.getBtCancelar().setOnAction(e -> {
            upView.getStage().close();
        });

        upView.getBtAtualizar().setOnAction(e -> {


            if (upView.getTxUsuario().getText().isBlank()
                    || upView.getTxEmail().getText().isBlank()) {

                mapAlert(Alert.AlertType.INFORMATION,
                        "ATENÇÃO",
                        "Ambos campos de entrada não podem estar vazios",
                        "Preencha-os e tente novamente.");
            } else {

                if (!confirmUpdate()) return;

                try {

                    user.updateUser(upView.getTxUsuario().getText(),
                            upView.getTxEmail().getText(),
                            decView.getUser().getIdUser());
                    upView.getStage().close();
                    decView.getStage().close();

                } catch (InvalidEmailException ex) {
                    mapAlert(Alert.AlertType.INFORMATION,
                            "ATENÇÃO",
                            "Houve um problema com usuário",
                            ex.getMessage());
                } catch (InvalidUserException ex) {
                    mapAlert(Alert.AlertType.INFORMATION,
                            "Atenção",
                            "Houve um problema com email de usuário",
                            ex.getMessage());
                } catch (DatabaseException ex) {
                    mapAlert(Alert.AlertType.ERROR,
                            "ERRO",
                            "Houve um erro com banco de dados",
                            ex.getMessage());
                }
            }
        });
    }

    private boolean confirmUpdate() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ATENÇÃO");
        alert.setHeaderText("Você está prestes a atualizar um usuário");
        alert.setContentText("OK para continuar.");

        return alert.showAndWait()
                .filter(filt ->
                        filt == ButtonType.OK
                ).isPresent();
    }

    public void mapAlert(Alert.AlertType type,
                    String title,
                    String header,
                    String content) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();

    }


}
