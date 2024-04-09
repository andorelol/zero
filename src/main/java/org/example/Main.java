package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando uma lista de produtos
        List<Produto> listaDeProdutos = new ArrayList<>();

        CadastroProdutos cadastro = new CadastroProdutos(listaDeProdutos);
        RegistroVendas registroVendas = new RegistroVendas();

        // Criando os vendedores
        Vendedor guto = new Vendedor("Guto");
        Vendedor andre = new Vendedor("Andre");
        Vendedor douglas = new Vendedor("Douglas");
        registroVendas.adicionarVendedor(guto);
        registroVendas.adicionarVendedor(andre);
        registroVendas.adicionarVendedor(douglas);

        // Menu principal
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Buscar produto por ID ou nome");
            System.out.println("3. Remover produto por ID");
            System.out.println("4. Editar produto por ID");
            System.out.println("5. Listar todos os produtos");
            System.out.println("6. Vender produto");
            System.out.println("7. Mostrar vendas por vendedor");
            System.out.println("8. Mostrar vendas totais");
            System.out.println("9. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Cadastro de produto
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o preço do produto: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Digite a quantidade em estoque: ");
                    int estoque = scanner.nextInt();
                    Produto novoProduto = new Produto(nome, preco, estoque);
                    cadastro.cadastrarProduto(novoProduto);
                    break;
                case 2:
                    // Busca de produto
                    System.out.print("Digite o ID ou nome do produto a ser buscado: ");
                    String busca = scanner.nextLine();
                    Produto produtoEncontrado = cadastro.buscarProduto(busca);
                    if (produtoEncontrado != null) {
                        System.out.println("Produto encontrado:");
                        System.out.println("ID: " + produtoEncontrado.getId());
                        System.out.println("Nome: " + produtoEncontrado.getNome());
                        System.out.println("Preço: " + produtoEncontrado.getPreco());
                        System.out.println("Estoque: " + produtoEncontrado.getEstoque());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;
                case 3:
                    // Remoção de produto
                    System.out.print("Digite o ID do produto a ser removido: ");
                    int idRemover = scanner.nextInt();
                    cadastro.removerProduto(idRemover);
                    break;
                case 4:
                    // Edição de produto
                    System.out.print("Digite o ID do produto a ser editado: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.print("Digite o novo nome do produto: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Digite o novo preço do produto: ");
                    double novoPreco = scanner.nextDouble();
                    System.out.print("Digite a nova quantidade em estoque: ");
                    int novoEstoque = scanner.nextInt();
                    cadastro.editarProduto(idEditar, novoNome, novoPreco, novoEstoque);
                    break;
                case 5:
                    // Listagem de produtos
                    cadastro.listarProdutos();
                    break;
                case 6:
                    // Venda de produto
                    System.out.print("Digite o ID do produto a ser vendido: ");
                    int idVender = scanner.nextInt();
                    System.out.print("Digite a quantidade a ser vendida: ");
                    int quantidadeVenda = scanner.nextInt();
                    System.out.print("Digite o nome do vendedor (Guto, Andre ou Douglas): ");
                    String nomeVendedor = scanner.next();
                    Vendedor vendedor = null;
                    switch (nomeVendedor.toLowerCase()) {
                        case "guto":
                            vendedor = guto;
                            break;
                        case "andre":
                            vendedor = andre;
                            break;
                        case "douglas":
                            vendedor = douglas;
                            break;
                        default:
                            System.out.println("Vendedor não reconhecido.");
                            break;
                    }
                    if (vendedor != null) {
                        cadastro.venderProduto(idVender, quantidadeVenda, registroVendas, vendedor);
                    }
                    break;
                case 7:
                    // Mostrar vendas por vendedor
                    registroVendas.mostrarVendasPorVendedor();
                    break;
                case 8:
                    // Mostrar vendas totais
                    registroVendas.mostrarVendasTotais();
                    break;
                case 9:
                    // Sair
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
