package structures;

import models.Passageiro;

public class MyArrayList {
    // PERGUNTAR SE POSSO COLOCAR AQUI O FINAL ?????
    private final Passageiro[] elementos; // Array interno para armazenar os passageiros
    private int size; // Número de passageiros atualmente na lista
    private final int capacidade; // Limite máximo de elementos que o array pode armazenar

    // Construtor
    public MyArrayList(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = new Passageiro[capacidade];
        this.size = 0;
    }

    // Verificar se a lista está cheia
    public boolean isFull() {
        return size == capacidade;
    }

    // Verificar se a lista está vazia
    public boolean isEmpty() {
        return size == 0;
    }

    // Adicionar um passageiro ao fim da lista (se não estiver cheia)
    public boolean add(Passageiro passageiro) {
        if (isFull()) {
            System.out.println("Autocarro cheio! Não é possível adicionar: " + passageiro.getNome());
            return false;
        }
        if (passageiro == null) {
            System.out.println("Erro: não é possivel adicionar um passageiro nulo à lista.");
            return false;
        }
        elementos[size] = passageiro;
        size++;
        return true;
    }

    // Remover um passageiro numa posição específica da lista (verifica se o índice é válido)
    public Passageiro remove(int indice) {
        if (indice < 0 || indice >= size) {
            System.out.println("Índice inválido! Não é possível remover passageiro na posição: " + indice);
            return null;
        }

        Passageiro removido = elementos[indice];

        // Deslocar todos os elementos seguintes uma posição para a esquerda
        for (int i = indice; i < size - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[size - 1] = null; // Limpar a última posição
        size--;

        return removido;
    }

    // Consultar um passageiro numa posição específica da lista (verifica se o índice é válido)
    public Passageiro get(int indice) {
        if (indice < 0 || indice >= size) {
            System.out.println("Índice inválido! Não é possível consultar passageiro na posição: " + indice);
            return null;
        }
        return elementos[indice];
    }

    // Consultar quantos passageiros estão na lista
    public int size() {
        return size;
    }

    // Consultar a capacidade total da lista
    public int getCapacidade() {
        return capacidade;
    }

    // Imprime todos os passageiros na lista
    public void printLista() {
        if (isEmpty()) {
            System.out.println("O autocarro está vazio!");
            return;
        }

        System.out.println("=== Passageiros a Bordo ===");
        for (int i = 0; i < size; i++) {
            System.out.println("- " + elementos[i].getNome());
        }
        System.out.println("---------------------------");
        System.out.println("Total: " + size + " passageiro(s) a bordo.");
        System.out.println("===========================");
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "O autocarro está vazio!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Passageiros a Bordo ===\n");

        for (int i = 0; i < size; i++) {
            sb.append("- ").append(elementos[i].getNome()).append("\n");
        }

        sb.append("---------------------------\n");
        sb.append("Total: ").append(size).append(" passageiro(s) a bordo.\n");
        sb.append("===========================");

        return sb.toString();
    }

}
