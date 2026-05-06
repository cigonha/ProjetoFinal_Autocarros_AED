package structures;

import models.Passageiro;

public class MyQueue {

    private static class Node {

        Passageiro passageiro;
        Node next;

        Node(Passageiro passageiro) {
            this.passageiro = passageiro;
            this.next = null;
        }
    }

    private Node front; // Início da fila - quem sai primeiro
    private Node rear;  // Fim da fila - onde entram os novos
    private int size;   // Tamanho da fila

    // Construtor
    public MyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Verificar se a fila está vazia
    public boolean isEmpty() {
        return size == 0;
    }

    // Adicionar um passageiro à fila (vai entrar no final (rear))
    public void enqueue(Passageiro passageiro) {
        if (passageiro == null) {
            System.out.println("Erro: não é possivel adicionar um passageiro nulo à fila.");
            return;
        }

        Node newNode = new Node(passageiro);
        if (rear == null) {
            // Se a fila estiver vazia, o front e rear apontam ambos para o único nó
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Remover um passageiro da fila (quem está no início (front))
    public Passageiro dequeue() {
        if (isEmpty()) {
            System.out.println("Aviso: A fila está vazia. Não há passageiro para remover.");
            return null;
        }

        Passageiro p = front.passageiro;
        front = front.next;

        if (front == null) {
            // A fila ficou vazia após a remoção, então rear também deve ser null
            rear = null;
        }
        size--;
        return p; // Retorna o passageiro removido
    }

    // Consultar o passageiro no início da fila sem o remover
    public Passageiro peek() {
        if (isEmpty()) {
            return null;
        }
        return front.passageiro;
    }

    // Consultar quantos passageiros estão na fila
    public int size() {
        return size;
    }

    // Imprime todos os passageiros na fila
    public void printFila() {
        if (isEmpty()) {
            System.out.println("A fila está vazia.");
            return;
        }

        Node current = front;
        System.out.print("Fila: ");
        while (current != null) {
            System.out.print(current.passageiro.getNome() + ", ");
            if (current.next != null) {
                System.out.print("-> ");
            }
            current = current.next;
        }
        System.out.println("[Fim da fila]");
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Fila vazia.";
        }

        StringBuilder sb = new StringBuilder("Fila: ");
        Node current = front;
        while (current != null) {
            sb.append(current.passageiro.getNome());
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }
}
