package src.models.bancos;

import src.main.Banco;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaBancaria {
    private final String banco;
    private final int numAgencia;
    private final int numConta;
    private final int digitoConta;
    private final double saldo;

    public ContaBancaria(String banco, int numAgencia, int numConta, int digitoConta, double saldo) {
        this.banco = banco;
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.digitoConta = digitoConta;
        this.saldo = saldo;
    }

    public static void criar(int idUsuario) throws SQLException {
        Connection conexao = Banco.criarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
                INSERT INTO contas_bancarias (id_usuario) VALUES (?)
                """, PreparedStatement.RETURN_GENERATED_KEYS);

        stmt.setInt(1, idUsuario);

        int resultado = stmt.executeUpdate();

        // A variável `resultado` retorna `1` caso tenha sido executada corretamente.
        // Caso tenha acontecido algum erro, `0` é retornado.
        if (resultado == 1) {
            System.out.println("[conta-bancária] Conta bancária criada com sucesso.");
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao criar conta bancária.",
                    "Zubank - Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            System.out.println("[conta-bancária] Erro ao criar conta bancária.");
        }
    }

    public static ContaBancaria get(int idUsuario) throws SQLException {
        Connection conexao = Banco.criarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
                SELECT * FROM contas_bancarias WHERE id_usuario = ?
                """);

        stmt.setInt(1, idUsuario);

        ResultSet resultSet = stmt.executeQuery();

        if (!resultSet.next()) {
            return null;
        }

        return new ContaBancaria(
                resultSet.getString("banco"),
                resultSet.getInt("num_agencia"),
                resultSet.getInt("num_conta"),
                resultSet.getInt("digito_conta"),
                resultSet.getDouble("saldo")
        );
    }

    public double getSaldo() {
        return this.saldo;
    }
}
