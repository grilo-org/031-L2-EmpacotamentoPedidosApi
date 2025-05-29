package com.example.empacotamentopedidosapi.controller;

import com.example.empacotamentopedidosapi.model.Pedido;
import com.example.empacotamentopedidosapi.model.RespostaPedido;
import com.example.empacotamentopedidosapi.service.EmpacotamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empacotar")
public class EmpacotamentoController {

    @Autowired
    private EmpacotamentoService service;

    @PostMapping
    public RespostaPedido empacotar(@RequestBody Pedido pedido) {
        return service.empacotarPedidos(pedido);
    }

}