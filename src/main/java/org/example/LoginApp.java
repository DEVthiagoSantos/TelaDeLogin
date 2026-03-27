package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.view.TabelaApp;

public class LoginApp extends Application {

    private VBox vbox;
    private TextField txUsuario;
    private PasswordField txSenha;
    private Button btEnter, btSair;
    private Alert alert;

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        initComponents();
        initListener();

        String css = this.getClass().getResource("/style.css")
                .toExternalForm();
        Image iconImage = new Image(
                getClass().getResourceAsStream("/icone.png"));

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(iconImage);
        stage.setTitle("LoginApp");
        initLayout();
        stage.show();
        LoginApp.stage = stage;
    }

    public void initComponents() {
        vbox = new VBox();
        vbox.getStyleClass().add("login_custom");

        txUsuario = new TextField();
        txUsuario.setPromptText("Digite nome de usuário...");
        txUsuario.getStyleClass().add("text_custom");

        txSenha = new PasswordField();
        txSenha.setPromptText("Digite a senha...");
        txSenha.getStyleClass().add("text_custom");

        btEnter = new Button();
        btEnter.setText("Enter");
        btEnter.getStyleClass().add("button_padrao");

        btSair = new Button();
        btSair.setText("Sair");
        btSair.getStyleClass().add("button_exit");

        vbox.getChildren().addAll(txUsuario, txSenha, btEnter, btSair);
    }

    public void initLayout() {

        vbox.setPrefSize(400, 300);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        txUsuario.setMaxWidth(150);
        txSenha.setMaxWidth(150);

        btEnter.setMaxWidth(50);
        btSair.setMaxWidth(50);
    }

    public void initListener() {
        btSair.setCancelButton(true); // Apertar ESC faz com que a tela feche
        btSair.setOnAction(e -> {
            fecharApp();
        });
        btEnter.setDefaultButton(true); // Apertar Enter também funciona como botão
        // Usuario e Senha de teste
        btEnter.setOnAction(e -> {
              if (txUsuario.getText().equals("entaoth")
                      && txSenha.getText().equals("senha")) {

                  try {
                      new TabelaApp().start(new Stage());
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
                  LoginApp.stage.close();
                  // Quando a tela abrir, a de login irá fechar
              } else if (txUsuario.getText().isEmpty()
                      && txSenha.getText().isEmpty()) {
                    // Alerta

                  alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Atenção!");
                  alert.setHeaderText("Usuário ou Senha não podem estar vazios.");
                  alert.setContentText("Tente novamente.");
                  alert.showAndWait();
              } else {
                    // Error

                  alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("ERROR");
                  alert.setHeaderText("Usuário ou Senha estão incorretos.");
                  alert.setContentText("Verifique e tente novamente.");
                  alert.showAndWait();
              }
        });
    }

    public void fecharApp() {
        System.exit(0);
    }


    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {

        launch(args);
    }
}