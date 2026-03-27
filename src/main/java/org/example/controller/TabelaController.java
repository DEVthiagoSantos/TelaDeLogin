package org.example.controller;

import javafx.scene.control.Alert;
import org.example.model.PageResponse;
import org.example.model.User;
import org.example.service.UserService;
import org.example.view.TabelaView;

import java.sql.SQLException;

public class TabelaController {

    private final TabelaView view;
    private final UserService userService;

    private int currentPage = 1;
    private int LIMIT_ITEMS = 10;

    public TabelaController(TabelaView view) {
        this.view = view;
        this.userService = new UserService();

        initListeners();
        carregarDados();
    }

    private void initListeners() {

        view.getTxPesquisa().textProperty()
                .addListener((obs, oldV, newV) ->{
                    currentPage = 1;
                    carregarDados();
                });

        view.getBtProximo().setOnAction(e -> {
            currentPage++;
            carregarDados();
        });

        view.getBtAnterior().setOnAction(e -> {
            currentPage--;
            carregarDados();
        });
    }

    public void carregarDados() {

        try {
            PageResponse<User> response
                    = userService.list(
                            view.getTxPesquisa().getText(),
                    currentPage, LIMIT_ITEMS);

            view.getTable()
                    .getItems().setAll(response.getData());
            view.getBtAnterior()
                    .setDisable(currentPage <= 1);
            view.getBtProximo()
                    .setDisable(currentPage >= response.getTotalPages());

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ATENÇÃO!");
            alert.setHeaderText("Houve um pequeno problema com a tabela");
            alert.setContentText("Tente novamente mais tarde.");
        }
    }
}
