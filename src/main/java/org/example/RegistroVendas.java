package org.example;

import java.sql.*;
import java.util.*;

public class RegistroVendas {
    // Constantes para conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/andre";
    private static final String USER = "root";
    private static final String PASSWORD = "andre";

    public RegistroVendas() {
    }

    public void registrarVenda(Vendedor vendedor, Produto produto, int quantidade) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Inserir os dados no banco de dados
            String sql = "INSERT INTO vendas (data_venda, vendedor, produto_id, quantidade) VALUES (CURDATE(), ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, vendedor.getNome());
                statement.setInt(2, produto.getId());
                statement.setInt(3, quantidade);
                statement.executeUpdate();
                System.out.println("Venda registrada com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar a venda.");
        }
    }

    public void mostrarVendasPorVendedor() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT vendedor, produto_id, SUM(quantidade) AS total FROM vendas GROUP BY vendedor, produto_id";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Vendas por vendedor:");
                while (resultSet.next()) {
                    String vendedor = resultSet.getString("vendedor");
                    int produtoId = resultSet.getInt("produto_id");
                    int total = resultSet.getInt("total");
                    System.out.println("Vendedor: " + vendedor + ", Produto ID: " + produtoId + ", Total: " + total);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVendasTotais() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT produto_id, SUM(quantidade) AS total FROM vendas GROUP BY produto_id";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Vendas totais:");
                while (resultSet.next()) {
                    int produtoId = resultSet.getInt("produto_id");
                    int total = resultSet.getInt("total");
                    System.out.println("Produto ID: " + produtoId + ", Total: " + total);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package org.example;

import java.sql.*;
import java.util.*;

public class RegistroVendas {
    // Constantes para conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/andre";
    private static final String USER = "root";
    private static final String PASSWORD = "andre";

    public RegistroVendas() {
    }

    public void registrarVenda(Vendedor vendedor, Produto produto, int quantidade) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Inserir os dados no banco de dados
            String sql = "INSERT INTO vendas (data_venda, vendedor, produto_id, quantidade) VALUES (CURDATE(), ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, vendedor.getNome());
                statement.setInt(2, produto.getId());
                statement.setInt(3, quantidade);
                statement.executeUpdate();
                System.out.println("Venda registrada com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar a venda.");
        }
    }

    public void mostrarVendasPorVendedor() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT vendedor, produto_id, SUM(quantidade) AS total FROM vendas GROUP BY vendedor, produto_id";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Vendas por vendedor:");
                while (resultSet.next()) {
                    String vendedor = resultSet.getString("vendedor");
                    int produtoId = resultSet.getInt("produto_id");
                    int total = resultSet.getInt("total");
                    System.out.println("Vendedor: " + vendedor + ", Produto ID: " + produtoId + ", Total: " + total);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVendasTotais() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT produto_id, SUM(quantidade) AS total FROM vendas GROUP BY produto_id";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Vendas totais:");
                while (resultSet.next()) {
                    int produtoId = resultSet.getInt("produto_id");
                    int total = resultSet.getInt("total");
                    System.out.println("Produto ID: " + produtoId + ", Total: " + total);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

