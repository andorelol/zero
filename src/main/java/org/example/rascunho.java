//package org.example; // Corrigindo o nome do pacote
//
//import java.util.*;
//
//// Definição da classe Produto
//class Produto {
//    private static int proximoId = 1;
//    private int id;
//    private String nome;
//    private double preco;
//    private int estoque;
//
//    // Construtor da classe Produto
//    public Produto(String nome, double preco, int estoque) {
//        this.id = proximoId++;
//        this.nome = nome;
//        this.preco = preco;
//        this.estoque = estoque;
//    }
//
//    // Métodos getters e setters para id, nome, preço e estoque
//    public int getId() {
//        return id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public double getPreco() {
//        return preco;
//    }
//
//    public void setPreco(double preco) {
//        this.preco = preco;
//    }
//
//    public int getEstoque() {
//        return estoque;
//    }
//
//    public void setEstoque(int estoque) {
//        this.estoque = estoque;
//    }
//}
//
//// Classe para registrar vendas
//class RegistroVendas {
//    private List<Vendedor> vendedores;
//    private Map<Produto, Integer> vendasTotais;
//
//    public RegistroVendas() {
//        vendedores = new ArrayList<>();
//        vendasTotais = new HashMap<>();
//    }
//
//    public void adicionarVendedor(Vendedor vendedor) {
//        vendedores.add(vendedor);
//    }
//
//    public void registrarVenda(Vendedor vendedor, Produto produto, int quantidade) {
//        vendedor.registrarVenda(produto, quantidade);
//        vendasTotais.put(produto, vendasTotais.getOrDefault(produto, 0) + quantidade);
//    }
//
//    public void mostrarVendasPorVendedor() {
//        System.out.println("Vendas por vendedor:");
//        for (Vendedor vendedor : vendedores) {
//            System.out.println("Vendedor: " + vendedor.getNome());
//            Map<Produto, Integer> vendas = vendedor.getVendas();
//            if (!vendas.isEmpty()) {
//                List<Map.Entry<Produto, Integer>> sortedVendas = new ArrayList<>(vendas.entrySet());
//                sortedVendas.sort((a, b) -> b.getValue().compareTo(a.getValue()));
//                for (Map.Entry<Produto, Integer> entry : sortedVendas) {
//                    Produto produto = entry.getKey();
//                    int quantidade = entry.getValue();
//                    System.out.println(produto.getNome() + ": " + quantidade);
//                }
//            } else {
//                System.out.println("Nenhuma venda registrada.");
//            }
//            System.out.println("--------------------------");
//        }
//    }
//
//    public void mostrarVendasTotais() {
//        System.out.println("Vendas totais:");
//        List<Map.Entry<Produto, Integer>> sortedVendas = new ArrayList<>(vendasTotais.entrySet());
//        sortedVendas.sort((a, b) -> b.getValue().compareTo(a.getValue()));
//        for (Map.Entry<Produto, Integer> entry : sortedVendas) {
//            Produto produto = entry.getKey();
//            int quantidade = entry.getValue();
//            System.out.println(produto.getNome() + ": " + quantidade);
//        }
//    }
//}
//
//// Definição da classe Vendedor
//class Vendedor {
//    private String nome;
//    private Map<Produto, Integer> vendas;
//
//    public Vendedor(String nome) {
//        this.nome = nome;
//        this.vendas = new HashMap<>();
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void registrarVenda(Produto produto, int quantidade) {
//        vendas.put(produto, vendas.getOrDefault(produto, 0) + quantidade);
//    }
//
//    public int getTotalVendido(Produto produto) {
//        return vendas.getOrDefault(produto, 0);
//    }
//
//    public Map<Produto, Integer> getVendas() {
//        return vendas;
//    }
//}
//
//// Classe para registrar produtos
//class CadastroProdutos {
//    private List<Produto> produtos;
//
//    // Construtor da classe CadastroProdutos
//    public CadastroProdutos() {
//        produtos = new ArrayList<>();
//    }
//
//    // Métodos para cadastrar, buscar, remover e editar um produto
//    public void cadastrarProduto(Produto produto) {
//        produtos.add(produto);
//        System.out.println("Produto cadastrado com sucesso!");
//    }
//
//    public Produto buscarProduto(String chave) {
//        for (Produto produto : produtos) {
//            if (produto.getNome().equalsIgnoreCase(chave) || String.valueOf(produto.getId()).equals(chave)) {
//                return produto;
//            }
//        }
//        return null;
//    }
//
//    public void removerProduto(int id) {
//        Produto produto = buscarProduto(String.valueOf(id));
//        if (produto != null) {
//            produtos.remove(produto);
//            System.out.println("Produto removido com sucesso!");
//        } else {
//            System.out.println("Produto não encontrado.");
//        }
//    }
//
//    public void editarProduto(int id, String novoNome, double novoPreco, int novoEstoque) {
//        Produto produto = buscarProduto(String.valueOf(id));
//        if (produto != null) {
//            produto.setNome(novoNome);
//            produto.setPreco(novoPreco);
//            produto.setEstoque(novoEstoque);
//            System.out.println("Produto editado com sucesso!");
//        } else {
//            System.out.println("Produto não encontrado.");
//        }
//    }
//
//    // Método para listar todos os produtos
//    public void listarProdutos() {
//        System.out.println("Lista de Produtos:");
//        for (Produto produto : produtos) {
//            System.out.println("ID: " + produto.getId());
//            System.out.println("Nome: " + produto.getNome());
//            System.out.println("Preço: " + produto.getPreco());
//            System.out.println("Estoque: " + produto.getEstoque());
//            System.out.println("--------------------------");
//        }
//    }
//
//    // Método para vender um produto
//    public void venderProduto(int id, int quantidade, RegistroVendas registro, Vendedor vendedor) {
//        Produto produto = buscarProduto(String.valueOf(id));
//        if (produto != null) {
//            if (produto.getEstoque() >= quantidade) {
//                produto.setEstoque(produto.getEstoque() - quantidade);
//                registro.registrarVenda(vendedor, produto, quantidade);
//                System.out.println("Venda realizada com sucesso!");
//            } else {
//                System.out.println("Quantidade insuficiente em estoque.");
//            }
//        } else {
//            System.out.println("Produto não encontrado.");
//        }
//    }
//}
//
//// Classe principal Main
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        CadastroProdutos cadastro = new CadastroProdutos();
//        RegistroVendas registroVendas = new RegistroVendas();
//
//        // Criando os vendedores
//        Vendedor guto = new Vendedor("Guto");
//        Vendedor andre = new Vendedor("Andre");
//        Vendedor douglas = new Vendedor("Douglas");
//        registroVendas.adicionarVendedor(guto);
//        registroVendas.adicionarVendedor(andre);
//        registroVendas.adicionarVendedor(douglas);
//
//        // Menu principal
//        while (true) {
//            System.out.println("Menu:");
//            System.out.println("1. Cadastrar produto");
//            System.out.println("2. Buscar produto por ID ou nome");
//            System.out.println("3. Remover produto por ID");
//            System.out.println("4. Editar produto por ID");
//            System.out.println("5. Listar todos os produtos");
//            System.out.println("6. Vender produto");
//            System.out.println("7. Mostrar vendas por vendedor");
//            System.out.println("8. Mostrar vendas totais");
//            System.out.println("9. Sair");
//
//            System.out.print("Escolha uma opção: ");
//            int opcao = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (opcao) {
//                case 1:
//                    // Cadastro de produto
//                    System.out.print("Digite o nome do produto: ");
//                    String nome = scanner.nextLine();
//                    System.out.print("Digite o preço do produto: ");
//                    double preco = scanner.nextDouble();
//                    System.out.print("Digite a quantidade em estoque: ");
//                    int estoque = scanner.nextInt();
//                    Produto novoProduto = new Produto(nome, preco, estoque);
//                    cadastro.cadastrarProduto(novoProduto);
//                    break;
//                case 2:
//                    // Busca de produto
//                    System.out.print("Digite o ID ou nome do produto a ser buscado: ");
//                    String busca = scanner.nextLine();
//                    Produto produtoEncontrado = cadastro.buscarProduto(busca);
//                    if (produtoEncontrado != null) {
//                        System.out.println("Produto encontrado:");
//                        System.out.println("ID: " + produtoEncontrado.getId());
//                        System.out.println("Nome: " + produtoEncontrado.getNome());
//                        System.out.println("Preço: " + produtoEncontrado.getPreco());
//                        System.out.println("Estoque: " + produtoEncontrado.getEstoque());
//                    } else {
//                        System.out.println("Produto não encontrado.");
//                    }
//                    break;
//                case 3:
//                    // Remoção de produto
//                    System.out.print("Digite o ID do produto a ser removido: ");
//                    int idRemover = scanner.nextInt();
//                    cadastro.removerProduto(idRemover);
//                    break;
//                case 4:
//                    // Edição de produto
//                    System.out.print("Digite o ID do produto a ser editado: ");
//                    int idEditar = scanner.nextInt();
//                    scanner.nextLine(); // Consumir a nova linha
//                    System.out.print("Digite o novo nome do produto: ");
//                    String novoNome = scanner.nextLine();
//                    System.out.print("Digite o novo preço do produto: ");
//                    double novoPreco = scanner.nextDouble();
//                    System.out.print("Digite a nova quantidade em estoque: ");
//                    int novoEstoque = scanner.nextInt();
//                    cadastro.editarProduto(idEditar, novoNome, novoPreco, novoEstoque);
//                    break;
//                case 5:
//                    // Listagem de produtos
//                    cadastro.listarProdutos();
//                    break;
//                case 6:
//                    // Venda de produto
//                    System.out.print("Digite o ID do produto a ser vendido: ");
//                    int idVender = scanner.nextInt();
//                    System.out.print("Digite a quantidade a ser vendida: ");
//                    int quantidadeVenda = scanner.nextInt();
//                    System.out.print("Digite o nome do vendedor (Guto, Andre ou Douglas): ");
//                    String nomeVendedor = scanner.next();
//                    Vendedor vendedor = null;
//                    switch (nomeVendedor.toLowerCase()) {
//                        case "guto":
//                            vendedor = guto;
//                            break;
//                        case "andre":
//                            vendedor = andre;
//                            break;
//                        case "douglas":
//                            vendedor = douglas;
//                            break;
//                        default:
//                            System.out.println("Vendedor não reconhecido.");
//                            break;
//                    }
//                    if (vendedor != null) {
//                        cadastro.venderProduto(idVender, quantidadeVenda, registroVendas, vendedor);
//                    }
//                    break;
//                case 7:
//                    // Mostrar vendas por vendedor
//                    registroVendas.mostrarVendasPorVendedor();
//                    break;
//                case 8:
//                    // Mostrar vendas totais
//                    registroVendas.mostrarVendasTotais();
//                    break;
//                case 9:
//                    // Sair
//                    System.out.println("Saindo...");
//                    return;
//                default:
//                    System.out.println("Opção inválida. Tente novamente.");
//                    break;
//            }
//        }
//    }
//}
