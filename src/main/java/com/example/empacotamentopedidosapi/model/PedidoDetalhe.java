package com.example.empacotamentopedidosapi.model;

import lombok.Data;
import java.util.List;

@Data
public class PedidoDetalhe {
    private int pedido_id;
    private List<Produto> produtos;
}