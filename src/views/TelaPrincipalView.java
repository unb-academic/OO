package src.views;

import src.models.bancos.ContaBancaria;
import src.models.pessoas.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class TelaPrincipalView extends JFrame {
    private final JFrame frame;
    private final int idUsuario;

    public TelaPrincipalView(int idUsuario) throws SQLException, IOException, FontFormatException {
        super();

        // Fonte
        File arquivo = new File("assets/fonts/Poppins-Medium.ttf");
        Font fonte = Font.createFont(Font.TRUETYPE_FONT, arquivo).deriveFont(15f);

        JFrame frame = new JFrame();
        frame.setTitle("Zubank - Controle Financeiro");
        frame.setSize(800,600);
        frame.getContentPane().setBackground(new Color(95, 159, 159));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel para mostrar o nome de usuário e o saldo.
        JPanel painel = new JPanel();
        painel.setSize(800,100);
        painel.setBackground(new Color(95, 159, 159));
        painel.setLayout(null);
        painel.setVisible(true);
        frame.add(painel);

        Usuario usuario = Usuario.get(idUsuario);
        ContaBancaria contaBancaria = ContaBancaria.get(idUsuario);

        // O assert é uma forma de garantir que uma condição seja verdadeira.
        // Caso a condição seja falsa, o programa é interrompido.
        // Aqui estamos garantindo que o usuário e sua conta bancária não sejam nulos.
        assert contaBancaria != null;
        assert usuario != null;

        this.idUsuario = idUsuario;

        JLabel nomePainel = new JLabel();
        nomePainel.setText(usuario.getNome());
        nomePainel.setForeground(Color.white);
        nomePainel.setBounds(5, 0, 600,100);
        nomePainel.setFont(fonte);
        nomePainel.setVisible(true);
        painel.add(nomePainel);

        JLabel valorPainel = new JLabel();
        valorPainel.setText("<html><font color=\"white\">R$</font>" + contaBancaria.getSaldo() + "</html>");
        valorPainel.setForeground(Color.black);
        valorPainel.setBounds(560, 0, 600,100);
        valorPainel.setFont(fonte);
        valorPainel.setVisible(true);

        if (contaBancaria.getSaldo() > 0) {
            valorPainel.setForeground(new Color(27, 236, 62));
        } else {
            valorPainel.setForeground(new Color(246, 106, 106));
        }

        painel.add(valorPainel);

        // Painel para acoplar os botões de interação do usuário
        JPanel botoes = new JPanel();
        botoes.setBounds(0,100,300,500);
        botoes.setBackground(Color.black);
        botoes.setVisible(true);
        botoes.setLayout(null);
        frame.add(botoes);

        // Botões de interação
        // Botão da conta bancária
        JButton painelContaBancaria = new JButton();
        painelContaBancaria.setText("Conta Bancária");
        painelContaBancaria.setFont(fonte);
        painelContaBancaria.setBounds(0,0,300,100);
        painelContaBancaria.setBackground(new Color(95, 159, 159));
        painelContaBancaria.setForeground(new Color(255, 255, 255));
        painelContaBancaria.addActionListener(this::abrirConta);
        botoes.add(painelContaBancaria);

        // Botão de despesas
        JButton despesas = new JButton();
        despesas.setText("Despesas");
        despesas.setFont(fonte);
        despesas.setBounds(0,100,300,100);
        despesas.setBackground(new Color(95, 159, 159));
        despesas.setForeground(new Color(255, 255, 255));
//        despesas.addActionListener(this::abrirDespesas);
        botoes.add(despesas);

        // Botão de controle de finanças
        JButton controle = new JButton();
        controle.setText("Controle");
        controle.setFont(fonte);
        controle.setBounds(0,200,300,100);
        controle.setBackground(new Color(95, 159, 159));
        controle.setForeground(new Color(255, 255, 255));
//        controle.addActionListener(this::abrirControle);
        botoes.add(controle);

        // Botão para visualizar perfil
        JButton meuPerfil = new JButton();
        meuPerfil.setText("Meu Perfil");
        meuPerfil.setFont(fonte);
        meuPerfil.setBounds(0,300,300,100);
        meuPerfil.setBackground(new Color(95, 159, 159));
        meuPerfil.setForeground(new Color(255, 255, 255));
//        meuPerfil.addActionListener(this::abrirPerfil);
        botoes.add(meuPerfil);

        // Botão para fazer logout
        JButton sair = new JButton();
        sair.setText("Desconectar");
        sair.setFont(fonte);
        sair.setBounds(0,400,300,70);
        sair.setBackground(new Color(246, 106, 106));
        sair.setForeground(new Color(253, 253, 253));
        botoes.add(sair);
        sair.addActionListener(event -> {
            try {
                frame.dispose();
                new LoginView().mostrar();
            } catch (IOException | RuntimeException | FontFormatException e) {
                System.out.println("[erro] " + e.getMessage());

                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Erro - Zubank",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Imagem
        Imagem imagem = new Imagem();
        imagem.setBounds(300,100, 500,500);
        imagem.setBackground(new Color(0, 0, 0));
        imagem.setVisible(true);
        frame.add(imagem);

        this.frame = frame;
    }

    private void abrirConta(ActionEvent actionEvent) {
        this.frame.dispose();

        try {
            new ContaBancariaView(this.idUsuario).mostrar();
        } catch (IOException | FontFormatException e) {
            System.out.println("[erro] " + e.getMessage());

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Erro - Zubank",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void mostrar() {
        this.frame.setVisible(true);
    }
}

class Imagem extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon fundo = new ImageIcon("assets/images/menu.png");
        Image image = fundo.getImage();
        g.drawImage(image, 0, 0, this);
    }
}
