package org.example.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.exceptions.AppException;
import org.example.model.User;


public class DescricaoView {

    private User user;
    private TabelaView tbView;
    private VBox vbox;
    private Label txUser, txEmail, txId;
    private Button btUpdate, btDelete;
    private ImageView imgView;

    private Stage stage;

    public DescricaoView(TabelaView tbView, User user) {
        this.tbView = tbView;
        this.user = user;
    }

    public void show() throws AppException {

        initComponents();

        Scene scene = new Scene(vbox);

        stage = View.show(scene,
                "Descrição",
                false);

        stage.setOnHiding(e ->
                tbView.getTable()
                .getSelectionModel()
                .clearSelection());
    }

    public void initComponents() {

        vbox = new VBox(15);
        vbox.setPrefSize(232, 268);
        vbox.getStyleClass().add("login_custom");

        Image img = new Image(getClass()
                .getResourceAsStream("/user.png"));

        txUser = new Label("Usuário: "
                + user.getUserName());
        txUser.getStyleClass().add("text_label");
        txEmail = new Label("Email: "
                + user.getEmail());
        txEmail.getStyleClass().add("text_label");
        txId = new Label("ID: "
                + user.getIdUser());
        txId.getStyleClass().add("text_label");

        imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);

        btUpdate = new Button("Atualizar");
        btUpdate.getStyleClass().add("button_update");
        btDelete = new Button("Deletar");
        btDelete.getStyleClass().add("button_exit");

        HBox box = new HBox(15);
        box.setMaxHeight(50);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(btUpdate, btDelete);

        vbox.getChildren().addAll(imgView, txUser, txEmail, txId, box);
    }

    public TabelaView getTbView() {
        return tbView;
    }

    public void setTbView(TabelaView tbView) {
        this.tbView = tbView;
    }

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public Label getTxUser() {
        return txUser;
    }

    public void setTxUser(Label txUser) {
        this.txUser = txUser;
    }

    public Label getTxEmail() {
        return txEmail;
    }

    public void setTxEmail(Label txEmail) {
        this.txEmail = txEmail;
    }

    public Label getTxId() {
        return txId;
    }

    public void setTxId(Label txId) {
        this.txId = txId;
    }

    public Button getBtUpdate() {
        return btUpdate;
    }

    public void setBtUpdate(Button btUpdate) {
        this.btUpdate = btUpdate;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
