package models;

import structures.MyQueue;

public class Paragem {

    private String nome;
    private MyQueue filaPassageiros;
    private Paragem next;

    // Construtor
    public Paragem(String nome) {
        this.nome = nome;
        this.filaPassageiros = new MyQueue();
        this.next = null;
    }

    // --- Getters e Setters ---
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public MyQueue getFilaPassageiros() {
        return filaPassageiros;
    }

    public void setFilaPassageiros(MyQueue filaPassageiros) {
        this.filaPassageiros = filaPassageiros;
    }

    public Paragem getNext() {
        return next;
    }

    public void setNext(Paragem next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "Paragem: " + nome + " [" + filaPassageiros.size() + " passageiro(s) em espera]";
    }
}
