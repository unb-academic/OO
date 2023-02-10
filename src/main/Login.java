package src.main;

import src.views.TelaPrincipalView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static void verificarLogin(
            String usuario,
            String senha,
            JFrame frame
    ) throws SQLException, IOException, FontFormatException {
        Connection conexao = Banco.criarConexao();

        PreparedStatement stmt = conexao.prepareStatement("""
                SELECT * FROM usuarios WHERE login = ? AND senha = ?
                """);

        stmt.setString(1, usuario);
        stmt.setString(2, senha);

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            frame.dispose();
            new TelaPrincipalView(resultSet.getInt("id")).mostrar();
        } else {
            System.out.println("[login] Usuário ou senha incorretos.");

            JOptionPane.showMessageDialog(
                    null,
                    "Usuário ou senha incorretos",
                    "Erro - Zubank",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
