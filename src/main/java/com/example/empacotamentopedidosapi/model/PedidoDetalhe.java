package com.example.empacotamentopedidosapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoDetalhe {
    private int pedido_id;
    private List<Produto> produtos;
}