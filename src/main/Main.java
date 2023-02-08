package src.main;

import src.views.Login;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        System.out.println("[banco] Criando as tabelas necessárias.");
        Banco.criarTabelas();

        System.out.println("[view] Iniciando a view de login");
        Login login = new Login();
        login.mostrar();
    }
}
