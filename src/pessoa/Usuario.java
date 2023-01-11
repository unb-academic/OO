package src.pessoa;

class Usuario extends Pessoa {
    private String login;
    private String senha;

    public Usuario(String nome, String telefone, int idade, String cpf, String login, String senha) {
        super(nome, telefone, idade, cpf);
        this.login = login;
        this.senha = senha;
    }
}
