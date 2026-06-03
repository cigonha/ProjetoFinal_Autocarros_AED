package sorting;

import models.Paragem;
import structures.MyLinkedList;

/**
 * Classe responsável por aplicar algoritmos de ordenação às paragens.
 * - Bubble Sort para ordenar por Nome (Ordem Alfabética)
 * - Selection Sort para ordenar por Lotação na fila (Ordem Decrescente)
 */
public class ParagemSorter {
    
    /**
     * Método auxiliar: Converte a MyLinkedList (Linha) num Array tradicional de Paragens.
     * Isto permite-nos aplicar os algoritmos de ordenação clássicos baseados em índices.
     */
    public static Paragem[] converterParaArray(MyLinkedList linha) {
        int tamanho = linha.size();
        Paragem[] array = new Paragem[tamanho];

        Paragem atual = linha.getHead();
        int index = 0;

        while (atual != null) {
            array[index] = atual;
            atual = atual.getNext();
            index++;
        }

        return array;
    }

    /**
     * 1. BUBBLE SORT: Ordenar as paragens por Nome (Alfabeticamente A-Z)
     * Compara strings usando o método compareToIgnoreCase.
     */
    public static void bubbleSortPorNome(Paragem[] vetor) {
        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                
                // Se o nome da paragem atual for alfabeticamente "maior" que o da próxima, trocamos
                if (vetor[j].getNome().compareToIgnoreCase(vetor[j + 1].getNome()) > 0) {
                    // Lógica de Swap (Troca)
                    Paragem temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        } 
    }

    /**
     * 2. SELECTION SORT: Ordenar as paragens por Número de Passageiros (Decrescente)
     * Coloca as paragens com FILAS MAIORES no topo da lista.
     */
    public static void selectionSortPorPassageiros(Paragem[] vetor) {
        int n = vetor.length;
        
        for (int i = 0; i < n - 1; i++) {
            int max = i; // Assumimos que o primeiro é o maior
            
            for (int j = i + 1; j < n; j++) {
                // Comparamos o tamanho das filas de espera
                if (vetor[j].getFilaPassageiros().size() > vetor[max].getFilaPassageiros().size()) {
                    max = j;
                }
            }
            
            // Lógica de Swap (Troca)
            Paragem temp = vetor[i];
            vetor[i] = vetor[max];
            vetor[max] = temp;
        }
    }

    // Método para imprimir o array após ser ordenado
    public static void imprimirOrdenacao(Paragem[] vetor, String criterio) {
        System.out.println("\n=== Estatísticas: Paragens Ordenadas por " + criterio + " ===");
        
        if (vetor.length == 0) {
            System.out.println("Não existem paragens na linha.");
            return;
        }

        for (int i = 0; i < vetor.length; i++) {
            System.out.println((i + 1) + ". " + vetor[i].getNome() + 
                               " (" + vetor[i].getFilaPassageiros().size() + " passageiros na fila)");
        }
        System.out.println("==========================================================");
    }
}
