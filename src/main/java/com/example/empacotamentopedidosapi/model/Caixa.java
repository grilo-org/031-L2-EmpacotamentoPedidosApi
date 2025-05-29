package com.example.empacotamentopedidosapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Caixa {
    private String nome;
    private int altura;
    private int largura;
    private int comprimento;
}