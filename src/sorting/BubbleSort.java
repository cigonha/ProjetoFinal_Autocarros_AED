package sorting;

import models.Paragem;

public class BubbleSort {

    // Ordena um array de paragens por número de passageiros em espera (ordem crescente)
    public static void sort(Paragem[] paragens) {
        int n = paragens.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (paragens[j].getFilaPassageiros().size() > paragens[j + 1].getFilaPassageiros().size()) {
                    // Trocar as duas paragens
                    Paragem temp = paragens[j];
                    paragens[j] = paragens[j + 1];
                    paragens[j + 1] = temp;
                }
            }
        }
    }
}