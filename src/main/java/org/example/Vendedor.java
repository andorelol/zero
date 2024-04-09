package org.example;

import java.util.HashMap;
import java.util.Map;

public class Vendedor {
    private String nome;
    private Map<Produto, Integer> vendas;

    public Vendedor(String nome) {
        this.nome = nome;
        this.vendas = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void registrarVenda(Produto produto, int quantidade) {
        vendas.put(produto, vendas.getOrDefault(produto, 0) + quantidade);
    }

    public int getTotalVendido(Produto produto) {
        return vendas.getOrDefault(produto, 0);
    }

    public Map<Produto, Integer> getVendas() {
        return vendas;
    }
}
