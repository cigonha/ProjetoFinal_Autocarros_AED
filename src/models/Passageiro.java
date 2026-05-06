package models;

public class Passageiro {
    /*idade e deficiência (Perguntar à porfessora)*/
    private String nome;
    private String destino; // Nome da paragem de destino do passageiro
    
    // Construtor
    public Passageiro(String nome, String destino) {
        this.nome = nome;
        this.destino = destino;
    }

    // --- Getters e Setters ---
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
         return nome + " -> " + destino;
    }
}
