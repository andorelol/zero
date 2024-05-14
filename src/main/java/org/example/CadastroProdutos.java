package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroProdutos {
    private List<Produto> produtos;

    public CadastroProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public CadastroProdutos() {
        this.produtos = new ArrayList<>();
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

    public void venderProduto(RegistroVendas registro, List<Vendedor> vendedores) {
        Scanner scanner = new Scanner(System.in);

        // Mostrar lista de produtos disponíveis
        System.out.println("Lista de Produtos Disponíveis:");
        listarProdutos();

        // Solicitar ID do produto a ser vendido
        System.out.print("Digite o ID do produto que deseja vender: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        // Verificar se o produto existe
        Produto produto = buscarProduto(String.valueOf(idProduto));
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        // Solicitar quantidade a ser vendida
        System.out.print("Digite a quantidade que deseja vender: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        // Verificar se a quantidade está disponível no estoque
        if (produto.getEstoque() < quantidade) {
            System.out.println("Quantidade insuficiente em estoque.");
            return;
        }

        // Mostrar lista de vendedores disponíveis
        System.out.println("Lista de Vendedores Disponíveis:");
        for (int i = 0; i < vendedores.size(); i++) {
            System.out.println((i + 1) + ". " + vendedores.get(i).getNome());
        }

        // Solicitar vendedor
        System.out.print("Digite o número do vendedor: ");
        int numeroVendedor = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        // Verificar se o número do vendedor é válido
        if (numeroVendedor < 1 || numeroVendedor > vendedores.size()) {
            System.out.println("Número de vendedor inválido.");
            return;
        }

        // Obter o vendedor selecionado
        Vendedor vendedor = vendedores.get(numeroVendedor - 1);

        // Realizar a venda
        produto.setEstoque(produto.getEstoque() - quantidade);
        registro.registrarVenda(vendedor, produto, quantidade);
        System.out.println("Venda realizada com sucesso!");
    }
}
