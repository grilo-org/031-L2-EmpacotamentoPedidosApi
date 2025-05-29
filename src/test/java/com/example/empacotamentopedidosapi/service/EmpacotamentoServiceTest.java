package com.example.empacotamentopedidosapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.empacotamentopedidosapi.model.Dimensoes;
import com.example.empacotamentopedidosapi.model.Pedido;
import com.example.empacotamentopedidosapi.model.PedidoDetalhe;
import com.example.empacotamentopedidosapi.model.Produto;

public class EmpacotamentoServiceTest {

    private EmpacotamentoService empacotamentoService = new EmpacotamentoService();

    @Test
    public void deve_empacotar_produto_na_caixa_um() {
        var produto = new Produto("Headset", new Dimensoes(25, 15, 20));
        var pedidoDetalhe = new PedidoDetalhe(3, List.of(produto));
        var pedido = new Pedido(List.of(pedidoDetalhe));
        var resultado = empacotamentoService.empacotarPedidos(pedido);
        assertEquals(1, resultado.getPedidos().size());
        assertEquals(1, resultado.getPedidos().get(0).getCaixas().size());
        assertEquals("Caixa 1", resultado.getPedidos().get(0).getCaixas().get(0).getCaixa_id());
    }

    @Test
    public void deve_empacotar_dois_produtos_na_caixa_um() {

        var produto = new Produto("Controle Xbox", new Dimensoes(10, 15, 10));
        var produto2 = new Produto("Carregador", new Dimensoes(3, 8, 8));

        var pedidoDetalhe = new PedidoDetalhe(3, List.of(produto, produto2));
        var pedido = new Pedido(List.of(pedidoDetalhe));
        var resultado = empacotamentoService.empacotarPedidos(pedido);
        assertEquals(1, resultado.getPedidos().size());
        assertEquals(1, resultado.getPedidos().get(0).getCaixas().size());
        assertEquals("Caixa 1", resultado.getPedidos().get(0).getCaixas().get(0).getCaixa_id());
        assertEquals(2, resultado.getPedidos().get(0).getCaixas().get(0).getProdutos().size());
    }

    @Test
    public void deve_empacotar_produto_na_caixa_dois() {
        var produto = new Produto("Teclado Mecânico", new Dimensoes(4, 45, 15));
        var pedidoDetalhe = new PedidoDetalhe(4, List.of(produto));
        var pedido = new Pedido(List.of(pedidoDetalhe));
        var resultado = empacotamentoService.empacotarPedidos(pedido);
        assertEquals(1, resultado.getPedidos().size());
        assertEquals(1, resultado.getPedidos().get(0).getCaixas().size());
        assertEquals("Caixa 2", resultado.getPedidos().get(0).getCaixas().get(0).getCaixa_id());
    }

    @Test
    public void deve_empacotar_produto_na_caixa_tres() {
        var produto = new Produto("Monitor", new Dimensoes(50, 60, 20));
        var pedidoDetalhe = new PedidoDetalhe(6, List.of(produto));
        var pedido = new Pedido(List.of(pedidoDetalhe));
        var resultado = empacotamentoService.empacotarPedidos(pedido);
        assertEquals(1, resultado.getPedidos().size());
        assertEquals(1, resultado.getPedidos().get(0).getCaixas().size());
        assertEquals("Caixa 3", resultado.getPedidos().get(0).getCaixas().get(0).getCaixa_id());
    }

    @Test
    public void deve_retornar_observacao_quando_produto_nao_couber_em_nenhuma_caixa() {
        var produto = new Produto("Mesa", new Dimensoes(100, 100 , 100));
        var pedidoDetalhe = new PedidoDetalhe(99, List.of(produto));
        var pedido = new Pedido(List.of(pedidoDetalhe));
        var resultado = empacotamentoService.empacotarPedidos(pedido);
        assertEquals(1, resultado.getPedidos().size());
        assertEquals(1, resultado.getPedidos().get(0).getCaixas().size());
        assertNull(resultado.getPedidos().get(0).getCaixas().get(0).getCaixa_id());
        assertEquals("Produto não cabe em nenhuma caixa disponível.", resultado.getPedidos().get(0).getCaixas().get(0).getObservacao());
    }
    
}
