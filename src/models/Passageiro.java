package models;

public class Passageiro {
    /*idade e deficiência (Perguntar à porfessora)*/
    private String nome;
    
    // Construtor
    public Passageiro(String nome) {
        this.nome = nome;
    }

    // --- Getter e Setter --
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
