package src.models.bancos;

import src.main.Banco;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaBancaria {
    private String banco;
    private int numAgencia;
    private int numConta;
    private int digitoConta;
    private double saldo;

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
}
