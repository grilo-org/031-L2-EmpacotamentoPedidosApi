package com.example.empacotamentopedidosapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Produto {
    private String produto_id;
    private Dimensoes dimensoes;

    public boolean cabeNaCaixa(Caixa caixa) {
        return this.dimensoes.getAltura() <= caixa.getDimensoes().getAltura() &&
               this.dimensoes.getLargura() <= caixa.getDimensoes().getLargura() &&
               this.dimensoes.getComprimento() <= caixa.getDimensoes().getComprimento();
    }
}