package src.models.pessoas;

class Pessoa {
    private String nome;
    private String telefone;
    private int idade;
    private String cpf;

    Pessoa(String nome, String telefone, int idade, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.cpf = cpf;
    }
}
