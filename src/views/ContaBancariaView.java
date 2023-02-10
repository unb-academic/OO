package src.views;

import src.models.pessoas.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ContaBancariaView extends JFrame {
    private final JFrame frame;

    public ContaBancariaView(int idUsuario) throws IOException, FontFormatException {
        super();

        // Fonte
        File arquivo = new File("assets/fonts/Poppins-Medium.ttf");
        Font fonte = Font.createFont(Font.TRUETYPE_FONT, arquivo).deriveFont(15f);

        JFrame frame = new JFrame();
        frame.setTitle("Zubank - Conta Bancária");
        frame.setSize(800,600);
        frame.getContentPane().setBackground(new Color(95, 159, 159));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBounds(0, 0, 300, 600);
        painelBotoes.setBackground(new Color(95, 159, 159));
        painelBotoes.setLayout(null);
        frame.add(painelBotoes);

        JButton salario = new JButton();
        salario.setLayout(null);
        salario.setText("Salário");
        salario.setFont(fonte);
        salario.setBounds(0, 0, 300, 100);
        salario.setBackground(new Color(95, 159, 159));
        salario.setForeground(Color.white);
        // salario.addActionListener(this::salarioInfoEditar);
        painelBotoes.add(salario);

        JButton rendaExtra = new JButton();
        rendaExtra.setText("Renda Extra");
        rendaExtra.setFont(fonte);
        rendaExtra.setBounds(0, 100, 300, 100);
        rendaExtra.setBackground(new Color(95, 159, 159));
        rendaExtra.setForeground(Color.white);
//        rendaExtra.addActionListener(this::addRendaExtra);
        painelBotoes.add(rendaExtra);

        JButton acoes = new JButton();
        acoes.setText("Ações");
        acoes.setFont(fonte);
        acoes.setBounds(0, 200, 300, 100);
        acoes.setBackground(new Color(95, 159, 159));
        acoes.setForeground(Color.white);
//        acoes.addActionListener(this::infoAcoes);
        painelBotoes.add(acoes);

        JButton editar = new JButton();
        editar.setText("Editar Informações");
        editar.setFont(fonte);
        editar.setBounds(0, 300, 300, 100);
        editar.setBackground(new Color(95, 159, 159));
        editar.setForeground(Color.white);
//        editar.addActionListener(this::editarInfoConta);
        painelBotoes.add(editar);

        // Painel para mostrar informações
        JPanel painelInfo = new JPanel();
        painelInfo.setBounds(300, 0, 500, 600);
        painelInfo.setBackground(new Color(180, 220, 209));
        painelInfo.setLayout(null);
        painelInfo.setVisible(true);

        JButton voltar = new JButton();
        voltar.setText("Voltar");
        voltar.setFont(fonte);
        voltar.setBounds(0, 505, 300, 63);
        voltar.setBackground(new Color(246, 106, 106));
        voltar.setForeground(Color.white);
        voltar.addActionListener(event -> {
            frame.dispose();
            try {
                new TelaPrincipalView(idUsuario).mostrar();
            } catch (SQLException | IOException | FontFormatException e) {
            }
        });
        painelBotoes.add(voltar);

        this.frame = frame;
    }

    public void mostrar() {
        this.frame.setVisible(true);
    }
}
