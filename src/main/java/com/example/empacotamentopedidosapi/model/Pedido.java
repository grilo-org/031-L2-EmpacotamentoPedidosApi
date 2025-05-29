package com.example.empacotamentopedidosapi.model;

import lombok.Data;
import java.util.List;

@Data
public class Pedido {
    private List<PedidoDetalhe> pedidos;
}