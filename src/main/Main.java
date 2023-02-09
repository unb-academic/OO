package src.main;

import src.views.LoginView;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException, SQLException {
        System.out.println("[banco] Criando as tabelas necessárias.");
        Banco.criarTabelas();

        System.out.println("[view] Iniciando a view de login");
        LoginView loginView = new LoginView();
        loginView.mostrar();
    }
}
