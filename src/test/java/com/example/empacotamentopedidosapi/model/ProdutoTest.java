package com.example.empacotamentopedidosapi.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProdutoTest {
    
    @Test
    public void deve_retornar_verdadeiro_quando_produto_couber_na_caixa() {
        var produto = new Produto("Produto Teste", new Dimensoes(1 , 2, 3));
        
        var resultado = produto.cabeNaCaixa(new Caixa("Caixa Teste", new Dimensoes(10, 20, 30)));

        assertTrue(resultado);
    }

    @Test
    public void deve_retornar_falso_quando_produto_nao_couber_na_caixa() {
        var produto = new Produto("Produto Teste", new Dimensoes(5 , 30, 10));
        
        var resultado = produto.cabeNaCaixa(new Caixa("Caixa Teste", new Dimensoes(10, 20, 30)));

        assertFalse(resultado);
    }
}
