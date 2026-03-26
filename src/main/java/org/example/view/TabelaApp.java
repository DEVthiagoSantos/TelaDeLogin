package org.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.model.PageResponse;
import org.example.model.User;
import org.example.service.UserService;

import java.sql.SQLException;

public class TabelaApp extends Application {

    private BorderPane borderPane;
    private HBox hBox;
    private Button btListar;

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
        initData();
        initListeners();

        String css = this.getClass().getResource("/style.css")
                .toExternalForm();

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(css);
        initLayout();
        stage.setScene(scene);
        stage.setTitle("Tabela de Usuários");
        stage.show();

    }

    public void initComponents() {

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getStyleClass().add("hbox_custom");

        btListar = new Button();
        btListar.setText("Listar");
        btListar.getStyleClass().add("button_enter");

        tbUserName = new TableColumn("Usuário");
        tbUserName.setCellValueFactory(
                new PropertyValueFactory<>("userName"));

        tbEmail = new TableColumn("Email");
        tbEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        tbIdUser = new TableColumn("ID");
        tbIdUser.setCellValueFactory(
                new PropertyValueFactory<>("idUser"));

        table.setItems(data);
        table.getColumns().addAll(tbIdUser, tbUserName, tbEmail);

        hBox.getChildren().add(btListar);

        borderPane = new BorderPane();
        borderPane.setTop(hBox);
        borderPane.setCenter(table);
    }

    public void initData() throws SQLException {

        PageResponse<User> response = userService.list(1, 10);

        data.setAll(response.getData());
    }

    public void initLayout() {

        btListar.setLayoutX(100);
        btListar.setLayoutY(60);

        table.setPrefHeight(300);


        hBox.setPrefSize(80, 70);

        borderPane.setPrefSize(500, 400);
    }

    public void initListeners() {
        btListar.setOnAction(e -> {
            try {
                initData();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
