package org.example.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;
import org.example.exceptions.AppException;
import org.example.exceptions.DatabaseException;
import org.example.model.PageResponse;
import org.example.model.User;
import org.example.service.UserService;
import org.example.view.CadastroView;
import org.example.view.DescricaoView;
import org.example.view.TabelaView;

public class TabelaController {

    private long totalPages;
    private final TabelaView view;
    private final UserService userService;

    private final Stage stage;

    private int currentPage = 1;
    private int LIMIT_ITEMS = 10;

    public TabelaController(TabelaView view, Stage stage) {
        this.view = view;
        this.stage = stage;
        this.userService = new UserService();

        initListeners();
        carregarDados();
    }

    private void initListeners() {

        view.getTxPesquisa().textProperty()
                .addListener((obs, oldV, newV) ->{
                    currentPage = 1;
                    updatePage();
                    carregarDados();
                });

        view.getBtProximo().setOnAction(e -> {
            currentPage++;
            updatePage();
            carregarDados();
        });

        view.getBtAnterior().setOnAction(e -> {
            currentPage--;
            updatePage();
            carregarDados();
        });

        view.getBtCadastrar().setOnAction(e -> {
            CadastroView viewCadastro = new CadastroView(new Stage());
            try {
                viewCadastro.show(view.getStage());
                new CadastroController(viewCadastro, userService);
            } catch (AppException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ATENÇÃO");
                alert.setHeaderText("Ocorreu um erro ao tentar abrir a " +
                        "sessão de cadastro");
                alert.setContentText(ex.getMessage());
            }
        });

        // Selecionar item da tabela
        view.getTable().setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    User user = view.getTable()
                            .getSelectionModel()
                            .getSelectedItem();

                    try {
                        DescricaoView decView = new DescricaoView(view, user, new Stage());
                        decView.show(new Stage());
                        new DescricaoController(decView, userService);
                    } catch (AppException ex) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Descrição");
                        alert.setHeaderText("Houve um problema ao tentar" +
                                " abrir essa sessão");
                        alert.setContentText(ex.getMessage());
                    }
                }
            });

            return row;
        });
    }

    public void carregarDados() {

        try {
            PageResponse<User> response
                    = userService.list(
                            view.getTxPesquisa().getText(),
                    currentPage, LIMIT_ITEMS);

            totalPages = response.getTotalPages();

            view.getTable()
                    .getItems().setAll(response.getData());
            view.getBtAnterior()
                    .setDisable(currentPage <= 1);
            view.getBtProximo()
                    .setDisable(currentPage >= response.getTotalPages());

        } catch (DatabaseException e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ATENÇÃO");
            alert.setHeaderText("Ocorreu um erro com Banco de Dados");
            alert.setContentText(e.getMessage());

        }
    }

    public void updatePage() {
        view.getNumPages().setText(
                currentPage + "/" + totalPages
        );
    }

    public Stage getStage() {
        return stage;
    }

    public long getTotalPages() {
        return totalPages;
    }
}
