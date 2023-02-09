package src.pessoa;

import src.main.Banco;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuario extends Pessoa {
    private String login;
    private String senha;

    public Usuario(String nome, String telefone, int idade, String cpf, String login, String senha) {
        super(nome, telefone, idade, cpf);
        this.login = login;
        this.senha = senha;
    }

    public static void criar(String nome, String telefone, int idade, String cpf, String login, String senha) throws SQLException {
        Connection conexao = Banco.criarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
                INSERT INTO usuarios (nome, telefone, idade, cpf, login, senha) VALUES (?, ?, ?, ?, ?, ?)
                """);

        stmt.setString(1, nome);
        stmt.setString(2, telefone);
        stmt.setInt(3, idade);
        stmt.setString(4, cpf);
        stmt.setString(5, login);
        stmt.setString(6, senha);

        int resultado = stmt.executeUpdate();

        // A variável `resultado` retorna `1` caso tenha sido executada corretamente.
        // Caso tenha acontecido algum erro, `0` é retornado.
        if (resultado == 1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Usuário cadastrado com sucesso!",
                    "Usuário cadastrado",
                    JOptionPane.PLAIN_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao cadastrar usuário!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
