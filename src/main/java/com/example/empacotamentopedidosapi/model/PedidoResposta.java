package com.example.empacotamentopedidosapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PedidoResposta {
    private int pedido_id;
    private List<CaixaResultado> caixas;
}