package org.example.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.exceptions.AppException;

public class AtualizarView {

    private VBox vBox;
    private TextField txUsuario, txEmail;
    private Button btAtualizar, btCancelar;

    private Stage stage;
    private DescricaoView view;

    public AtualizarView(Stage stage, DescricaoView view) {
        this.stage = stage;
        this.view = view;
    }

    public void show(Stage owner) throws AppException {

        initComponents();

        stage.initOwner(owner);
        stage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(vBox);

        View.show(stage, scene,
                "Atualizar",
                false);


    }

    public void initComponents() {

        vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(400, 300);
        vBox.getStyleClass().add("login_custom");

        txUsuario = new TextField();
        txUsuario.setPromptText("Novo nome de usuário...");
        txUsuario.getStyleClass().add("text_custom");
        txUsuario.setMaxWidth(150);

        txEmail = new TextField();
        txEmail.setPromptText("Novo email....");
        txEmail.getStyleClass().add("text_custom");
        txEmail.setMaxWidth(150);

        btAtualizar = new Button("Atualizar");
        btAtualizar.getStyleClass().add("button_padrao");
        btAtualizar.setMaxWidth(80);

        btCancelar = new Button("Cancelar");
        btCancelar.getStyleClass().add("button_exit");

        vBox.getChildren().addAll(txUsuario,
                txEmail,
                btAtualizar, btCancelar);

    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public TextField getTxUsuario() {
        return txUsuario;
    }

    public void setTxUsuario(TextField txUsuario) {
        this.txUsuario = txUsuario;
    }

    public TextField getTxEmail() {
        return txEmail;
    }

    public void setTxEmail(TextField txEmail) {
        this.txEmail = txEmail;
    }

    public DescricaoView getView() {
        return view;
    }

    public void setView(DescricaoView view) {
        this.view = view;
    }

    public Button getBtAtualizar() {
        return btAtualizar;
    }

    public void setBtAtualizar(Button btAtualizar) {
        this.btAtualizar = btAtualizar;
    }

    public Button getBtCancelar() {
        return btCancelar;
    }

    public void setBtCancelar(Button btCancelar) {
        this.btCancelar = btCancelar;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
