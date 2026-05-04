package models;

public class Passageiro {

    private String nome;

    // Construtor
    public Passageiro(String nome) {
        this.nome = nome;
    }

    // Getter para obter o nome
    public String getNome() {
        return nome;
    }

    // Setter para alterar o nome (se for preciso no futuro)
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Facilita a impressão
    @Override
    public String toString() {
        return this.nome;
    }
}
