package models;

import structures.MyQueue;

// Representa uma paragem da linha de autocarro, funcionando como um nó numa lista ligada.
public class Paragem {

    private String nome;
    private MyQueue filaPassageiros;
    private Paragem next; // Próxima paragem na linha (ponteiro da lista ligada)
    private double distanciaProxima; // Distância (em km) até à próxima paragem da rota

    // Construtor
    public Paragem(String nome, double distanciaProxima) {
        this.nome = nome;
        this.filaPassageiros = new MyQueue(10);
        this.next = null;
        this.distanciaProxima = distanciaProxima;
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

    public double getDistanciaProxima() {
        return distanciaProxima;
    }

    public void setDistanciaProxima(double distanciaProxima) {
        this.distanciaProxima = distanciaProxima;
    }

    @Override
    public String toString() {
        return "Paragem: " + nome + " [" + filaPassageiros.size() + " passageiro(s) em espera] - Distancia até à próxima: " + distanciaProxima + " km";
    }
}
