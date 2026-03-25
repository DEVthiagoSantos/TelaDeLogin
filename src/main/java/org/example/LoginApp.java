package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private VBox vbox;
    private TextField txUsuario;
    private PasswordField txSenha;
    private Button btEnter, btSair;
    private Alert alert;

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        initComponents();
        initListeners();

        String css = this.getClass().getResource("/style.css")
                .toExternalForm();

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("AppTeste");
        initLayout();
        stage.show();
        Main.stage = stage;
    }

    public void initComponents() {

        vbox = new VBox();
        vbox.getStyleClass().add("login_custom");

        // Campo de usuário
        txUsuario = new TextField();
        txUsuario.setPromptText("Nome de usuário...");
        txUsuario.getStyleClass().add("text_custom");

        // Campo de senha
        txSenha = new PasswordField();
        txSenha.setPromptText("Senha de usuário...");
        txSenha.getStyleClass().add("text_custom");

        // Campo dos botões
        btEnter = new Button();
        btEnter.setText("Enter");
        btEnter.getStyleClass().add("button_enter");

        btSair = new Button();
        btSair.setText("Sair");
        btSair.getStyleClass().add("button_exit");



        // Inserindo os elementos no painel
        vbox.getChildren().addAll(txUsuario,
                txSenha, btEnter, btSair);
    }

    public void initListeners() {

        btSair.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                fecharApp();
            }
        });
        btEnter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (txUsuario.getText().equals("entaoth")
                        && txSenha.getText().equals("senha")) {
                    // Quero que abra uma nova tela
                    Main.stage.close();
                    // Quando a nova tela abrir, essa vai se fechar
                    // está funcionando
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR!");
                    alert.setHeaderText("Usuário ou Senha incorreta!");
                    alert.setContentText("Tente novamente.");
                    alert.showAndWait();
                    // funcionando
                }
            }
        });
    }

    public void fecharApp() {
        System.exit(0);
    }

    public void initLayout() {

        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(400, 300);

        txUsuario.setMaxWidth(150);

        txSenha.setMaxWidth(150);

        btEnter.setMaxWidth(50);

    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {

        launch(args);
    }
}