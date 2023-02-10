package src.views;

import src.main.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class LoginView extends JFrame {
    private final JFrame frame;
    private final JTextField usuarioField;
    private final JPasswordField senhaField;

    public LoginView() throws IOException, FontFormatException {
        super();

        // Fonte
        File arquivo = new File("assets/fonts/Poppins-Medium.ttf");
        Font fonte = Font.createFont(Font.TRUETYPE_FONT, arquivo).deriveFont(15f);

        JFrame frame = new JFrame();
        frame.setSize(640, 360);
        frame.setTitle("Zubank - Login");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(95, 159, 159));
        frame.setLayout(null);

        // Usuário
        JLabel usuario = new JLabel("Usuário:");
        usuario.setFont(fonte);
        usuario.setBounds(5, 50, 120,30);
        usuario.setForeground(Color.white);
        frame.add(usuario);

        JTextField usuarioField = new JTextField();
        usuarioField.setText("");
        usuarioField.setBounds(85, 50, 140, 30);
        usuarioField.setVisible(true);
        this.usuarioField = usuarioField;
        frame.add(usuarioField);

        // Senha
        JLabel senha = new JLabel("Senha:");
        senha.setFont(fonte);
        senha.setBounds(5, 115, 120,30);
        senha.setForeground(Color.white);
        frame.add(senha);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setText("");
        senhaField.setBounds(85, 115, 140, 30);
        senhaField.setForeground(Color.black);
        senhaField.setVisible(true);
        this.senhaField = senhaField;
        frame.add(senhaField);

        // Botão de 'login'
        JButton entrar = new JButton("Entrar");
        entrar.setFont(fonte);
        entrar.setBounds(5, 225, 140, 35);
        entrar.setBackground(Color.white);
        entrar.setForeground(Color.black);
        entrar.setVisible(true);
        entrar.addActionListener(this::clickLogin);
        frame.add(entrar);

        // Botão de cadastro
        JButton cadastrar = new JButton("Cadastrar-se");
        cadastrar.setFont(fonte);
        cadastrar.setBounds(155, 225, 140, 35);
        cadastrar.setBackground(Color.white);
        cadastrar.setForeground(Color.black);
        cadastrar.setVisible(true);
        cadastrar.addActionListener(this::clickCadastro);
        frame.add(cadastrar);

        this.frame = frame;
    }

    private void clickLogin(ActionEvent actionEvent) {
        System.out.println("[view] Realizando login.");

        try {
            Login.verificarLogin(this.usuarioField.getText(), this.senhaField.getText(), this.frame);
        } catch (SQLException | IOException | FontFormatException e) {
            System.out.println("[view] Não foi possível realizar login.");
            System.out.println("[erro] " + e.getMessage());

            JOptionPane.showMessageDialog(
                    this.frame,
                    e.getMessage(),
                    "Zubank - Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clickCadastro(ActionEvent actionEvent) {
        System.out.println("[view] Iniciando view de cadastro.");

        try {
            new CadastroView().mostrar();
        } catch (IOException | FontFormatException e) {
            System.out.println("[view] Não foi possível realizar o cadastro.");
            System.out.println("[erro] " + e.getMessage());
        }
    }

    public void mostrar() {
        this.frame.setVisible(true);
    }
}
