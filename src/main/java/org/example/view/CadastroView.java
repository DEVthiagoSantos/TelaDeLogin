package org.example.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.exceptions.AppException;

import javax.swing.text.Position;

public class CadastroView {

    private VBox vBox;
    private TextField txUsuario = new TextField();
    private TextField txEmail = new TextField();
    private Button btCadastrar = new Button("Cadastrar");
    private Button btSair = new Button("Sair");

    private Stage stage;

    public void show() throws AppException {

        initComponents();

        Scene scene = new Scene(vBox);
        stage = View.show(scene,
                "CadastroView",
                false);


    }

    public void initComponents() {

        vBox = new VBox(15);
        vBox.setPrefSize(350, 250);
        vBox.setAlignment(Pos.CENTER);
        vBox.getStyleClass().add("login_custom");

        txUsuario.setPromptText("Digite nome de usuário...");
        txUsuario.setMaxWidth(150);
        txUsuario.getStyleClass().add("text_custom");

        txEmail.setPromptText("Digite email de usuário...");
        txEmail.setMaxWidth(150);
        txEmail.getStyleClass().add("text_custom");

        btCadastrar.getStyleClass().add("button_padrao");
        btCadastrar.setMaxWidth(90);
        btSair.getStyleClass().add("button_exit");
        btSair.setMaxWidth(90);

        vBox.getChildren().addAll(txUsuario, txEmail,
                btCadastrar, btSair);
    }

    public Stage getStage() {
        return stage;
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

    public Button getBtCadastrar() {
        return btCadastrar;
    }

    public void setBtCadastrar(Button btCadastrar) {
        this.btCadastrar = btCadastrar;
    }

    public Button getBtSair() {
        return btSair;
    }

    public void setBtSair(Button btSair) {
        this.btSair = btSair;
    }
}
