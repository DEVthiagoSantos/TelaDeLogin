package org.example.controller;

import org.example.view.DescricaoView;

public class DescricaoController {

    private DescricaoView view;

    public DescricaoController(DescricaoView view) {
        this.view = view;

        initListeners();
    }

    public void initListeners() {

        view.getBtUpdate().setOnAction(e -> {
            // Atualizar Usuário
        });
    }
}
