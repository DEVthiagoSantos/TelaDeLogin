package org.example.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.example.model.User;

public class TabelaView {

    private BorderPane root = new BorderPane();
    private TextField txPesquisa = new TextField();
    private TableView<User> table = new TableView<>();
    private Button btProximo = new Button("Proximo");
    private Button btAnterior = new Button("Anterior");
    private TableColumn<User, String> tbUserName = new TableColumn<>("Usuário");
    private TableColumn<User, String> tbEmail = new TableColumn<>("Email");;
    private TableColumn<User, Integer> tbIdUser = new TableColumn<>("ID");;


    public TabelaView() {

        HBox hBox = new HBox(txPesquisa, btProximo, btAnterior);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getStyleClass().add("hbox_custom");
        hBox.setSpacing(15);
        hBox.setPrefSize(60, 50);

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

        root.setTop(hBox);
        root.setCenter(table);
        root.setPrefSize(500, 400);

        table.setPrefHeight(300);
        table.getColumns().addAll(tbIdUser, tbUserName, tbEmail);

    }

    public Scene getScene() {

        String css = this.getClass().getResource("/style.css")
                .toExternalForm();

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(css);
        return scene;
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
}
