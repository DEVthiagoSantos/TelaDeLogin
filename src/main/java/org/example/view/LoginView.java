package org.example.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView {

    private VBox vbox;
    private TextField txUsuario;
    private PasswordField passSenha;
    private Button btEnter, btSair;
    private Alert alert;

    public LoginView() {

        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(400, 300);
        vbox.getStyleClass().add("login_custom");

        txUsuario = new TextField();
        txUsuario.setPromptText("Digite nome de usuário...");
        txUsuario.getStyleClass().add("text_custom");
        txUsuario.setMaxWidth(150);

        passSenha = new PasswordField();
        passSenha.setPromptText("Digite a senha...");
        passSenha.getStyleClass().add("text_custom");
        passSenha.setMaxWidth(150);

        btEnter = new Button("Enter");
        btEnter.getStyleClass().add("button_padrao");

        btSair = new Button("Sair");
        btSair.getStyleClass().add("button_exit");

        vbox.getChildren().addAll(txUsuario, passSenha, btEnter, btSair);

    }

    public Scene getScene() {

        String css = this.getClass().getResource("/style.css")
                .toExternalForm();

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(css);

        return scene;
    }

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public TextField getTxUsuario() {
        return txUsuario;
    }

    public void setTxUsuario(TextField txUsuario) {
        this.txUsuario = txUsuario;
    }

    public PasswordField getPassSenha() {
        return passSenha;
    }

    public void setPassSenha(PasswordField passSenha) {
        this.passSenha = passSenha;
    }

    public Button getBtEnter() {
        return btEnter;
    }

    public void setBtEnter(Button btEnter) {
        this.btEnter = btEnter;
    }

    public Button getBtSair() {
        return btSair;
    }

    public void setBtSair(Button btSair) {
        this.btSair = btSair;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }
}
