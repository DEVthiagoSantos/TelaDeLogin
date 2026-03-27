package org.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.model.PageResponse;
import org.example.model.User;
import org.example.service.UserService;

import java.sql.SQLException;

public class TabelaApp extends Application {

    private int currentPage = 1;
    private final int LIMIT_ITEMS = 10;

    private BorderPane borderPane;
    private HBox hBox;
    private TextField txPesquisa;
    private Button btProximo, btAnterior;

    private TableView<User> table = new TableView();

    private final ObservableList<User> data
            = FXCollections.observableArrayList();

    private TableColumn<User, String> tbUserName, tbEmail;
    private TableColumn<User, Integer> tbIdUser;

    private UserService userService;

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        userService = new UserService();

        initComponents();
        carregarDados();
        initListeners();

        /* Irei corrigir o erro ao listar nomes
        * e também adicionar o icone*/

        String css = this.getClass().getResource("/style.css")
                .toExternalForm();

        Image imgIcon = new Image(getClass()
                .getResourceAsStream("/icone.png"));

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(css);
        initLayout();
        stage.setScene(scene);
        stage.getIcons().add(imgIcon);
        stage.setTitle("Tabela de Usuários");
        stage.show();

    }

    public void initComponents() {

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getStyleClass().add("hbox_custom");

        txPesquisa = new TextField();
        txPesquisa.setPromptText("Pesquisar por nome...");
        txPesquisa.getStyleClass().add("text_custom");

        tbUserName = new TableColumn("Usuário");
        tbUserName.setCellValueFactory(
                new PropertyValueFactory<>("userName"));

        tbEmail = new TableColumn("Email");
        tbEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        tbIdUser = new TableColumn("ID");
        tbIdUser.setCellValueFactory(
                new PropertyValueFactory<>("idUser"));

        btProximo = new Button();
        btProximo.setText("Proximo");
        btProximo.getStyleClass().add("button_padrao");

        btAnterior = new Button();
        btAnterior.setText("Anterior");
        btAnterior.setDisable(true);
        btAnterior.getStyleClass().add("button_padrao");

        hBox.getChildren().addAll(txPesquisa, btProximo, btAnterior);

        table.setItems(data);
        table.getColumns().addAll(tbIdUser, tbUserName, tbEmail);


        borderPane = new BorderPane();
        borderPane.setTop(hBox);
        borderPane.setCenter(table);
    }

    public void initData() throws SQLException {

        // Page e Limite de Itens
        PageResponse<User> response
                = userService.list(txPesquisa.getText(),
                        currentPage, LIMIT_ITEMS);

        data.setAll(response.getData());
        btAnterior.setDisable(currentPage <= 1);
        btProximo.setDisable(currentPage >= response.getTotalPages());
    }

    public void initLayout() {

        table.setPrefHeight(300);

        btProximo.setMaxSize(60, 10);
        btAnterior.setMaxSize(60, 10);

        hBox.setSpacing(15);
        hBox.setPrefSize(60, 50);

        borderPane.setPrefSize(500, 400);
    }

    private ObservableList<User> findUsers() {

        ObservableList<User> usuariosEncontrados
                = FXCollections.observableArrayList();

        for (User users : data) {

            if (users.getUserName().contains(txPesquisa.getText())
                    || users.getEmail().contains(txPesquisa.getText())) {
                usuariosEncontrados.add(users);
            }
        }

        return usuariosEncontrados;
    }

    public void initListeners() {

        txPesquisa.textProperty()
                .addListener((observable, oldValue, newValue) ->{
                    currentPage = 1;
                    carregarDados();
                });

        btProximo.setOnAction(e -> {
            currentPage++;
            carregarDados();
        });

        btAnterior.setOnAction(e -> {
            currentPage--;
            carregarDados();
        });
    }

    public void carregarDados() {

        try {
            initData();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ATENÇÃO!");
            alert.setHeaderText("Houve um pequeno problema com a tabela");
            alert.setContentText("Tente novamente mais tarde.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
