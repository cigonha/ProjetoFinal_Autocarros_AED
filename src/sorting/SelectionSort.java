package sorting;

import models.Paragem;

public class SelectionSort {

    // Ordena um array de paragens por ordem alfabética do nome (ordem crescente)
    public static void sort(Paragem[] paragens) {
        int n = paragens.length;

        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;

            for (int j = i + 1; j < n; j++) {
                if (paragens[j].getNome().compareToIgnoreCase(paragens[indiceMenor].getNome()) < 0) {
                    indiceMenor = j;
                }
            }

            // Só troca se encontrou um elemento menor
            if (indiceMenor != i) {
                Paragem temp = paragens[i];
                paragens[i] = paragens[indiceMenor];
                paragens[indiceMenor] = temp;
            }
        }
    }
}