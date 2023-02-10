package src.models.pessoas;

import src.main.Banco;
import src.models.bancos.ContaBancaria;

import javax.swing.*;
import java.sql.*;

public class Usuario extends Pessoa {
    private String login;
    private String senha;

    public Usuario(String nome, String telefone, int idade, String cpf, String login, String senha) {
        super(nome, telefone, idade, cpf);
        this.login = login;
        this.senha = senha;
    }

    public static void criar(
            String nome,
            String telefone,
            int idade,
            String cpf,
            String login,
            String senha,
            JFrame frame
            ) throws SQLException {
        Connection conexao = Banco.criarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
                INSERT INTO usuarios (nome, telefone, idade, cpf, login, senha) VALUES (?, ?, ?, ?, ?, ?)
                """, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, nome);
        stmt.setString(2, telefone);
        stmt.setInt(3, idade);
        stmt.setString(4, cpf);
        stmt.setString(5, login);
        // Nunca insira a senhas diretamente no banco de dados, isso é uma má prática e
        // uma falha de segurança gravíssima. Sempre utilize algum tipo de hash de
        // derivação de chaves como o Bcrypt, Argon2, PBKDF2, etc.
        stmt.setString(6, senha);

        int resultado = stmt.executeUpdate();

        // A variável `resultado` retorna `1` caso tenha sido executada corretamente.
        // Caso tenha acontecido algum erro, `0` é retornado.
        if (resultado == 1) {
            ResultSet resultSet = stmt.getGeneratedKeys();
            int id = resultSet.getInt(1);

            // Criar uma conta bancária associada ao usuário.
            ContaBancaria.criar(id);

            JOptionPane.showMessageDialog(
                    null,
                    "Usuário cadastrado com sucesso!",
                    "Zubank - Usuário cadastrado",
                    JOptionPane.PLAIN_MESSAGE
            );

            frame.dispose();
            System.out.println("[usuário] Usuário cadastrado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao cadastrar usuário!",
                    "Zubank - Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            System.out.println("[usuário] Erro ao cadastrar usuário.");
        }
    }

    public static Usuario get(int idUsuario) throws SQLException {
        Connection conexao = Banco.criarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
                SELECT * FROM usuarios WHERE id = ?
                """);

        stmt.setInt(1, idUsuario);

        ResultSet resultSet = stmt.executeQuery();

        if (!resultSet.next()) {
            return null;
        }
        return new Usuario(
                resultSet.getString("nome"),
                resultSet.getString("telefone"),
                resultSet.getInt("idade"),
                resultSet.getString("cpf"),
                resultSet.getString("login"),
                resultSet.getString("senha")
        );
    }
}
