package structures;

import models.Passageiro;

/**
 * Estrutura de dados Fila (Queue) de Passageiros.
 *
 * Implementada como uma Fila Circular (Circular Queue). Previne o erro onde os
 * índices crescem infinitamente. Possui método resize() para crescer
 * dinamicamente, simulando uma fila de paragem real (sem limite fixo).
 */
public class MyQueue {

    private Passageiro[] elementos;
    private int front; // Índice do início da fila - quem sai primeiro
    private int rear;  // Índice do fim da fila - onde entram os novos
    private int size;   // Número de passageiros atualmente na fila

    // Construtor
    public MyQueue(int capacidadeInicial) {
        this.elementos = new Passageiro[capacidadeInicial];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // Verificar se a fila está vazia
    public boolean isEmpty() {
        return size == 0;
    }

    // Verificar se a fila está cheia
    public boolean isFull() {
        return size == elementos.length;
    }

    // Devolve o número atual de passageiros na fila
    public int size() {
        return size;
    }

    // Adiciona um passageiro no fim da fila (rear)
    public void enqueue (Passageiro passageiro) {
        if (passageiro == null) {
            System.out.println("Erro: nao é possível adicionar um passageiro nulo à fila.");
            return;
        }
        if (isFull()) {
            resize(); // Redimensiona e "desenrola" a fila se estiver fisicamente cheia
        }
        elementos[rear] = passageiro;
        // O operador % garante que o rear volta a 0 se atingir o limite do array
        rear = (rear + 1) % elementos.length;
        size++;
    }

    // Remove e devolve o passageiro que está na frente da fila (front)
    public Passageiro dequeue() {
        if (isEmpty()) {
            System.out.println("Fila vazia! Nao há passageiros para remover.");
            return null;
        }
        Passageiro removido = elementos[front];
        elementos[front] = null;
        front = (front + 1) % elementos.length;
        size--;

        return removido;
    }

    // Consulta quem é o próximo a sair, sem o remover
    public Passageiro peek() {
        if (isEmpty()) {
            return null;
        }
        return elementos[front];
    }

    // Método para aumentar a capacidade da fila caso o array encha, "desenrolando" a fila circular
    private void resize() {
        Passageiro[] novoArray = new Passageiro[elementos.length * 2];
        for (int i = 0; i < size; i++) {
            novoArray[i] = elementos[(front + i) % elementos.length];
        }
        elementos = novoArray;
        front = 0;
        rear = size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Fila vazia.";
        }
        
        StringBuilder sb = new StringBuilder("Fila: ");
        for (int i = 0; i < size; i++) {
            sb.append(elementos[(front + i) % elementos.length].getNome());
            if (i < size - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }
}
