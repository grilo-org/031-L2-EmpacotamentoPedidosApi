package com.example.empacotamentopedidosapi.model;

import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
public class CaixaResultado {
    private String caixa_id;
    private List<String> produtos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String observacao;
}