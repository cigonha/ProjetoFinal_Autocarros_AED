package structures;

import models.Paragem;

/**
 * Estrutura de dados Lista Ligada Simples (Singly Linked List) de Paragens.
 * Representa a rota/linha linear por onde o autocarro vai circular.
 *
 * Operações principais:
 * - addParagem           -> O(n) - Insere no fim da linha
 * - removerParagem       -> O(n) - Remove por nome e acerta ponteiros
 * - buscarParagem        -> O(n) - Encontra uma paragem específica
 * - calcularPercurso     -> O(n) - Calcula a distância total entre dois pontos
 */
public class MyLinkedList {

    private Paragem head; // Primeira paragem da linha (início da rota)
    private int size;    // Número total de paragens atualmente criadas

    // Construtor
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Retorna o tamanho atual da linha
    public int size() {
        return size;
    }

    // Retorna a primeira paragem
    public Paragem getHead() {
        return head;
    }   

    // Adiciona uma nova paragem no fim da linha
    public void addParagem(String nome, double distanciaProxima) {
        Paragem novaParagem = new Paragem(nome, distanciaProxima);
        if (head == null) {
            // Se a linha estiver vazia, o head passa a ser esta paragem
            head = novaParagem;
        } else {
            // Caso contrário, percorremos a lista até encontrar a última paragem
            Paragem atual = head;
            while (atual.getNext() != null) {
                atual = atual.getNext();
            }
            // Ligamos a última paragem à nova paragem criada
            atual.setNext(novaParagem);
        }
        size++;
    }

    /**
     * Remove uma paragem da linha com base no nome.
     * Ajusta os ponteiros para "saltar" o elemento removido, evitando quebras na rota.
     */
    public boolean removerParagem(String nome) {
        if (head == null) {
            System.out.println("Linha vazia! Nao há paragens para remover.");
            return false;
        }

        // Caso 1: A paragem a remover é a primeira (head)
        if (head.getNome().equalsIgnoreCase(nome)) {
            head = head.getNext(); // O head passa a ser a próxima paragem
            size--;
            return true;
        }

        // Caso 2: A paragem está a meio ou no fim
        Paragem atual = head;
        // Caminhamos até que o PRÓXIMO elemento seja o que queremos remover
        while (atual.getNext() != null && !atual.getNext().getNome().equalsIgnoreCase(nome)) {
            atual = atual.getNext();
        }

        // Se encontrámos a paragem
        if (atual.getNext() != null) {
            // Fazemos o elemento atual apontar para o "neto", eliminando o "filho" da corrente
            atual.setNext(atual.getNext().getNext());
            size--;
            return true;
        }

        System.out.println("Paragem '" + nome + "' nao encontrada na linha.");
        return false;
    }

    // Procura uma paragem pelo nome
    public Paragem buscarParagem(String nome) {
        Paragem atual = head;
        while (atual != null) {
            if (atual.getNome().equalsIgnoreCase(nome)) {
                return atual; // Paragem encontrada
            }
            atual = atual.getNext();
        }
        return null; // Paragem não encontrada
    }

    // Imprime a linha completa de paragens
    public void imprimirLinha() {
        if (head == null) {
            System.out.println("A linha de autocarro está vazia (Sem paragens).");
            return;
        }

        System.out.println("\n================ PERCURSO DA LINHA ================");
        Paragem current = head;
        while (current != null) {
            System.out.print("[" + current.getNome() + "]");
            
            // Se houver uma próxima paragem, desenha a seta com a distância
            if (current.getNext() != null) {
                System.out.print(" -> (" + current.getDistanciaProxima() + " km) -> ");
            }
            current = current.getNext();
        }
        System.out.println(" [Fim da Rota]");
        System.out.println("===================================================");
    }

    /**
     * Método para Calcular Percurso
     * Percorre a lista a partir de uma origem até ao destino, mostrando as paragens
     * intermédias e somando a distância total percorrida (Complexidade Linear O(n)).
     */
    public double calcularPercurso(String origem, String destino) {
        // 1. Encontrar o ponto de partida
        Paragem current = buscarParagem(origem);
        if (current == null) {
            System.out.println("Erro: Paragem de origem '" + origem + "' nao existe.");
            return -1;
        }

        System.out.println("\n--- Calculando Rota de [" + origem + "] para [" + destino + "] ---");
        double distanciaTotal = 0;
        boolean encontrouDestino = false;

        // 2. Caminhar a partir da origem somando os pesos (distâncias)
        while (current != null) {
            System.out.print(current.getNome());
            
            if (current.getNome().equalsIgnoreCase(destino)) {
                encontrouDestino = true;
                System.out.println(" (Chegou ao Destino!)");
                break;
            }
            
            distanciaTotal += current.getDistanciaProxima();
            System.out.print(" -> ");
            current = current.getNext();
        }

        if (!encontrouDestino) {
            System.out.println("\nErro: O destino [" + destino + "] nao é alcançável a partir de [" + origem + "] nesta rota.");
            return -1;
        }

        return distanciaTotal;
    }
}
