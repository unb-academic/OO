package src.main;

import java.sql.*;

public class Banco {
    public static void criarTabelas() throws SQLException {
        Connection conexao = Banco.criarConexao();
        System.out.println("[banco] Conexão com o banco realizada.");

        Statement stmt = conexao.createStatement();

        // Tabela de usuários
        stmt.execute("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER NOT NULL PRIMARY KEY,
                    nome VARCHAR(255),
                    telefone VARCHAR(255),
                    idade INTEGER,
                    cpf VARCHAR(255),
                    login VARCHAR(255) UNIQUE,
                    senha VARCHAR(255)
                )
                """);
        System.out.println("[banco] Tabela `usuarios` criada com sucesso.");

        // Tabela de conta bancária
        stmt.execute("""
                CREATE TABLE IF NOT EXISTS contas_bancarias (
                    id INTEGER NOT NULL PRIMARY KEY,
                    id_usuario INTEGER NOT NULL,
                    banco VARCHAR(255),
                    num_agencia VARCHAR(255),
                    num_conta VARCHAR(255),
                    digito_conta VARCHAR(255),
                    saldo REAL,
                    FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
                )
                """);
        System.out.println("[banco] Tabela `conta_bancaria` criada com sucesso.");
    }

    public static Connection criarConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:sqlite.db");
    }
}
