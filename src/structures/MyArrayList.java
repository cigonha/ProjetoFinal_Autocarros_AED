package structures;

import models.Passageiro;

public class MyArrayList {
    private Passageiro[] elementos; // Array interno para armazenar os passageiros
    private int size; // Número de passageiros atualmente na lista
    
    // Construtor
    public MyArrayList(int capacidadeInicial) {
        this.elementos = new Passageiro[capacidadeInicial];
        this.size = 0;
    }

    // Adicionar um passageiro no fim da lista
    public void add(Passageiro passageiro) {
        if (isFull()){
            resize();
        }
        elementos[size] = passageiro;
        size++;
    }

    // Aceder ao passageiro numa posição específica
    public Passageiro get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index + ", Tamanho: " + size);
        }
        return elementos[index];
    }

    // Remover o passageiro na posição indicada (desloca elementos para a esquerda)
    public Passageiro remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index + ", Tamanho: " + size);
        }
        
        Passageiro removido = elementos[index];
        for (int i = index; i < size - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[size - 1] = null; // Limpar a última posição
        size--;

        return removido;
    }

    // Devolve o número de passageiros atualmente na lista
    public int size() {
        return size;
    }   

    // Verificar se a lista está vazia
    public boolean isEmpty() {
        return size == 0;
    }

    // Verificar se o array interno está cheio
    public boolean isFull() {
        return size == elementos.length;
    }

    // Duplica a capacidade do array interno quando este está cheio
    private void resize() {
        Passageiro[] novoArray = new Passageiro[elementos.length * 2];
        for (int i = 0; i < size; i++) {
            novoArray[i] = elementos[i];
        }
        elementos = novoArray;  
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
        sb.append("Total: ").append(size).append(" passageiro(s) a bordo.");
        
        return sb.toString();
    }    

}