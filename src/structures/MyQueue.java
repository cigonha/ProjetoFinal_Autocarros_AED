package structures;

import models.Passageiro;

public class MyQueue {

    // Nó específico para a fila de passageiros
    class Node {
        Passageiro passageiro;
        Node next;

        Node(Passageiro passageiro) {
            this.passageiro = passageiro;
            this.next = null;
        }
    }

    private Node front; // O primeiro da fila (quem sai)
    private Node rear;  // O último da fila (quem entra)
    private int size;

    public MyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Adicionar passageiro à fila (entra no fim da fila)
    public void enqueue(Passageiro passageiro) {
        Node newNode = new Node(passageiro);

        if (rear == null) { // Se a fila está vazia
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode; 
            rear = newNode;      
        }
        size++;
    }

    // Remover passageiro da fila (sai o que está na frente)
    public Passageiro dequeue() {
        if (front == null) {
            return null; // Fila vazia
        }

        Passageiro p = front.passageiro;
        front = front.next; 

        if (front == null) {
            rear = null;
        }

        size--;
        return p;
    }

    public int size() {
        return size;
    }

    // Consultar quais os passageiros na fila
    public void printFila() {
        Node current = front;
        if (current == null) {
            System.out.println("Nenhum passageiro na fila.");
            return;
        }
        
        while (current != null) {
            System.out.print(current.passageiro.getNome() + " -> ");
            current = current.next;
        }
        System.out.println("Fim da fila");
    }
}