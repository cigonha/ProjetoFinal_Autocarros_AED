package structures;

import models.Paragem;

public class MyLinkedList {

    private Paragem head; // Primeira paragem da linha
    private int size;     // Número de paragens na lista

    // Construtor — lista começa vazia
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Verifica se a lista está vazia
    public boolean isEmpty() {
        return head == null;
    }

    // Devolve o número de paragens
    public int size() {
        return size;
    }

    // Adiciona uma paragem no fim da linha
    public void addParagem(Paragem nova) {
        if (nova == null) return;

        if (isEmpty()) {
            head = nova;
        } else {
            Paragem atual = head;
            while (atual.getNext() != null) {
                atual = atual.getNext();
            }
            atual.setNext(nova);
        }
        size++;
    }

    // Devolve uma paragem pelo nome (null se não existir)
    public Paragem getParagem(String nome) {
        Paragem atual = head;
        while (atual != null) {
            if (atual.getNome().equalsIgnoreCase(nome)) {
                return atual;
            }
            atual = atual.getNext();
        }
        return null;
    }

    // Devolve a primeira paragem (head)
    public Paragem getHead() {
        return head;
    }

    // Remove uma paragem pelo nome
    public boolean removeParagem(String nome) {
        if (isEmpty()) return false;

        // Caso especial: remover o head
        if (head.getNome().equalsIgnoreCase(nome)) {
            head = head.getNext();
            size--;
            return true;
        }

        // Caso geral: percorrer até encontrar o anterior ao nó a remover
        Paragem anterior = head;
        while (anterior.getNext() != null) {
            if (anterior.getNext().getNome().equalsIgnoreCase(nome)) {
                anterior.setNext(anterior.getNext().getNext());
                size--;
                return true;
            }
            anterior = anterior.getNext();
        }

        return false; // Não encontrou
    }

    // Converte a lista num array de Paragens (útil para os algoritmos de ordenação)
    public Paragem[] toArray() {
        Paragem[] array = new Paragem[size];
        Paragem atual = head;
        for (int i = 0; i < size; i++) {
            array[i] = atual;
            atual = atual.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Linha sem paragens.";

        StringBuilder sb = new StringBuilder("Linha: ");
        Paragem atual = head;
        while (atual != null) {
            sb.append(atual.getNome());
            if (atual.getNext() != null) sb.append(" → ");
            atual = atual.getNext();
        }
        return sb.toString();
    }
}