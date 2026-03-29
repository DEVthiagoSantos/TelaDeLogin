package org.example.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.model.User;

public class TabelaView {

    private BorderPane root = new BorderPane();
    private TextField txPesquisa = new TextField();
    private Label numPages = new Label("1/");
    private TableView<User> table = new TableView<>();
    private Button btProximo = new Button(">>");
    private Button btAnterior = new Button("<<");
    private Button btCadastrar = new Button("Cadastrar");
    private TableColumn<User, String> tbUserName = new TableColumn<>("Usuário");
    private TableColumn<User, String> tbEmail = new TableColumn<>("Email");
    private TableColumn<User, Integer> tbIdUser = new TableColumn<>("ID");

    private Stage stage;

    public void initComponents() {
        HBox boxPesquisa = new HBox(txPesquisa);
        boxPesquisa.setAlignment(Pos.CENTER_LEFT);
        boxPesquisa.getStyleClass().add("hbox_custom");
        boxPesquisa.setSpacing(15);
        boxPesquisa.setPrefSize(60, 50);

        HBox boxBotoes = new HBox(btAnterior, numPages, btProximo);
        boxBotoes.setAlignment(Pos.CENTER);
        boxBotoes.getStyleClass().add("hbox_custom");
        boxBotoes.setSpacing(15);

        HBox boxCadastrar = new HBox(btCadastrar);
        boxCadastrar.setAlignment(Pos.TOP_CENTER);
        boxCadastrar.getStyleClass().add("hbox_custom");
        boxCadastrar.setSpacing(15);
        boxCadastrar.setPrefSize(70, 40);

        txPesquisa.setPromptText("Pesquisar por nome...");
        txPesquisa.getStyleClass().add("text_custom");


        tbUserName.setCellValueFactory(
                new PropertyValueFactory<>("userName"));

        tbIdUser.setCellValueFactory(
                new PropertyValueFactory<>("idUser"));


        tbEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        btProximo.getStyleClass().add("button_padrao");
        btProximo.setMaxSize(60, 10);

        btAnterior.setDisable(true);
        btAnterior.getStyleClass().add("button_padrao");
        btAnterior.setMaxSize(60, 10);

        btCadastrar.getStyleClass().add("button_padrao");
        btCadastrar.setMaxSize(80, 10);

        root.setTop(boxPesquisa);
        root.setCenter(table);
        root.setBottom(boxBotoes);
        root.setLeft(boxCadastrar);
        root.setPrefSize(600, 500);

        table.setPrefHeight(300);
        table.getColumns().addAll(tbIdUser, tbUserName, tbEmail);
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {

        initComponents();

        String css = this.getClass().getResource("/style.css")
                .toExternalForm();

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(css);
        return scene;
    }

    public Label getNumPages() {
        return numPages;
    }

    public void setNumPages(Label numPages) {
        this.numPages = numPages;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public TextField getTxPesquisa() {
        return txPesquisa;
    }

    public void setTxPesquisa(TextField txPesquisa) {
        this.txPesquisa = txPesquisa;
    }

    public TableView<User> getTable() {
        return table;
    }

    public void setTable(TableView<User> table) {
        this.table = table;
    }

    public Button getBtProximo() {
        return btProximo;
    }

    public void setBtProximo(Button btProximo) {
        this.btProximo = btProximo;
    }

    public Button getBtAnterior() {
        return btAnterior;
    }

    public void setBtAnterior(Button btAnterior) {
        this.btAnterior = btAnterior;
    }

    public TableColumn<User, String> getTbUserName() {
        return tbUserName;
    }

    public void setTbUserName(TableColumn<User, String> tbUserName) {
        this.tbUserName = tbUserName;
    }

    public TableColumn<User, String> getTbEmail() {
        return tbEmail;
    }

    public void setTbEmail(TableColumn<User, String> tbEmail) {
        this.tbEmail = tbEmail;
    }

    public TableColumn<User, Integer> getTbIdUser() {
        return tbIdUser;
    }

    public void setTbIdUser(TableColumn<User, Integer> tbIdUser) {
        this.tbIdUser = tbIdUser;
    }

    public Button getBtCadastrar() {
        return btCadastrar;
    }

    public void setBtCadastrar(Button btCadastrar) {
        this.btCadastrar = btCadastrar;
    }
}
