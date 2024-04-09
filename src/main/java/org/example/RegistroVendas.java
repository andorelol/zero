package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroVendas {
    private List<Vendedor> vendedores;
    private Map<Produto, Integer> vendasTotais;

    public RegistroVendas() {
        vendedores = new ArrayList<>();
        vendasTotais = new HashMap<>();
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void registrarVenda(Vendedor vendedor, Produto produto, int quantidade) {
        vendedor.registrarVenda(produto, quantidade);
        vendasTotais.put(produto, vendasTotais.getOrDefault(produto, 0) + quantidade);
    }

    public void mostrarVendasPorVendedor() {
        System.out.println("Vendas por vendedor:");
        for (Vendedor vendedor : vendedores) {
            System.out.println("Vendedor: " + vendedor.getNome());
            Map<Produto, Integer> vendas = vendedor.getVendas();
            if (!vendas.isEmpty()) {
                List<Map.Entry<Produto, Integer>> sortedVendas = new ArrayList<>(vendas.entrySet());
                sortedVendas.sort((a, b) -> b.getValue().compareTo(a.getValue()));
                for (Map.Entry<Produto, Integer> entry : sortedVendas) {
                    Produto produto = entry.getKey();
                    int quantidade = entry.getValue();
                    System.out.println(produto.getNome() + ": " + quantidade);
                }
            } else {
                System.out.println("Nenhuma venda registrada.");
            }
            System.out.println("--------------------------");
        }
    }

    public void mostrarVendasTotais() {
        System.out.println("Vendas totais:");
        List<Map.Entry<Produto, Integer>> sortedVendas = new ArrayList<>(vendasTotais.entrySet());
        sortedVendas.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        for (Map.Entry<Produto, Integer> entry : sortedVendas) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            System.out.println(produto.getNome() + ": " + quantidade);
        }
    }
}
