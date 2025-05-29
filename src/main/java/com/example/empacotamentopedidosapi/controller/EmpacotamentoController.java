package com.example.empacotamentopedidosapi.controller;

import com.example.empacotamentopedidosapi.model.Pedido;
import com.example.empacotamentopedidosapi.model.RespostaPedido;
import com.example.empacotamentopedidosapi.service.EmpacotamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empacotar")
@Tag(name = "Empacotamento", description = "API para empacotar pedidos")
public class EmpacotamentoController {

    @Autowired
    private EmpacotamentoService service;

    @PostMapping
    @Operation(
        summary = "Empacotar um pedido",
        description = "Recebe um pedido (com lista de produtos e dimensões) e retorna a resposta com as caixas utilizadas"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Empacotamento realizado com sucesso",
            content = @Content(schema = @Schema(implementation = RespostaPedido.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida"
        )
    })
    public RespostaPedido empacotar(@RequestBody Pedido pedido) {
        return service.empacotarPedidos(pedido);
    }

}