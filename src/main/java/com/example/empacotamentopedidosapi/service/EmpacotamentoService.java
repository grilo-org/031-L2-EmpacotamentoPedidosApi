package com.example.empacotamentopedidosapi.service;

import com.example.empacotamentopedidosapi.model.*;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class EmpacotamentoService {

    private final List<Caixa> caixasDisponiveis = asList(
            new Caixa("Caixa 1", new Dimensoes(30, 40, 80)),
            new Caixa("Caixa 2", new Dimensoes(80, 50, 40)),
            new Caixa("Caixa 3", new Dimensoes(50, 80, 60))
    );

    public RespostaPedido empacotarPedidos(Pedido pedido) {
        var resposta = new RespostaPedido();
        resposta.setPedidos(new ArrayList<>());

        for (PedidoDetalhe pedidoDetalhe : pedido.getPedidos()) {
            var caixasResultado = empacotarProdutosDoPedido(pedidoDetalhe);
            resposta.getPedidos().add(new PedidoResposta(pedidoDetalhe.getPedido_id(), caixasResultado));
        }
        return resposta;
    }

    private List<CaixaResultado> empacotarProdutosDoPedido(PedidoDetalhe pedidoDetalhe) {
        var caixasResultado = new ArrayList<CaixaResultado>();

        var produtosNaoAlocados = new ArrayList<Produto>(pedidoDetalhe.getProdutos());

        alocarProdutosNasCaixas(produtosNaoAlocados, caixasResultado);

        alocarProdutosNaoCabem(produtosNaoAlocados, caixasResultado);

        return caixasResultado;
    }

    private void alocarProdutosNasCaixas(List<Produto> produtosNaoAlocados, List<CaixaResultado> caixasResultado) {
        for (Caixa caixa : caixasDisponiveis) {
            var produtosParaEssaCaixa = new ArrayList<Produto>();

            var iterator = produtosNaoAlocados.iterator();
            while (iterator.hasNext()) {
                var produto = iterator.next();
                if (produto.cabeNaCaixa(caixa)) {
                    produtosParaEssaCaixa.add(produto);
                    iterator.remove();
                }
            }

            if (!produtosParaEssaCaixa.isEmpty()) {
                caixasResultado.add(criarCaixaResultado(caixa.getNome(), produtosParaEssaCaixa));
            }
        }
    }

    private CaixaResultado criarCaixaResultado(String caixaNome, List<Produto> produtos) {
        var caixaResultado = new CaixaResultado();
        caixaResultado.setCaixa_id(caixaNome);
        caixaResultado.setProdutos(produtos.stream()
                .map(Produto::getProduto_id)
                .collect(Collectors.toList()));
        return caixaResultado;
    }

    private void alocarProdutosNaoCabem(List<Produto> produtosNaoAlocados, List<CaixaResultado> caixasResultado) {
        for (Produto produto : produtosNaoAlocados) {
            var caixaResultado = new CaixaResultado();
            caixaResultado.setCaixa_id(null);
            caixaResultado.setProdutos(Collections.singletonList(produto.getProduto_id()));
            caixaResultado.setObservacao("Produto não cabe em nenhuma caixa disponível.");
            caixasResultado.add(caixaResultado);
        }
    }

}