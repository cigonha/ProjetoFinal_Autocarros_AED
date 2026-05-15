package app;

import models.Autocarro;
import models.Paragem;
import models.Passageiro;
import sorting.BubbleSort;
import sorting.SelectionSort;
import structures.MyLinkedList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // --- Inicialização da linha ---
        MyLinkedList linha = new MyLinkedList();

        Paragem marques    = new Paragem("Marquês");
        Paragem alameda    = new Paragem("Alameda");
        Paragem rossio     = new Paragem("Rossio");
        Paragem baixa      = new Paragem("Baixa");
        Paragem belem      = new Paragem("Belém");

        linha.addParagem(marques);
        linha.addParagem(alameda);
        linha.addParagem(rossio);
        linha.addParagem(baixa);
        linha.addParagem(belem);

        // --- Passageiros nas filas ---
        marques.getFilaPassageiros().enqueue(new Passageiro("Ana",    "Rossio"));
        marques.getFilaPassageiros().enqueue(new Passageiro("Bruno",  "Belém"));
        marques.getFilaPassageiros().enqueue(new Passageiro("Carla",  "Baixa"));

        alameda.getFilaPassageiros().enqueue(new Passageiro("Diana",  "Belém"));
        alameda.getFilaPassageiros().enqueue(new Passageiro("Eduardo","Rossio"));

        rossio.getFilaPassageiros().enqueue(new Passageiro("Fátima",  "Belém"));
        rossio.getFilaPassageiros().enqueue(new Passageiro("Gonçalo", "Baixa"));
        rossio.getFilaPassageiros().enqueue(new Passageiro("Helena",  "Belém"));
        rossio.getFilaPassageiros().enqueue(new Passageiro("Ivo",     "Belém"));

        baixa.getFilaPassageiros().enqueue(new Passageiro("Joana",    "Belém"));

        // --- Autocarro ---
        Autocarro autocarro = new Autocarro(5);

        // --- Ponteiro da paragem atual ---
        Paragem paradaAtual = linha.getHead();

        // --- Menu ---
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Sistema de Gestão de Autocarros    ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (opcao != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Ver estado da linha");
            System.out.println("2. Ver passageiros a bordo");
            System.out.println("3. Simular próxima paragem");
            System.out.println("4. Ordenar paragens por passageiros em espera (Bubble Sort)");
            System.out.println("5. Ordenar paragens por nome (Selection Sort)");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.println("\n=== Estado da Linha ===");
                    System.out.println(linha.toString());
                    System.out.println();
                    Paragem p = linha.getHead();
                    while (p != null) {
                        System.out.println(p.toString());
                        p = p.getNext();
                    }
                    if (paradaAtual != null) {
                        System.out.println("\nAutocarro está atualmente em: " + paradaAtual.getNome());
                    } else {
                        System.out.println("\nO autocarro terminou o percurso.");
                    }
                    break;

                case 2:
                    System.out.println("\n" + autocarro.toString());
                    break;

                case 3:
                    if (paradaAtual == null) {
                        System.out.println("\nO autocarro já chegou ao fim da linha. Percurso concluído.");
                        break;
                    }

                    System.out.println("\n>>> A chegar à paragem: " + paradaAtual.getNome());

                    // Desembarcar passageiros cujo destino é esta paragem
                    autocarro.desembarcarPassageiros(paradaAtual.getNome());

                    // Embarcar passageiros da fila até o autocarro encher
                    System.out.println("A embarcar passageiros em " + paradaAtual.getNome() + "...");
                    while (!paradaAtual.getFilaPassageiros().isEmpty() && !autocarro.estaCheio()) {
                        Passageiro proximo = paradaAtual.getFilaPassageiros().dequeue();
                        autocarro.embarcarPassageiro(proximo);
                    }

                    if (!paradaAtual.getFilaPassageiros().isEmpty()) {
                        System.out.println("Autocarro cheio! Ficaram " +
                            paradaAtual.getFilaPassageiros().size() +
                            " passageiro(s) em espera em " + paradaAtual.getNome() + ".");
                    }

                    // Avançar para a próxima paragem
                    paradaAtual = paradaAtual.getNext();

                    if (paradaAtual != null) {
                        System.out.println("\nPróxima paragem: " + paradaAtual.getNome());
                    } else {
                        System.out.println("\nFim da linha! Percurso concluído.");
                    }
                    break;

                case 4:
                    Paragem[] arrayBubble = linha.toArray();
                    BubbleSort.sort(arrayBubble);
                    System.out.println("\n=== Paragens ordenadas por passageiros em espera ===");
                    for (Paragem par : arrayBubble) {
                        System.out.println(par.toString());
                    }
                    break;

                case 5:
                    Paragem[] arraySelection = linha.toArray();
                    SelectionSort.sort(arraySelection);
                    System.out.println("\n=== Paragens ordenadas por nome ===");
                    for (Paragem par : arraySelection) {
                        System.out.println(par.toString());
                    }
                    break;

                case 0:
                    System.out.println("\nA encerrar o sistema. Até logo!");
                    break;

                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}