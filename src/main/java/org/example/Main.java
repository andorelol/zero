package org.example;
import java.sql.*;
import java.util.Scanner;
import org.example.CadastroProdutos;
import org.example.RegistroVendas;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mariadb://localhost:3306/andre";
    private static final String USER = "root";
    private static final String PASSWORD = "andre";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            createTables(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                exibirMenu();
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        cadastrarProduto(connection, scanner);
                        break;
                    case 2:
                        buscarProduto(connection, scanner);
                        break;
                    case 3:
                        removerProduto(connection, scanner);
                        break;
                    case 4:
                        editarProduto(connection, scanner);
                        break;
                    case 5:
                        listarProdutos(connection);
                        break;
                    case 6:
                        venderProduto(connection, scanner);
                        break;
                    case 7:
                        mostrarVendasPorVendedor(connection, scanner);
                        break;
                    case 8:
                        mostrarVendasTotais(connection);
                        break;
                    case 9:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        String createProdutosTable = "CREATE TABLE IF NOT EXISTS produtos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "preco DOUBLE NOT NULL," +
                "estoque INT NOT NULL)";
        String createVendasTable = "CREATE TABLE IF NOT EXISTS vendas (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "data_venda DATE NOT NULL," +
                "vendedor VARCHAR(255) NOT NULL," +
                "produto_id INT NOT NULL," +
                "quantidade INT NOT NULL," +
                "FOREIGN KEY (produto_id) REFERENCES produtos(id))";
        String createVendedoresTable = "CREATE TABLE IF NOT EXISTS vendedores (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createProdutosTable);
            statement.executeUpdate(createVendasTable);
            statement.executeUpdate(createVendedoresTable);
        }
    }

    private static void exibirMenu() {
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
    }

    private static void cadastrarProduto(Connection connection, Scanner scanner) {
        try {
            System.out.print("Digite o nome do produto: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o preço do produto: ");
            double preco = scanner.nextDouble();
            System.out.print("Digite a quantidade em estoque: ");
            int estoque = scanner.nextInt();

            String sql = "INSERT INTO produtos (nome, preco, estoque) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setDouble(2, preco);
                statement.setInt(3, estoque);
                statement.executeUpdate();
            }

            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void buscarProduto(Connection connection, Scanner scanner) {
        try {
            System.out.print("Digite o ID ou nome do produto a ser buscado: ");
            String busca = scanner.nextLine();

            String sql = "SELECT * FROM produtos WHERE id = ? OR nome = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, busca);
                statement.setString(2, busca);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nome = resultSet.getString("nome");
                        double preco = resultSet.getDouble("preco");
                        int estoque = resultSet.getInt("estoque");

                        System.out.println("ID: " + id + ", Nome: " + nome + ", Preço: " + preco + ", Estoque: " + estoque);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void removerProduto(Connection connection, Scanner scanner) {
        try {
            System.out.print("Digite o ID do produto a ser removido: ");
            int idRemover = scanner.nextInt();

            String sql = "DELETE FROM produtos WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idRemover);
                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Produto removido com sucesso!");
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void editarProduto(Connection connection, Scanner scanner) {
        try {
            System.out.print("Digite o ID do produto a ser editado: ");
            int idEditar = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            System.out.print("Digite o novo nome do produto: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo preço do produto: ");
            double novoPreco = scanner.nextDouble();
            System.out.print("Digite a nova quantidade em estoque: ");
            int novoEstoque = scanner.nextInt();

            String sql = "UPDATE produtos SET nome = ?, preco = ?, estoque = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, novoNome);
                statement.setDouble(2, novoPreco);
                statement.setInt(3, novoEstoque);
                statement.setInt(4, idEditar);
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Produto editado com sucesso!");
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listarProdutos(Connection connection) {
        try {
            String sql = "SELECT * FROM produtos";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    double preco = resultSet.getDouble("preco");
                    int estoque = resultSet.getInt("estoque");

                    System.out.println("ID: " + id + ", Nome: " + nome + ", Preço: " + preco + ", Estoque: " + estoque);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void venderProduto(Connection connection, Scanner scanner) {
        // Implementar a lógica para vender um produto
    }

    private static void mostrarVendasPorVendedor(Connection connection, Scanner scanner) {
        // Implementar a lógica para mostrar as vendas por vendedor
    }

    private static void mostrarVendasTotais(Connection connection) {
        // Implementar a lógica para mostrar as vendas totais
    }
}
