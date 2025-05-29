package com.example.empacotamentopedidosapi.model;

import lombok.Data;

@Data
public class Produto {
    private String produto_id;
    private Dimensoes dimensoes;

    public boolean cabeNaCaixa(Caixa caixa) {
        return this.dimensoes.getAltura() <= caixa.getAltura() &&
               this.dimensoes.getLargura() <= caixa.getLargura() &&
               this.dimensoes.getComprimento() <= caixa.getComprimento();
    }
}