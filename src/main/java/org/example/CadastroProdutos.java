package org.example;

import java.util.List;
import java.util.Scanner;

public class CadastroProdutos {
    private List<Produto> produtos;

    public CadastroProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public Produto buscarProduto(String chave) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(chave) || String.valueOf(produto.getId()).equals(chave)) {
                return produto;
            }
        }
        return null;
    }

    public void removerProduto(int id) {
        Produto produto = buscarProduto(String.valueOf(id));
        if (produto != null) {
            produtos.remove(produto);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void editarProduto(int id, String novoNome, double novoPreco, int novoEstoque) {
        Produto produto = buscarProduto(String.valueOf(id));
        if (produto != null) {
            produto.setNome(novoNome);
            produto.setPreco(novoPreco);
            produto.setEstoque(novoEstoque);
            System.out.println("Produto editado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void listarProdutos() {
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Estoque: " + produto.getEstoque());
            System.out.println("--------------------------");
        }
    }

    public void venderProduto(int id, int quantidade, RegistroVendas registro, Vendedor vendedor) {
        Produto produto = buscarProduto(String.valueOf(id));
        if (produto != null) {
            if (produto.getEstoque() >= quantidade) {
                produto.setEstoque(produto.getEstoque() - quantidade);
                registro.registrarVenda(vendedor, produto, quantidade);
                System.out.println("Venda realizada com sucesso!");
            } else {
                System.out.println("Quantidade insuficiente em estoque.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
