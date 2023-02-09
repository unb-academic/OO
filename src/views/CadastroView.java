package src.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import src.pessoa.*;

public class CadastroView extends JFrame {
    private final JFrame frame;

    private JTextField telefoneField;
    private JTextField nomeField;
    private JTextField idadeField;
    private JTextField cpfField;
    private JTextField usuarioField;
    private JTextField senhaField;

    public CadastroView() throws IOException, FontFormatException {
        super();

        // Fonte
        File arquivo = new File("assets/fonts/Poppins-Medium.ttf");
        Font fonte = Font.createFont(Font.TRUETYPE_FONT, arquivo).deriveFont(15f);

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setTitle("Zubank - Cadastro");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(95, 159, 159));
        frame.setLayout(null);

        // Título
        JLabel titulo = new JLabel( "Insira seus dados:");
        titulo.setBounds(275, -125, 300, 300);
        titulo.setFont(fonte);
        titulo.setForeground(Color.white);
        frame.add(titulo);

        // Nome
        JLabel nome = new JLabel("Nome:");
        nome.setBounds(5, 110, 120, 30);
        nome.setFont(fonte);
        nome.setForeground(Color.white);
        frame.add(nome);

        JTextField nomeField = new JTextField();
        nomeField.setBounds(81, 110, 504, 30);
        nomeField.setVisible(true);
        this.nomeField = nomeField;
        frame.add(nomeField);

        // Telefone
        JLabel telefone = new JLabel("Telefone:");
        telefone.setBounds(5, 160, 120,30);
        telefone.setFont(fonte);
        telefone.setForeground(Color.white);
        frame.add(telefone);

        JTextField telefoneField = new JTextField();
        telefoneField.setText("");
        telefoneField.setBounds(110, 160, 475, 30);
        telefoneField.setVisible(true);
        this.telefoneField = telefoneField;
        frame.add(telefoneField);

        // Idade
        JLabel idade = new JLabel("Idade:");
        idade.setBounds(5, 210, 120,30);
        idade.setFont(fonte);
        idade.setForeground(Color.white);
        frame.add(idade);

        JTextField idadeField = new JTextField();
        idadeField.setText("");
        idadeField.setBounds(75, 210, 510, 30);
        idadeField.setVisible(true);
        this.idadeField = idadeField;
        frame.add(idadeField);

        // CPF
        JLabel cpf = new JLabel("CPF:");
        cpf.setBounds(5, 260, 120,30);
        cpf.setFont(fonte);
        cpf.setForeground(Color.white);
        frame.add(cpf);

        JTextField cpfField = new JTextField();
        cpfField.setText("");
        cpfField.setBounds(62, 260, 523, 30);
        cpfField.setVisible(true);
        this.cpfField = cpfField;
        frame.add(cpfField);

        // Nome de Usuário
        JLabel usuario = new JLabel("Usuário:");
        usuario.setBounds(5, 400, 120,30);
        usuario.setFont(fonte);
        usuario.setForeground(Color.white);
        frame.add(usuario);

        JTextField usuarioField = new JTextField();
        usuarioField.setText("");
        usuarioField.setBounds(100, 400, 250, 30);
        usuarioField.setVisible(true);
        this.usuarioField = usuarioField;
        frame.add(usuarioField);

        // Senha
        JLabel senha = new JLabel("Senha:");
        senha.setBounds(5, 450, 120,30);
        senha.setFont(fonte);
        senha.setForeground(Color.black);
        frame.add(senha);

        JTextField senhaField = new JPasswordField();
        senhaField.setText("");
        senhaField.setBounds(85, 450, 265, 30);
        senhaField.setVisible(true);
        this.senhaField = senhaField;
        frame.add(senhaField);

        // Botão de Cadastro
        JButton cadastrar = new JButton("Cadastrar");
        cadastrar.setFont(fonte);
        cadastrar.setBounds(325, 500, 150, 40);
        cadastrar.setBackground(Color.white);
        cadastrar.setForeground(Color.black);
        cadastrar.setVisible(true);
        cadastrar.addActionListener(this::cadastrarUsuario);
        frame.add(cadastrar);

        this.frame = frame;
    }

    private void cadastrarUsuario(ActionEvent actionEvent) {
        try {
            Usuario.criar(
                    this.nomeField.getText(),
                    this.telefoneField.getText(),
                    Integer.parseInt(this.idadeField.getText()),
                    this.cpfField.getText(),
                    this.usuarioField.getText(),
                    this.senhaField.getText()
            );
        } catch (SQLException e) {
            System.out.println("[erro] Erro ao criar usuário");
            System.out.println("[erro] " + e.getMessage());
        }
    }

    public void mostrar() {
        this.frame.setVisible(true);
    }
}
