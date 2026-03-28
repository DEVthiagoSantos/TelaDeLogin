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

import javax.swing.text.Position;

public class CadastroView extends Application {

    private VBox vBox;
    private TextField txUsuario = new TextField();
    private TextField txEmail = new TextField();
    private Button btCadastrar = new Button("Cadastrar");
    private Button btSair = new Button("Sair");

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        initComponents();

        String css = this.getClass().getResource("/style.css")
                .toExternalForm();
        Image imgIcon = new Image(
                getClass().getResourceAsStream("/icone.png"));

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Tela de Cadastro");
        stage.getIcons().add(imgIcon);
        stage.show();
        CadastroView.stage = stage;
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
