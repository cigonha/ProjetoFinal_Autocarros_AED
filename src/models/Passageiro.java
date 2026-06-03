package models;

public class Passageiro {
    private String nome;
    
    // Construtor
    public Passageiro(String nome) {
        this.nome = nome;
    }

    // --- Getters e Setters ---
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
         return nome;
    }
}
