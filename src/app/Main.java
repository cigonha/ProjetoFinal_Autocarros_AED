package app;

import java.util.Scanner;
import models.Autocarro;
import structures.MyLinkedList;

public class Main {

    // Variáveis globais estáticas para podermos aceder em todos os métodos do Main
    private static MyLinkedList linha;
    private static Autocarro autocarro;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int opcao = -1;

        desenharAutocarro();
        System.out.println("   === BEM-VINDO AO SISTEMA DE GESTAO DE TRANSPORTES ===\n");

        do {
            mostrarMenu();
            System.out.print("[>] Escolha uma opcao: ");
            
            // Proteção contra letras (se o utilizador digitar algo errado)
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("[!] Erro: Por favor, insira um numero valido.");
                scanner.nextLine();
                continue;
            }

            // Ponto de segurança: Obrigar a criar a linha primeiro (Opção 1 ou Sair)
            if (linha == null && opcao != 1 && opcao != 0) {
                System.out.println("[!] Aviso: Tem de criar a linha de autocarro primeiro (Opcao 1)!");
                continue;
            }

            processarOpcao(opcao);

        } while (opcao != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n====================== MENU ======================");
        System.out.println("1. [>] Criar linha de autocarro");
        System.out.println("2. [+] Adicionar/Remover paragens");
        System.out.println("3. [P] Adicionar passageiros a uma paragem");
        System.out.println("4. [A] Simular chegada do autocarro (Embarque/Desembarque)");
        System.out.println("5. [*] Ordenar paragens (Estatisticas)");
        System.out.println("6. [=] Mostrar estado atual da linha");
        System.out.println("7. [~] Calcular percurso entre paragens");
        System.out.println("0. [x] Sair");
        System.out.println("==================================================");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                criarLinha();
                break;
            case 2:
                gerirParagens();
                break;
            case 3:
                System.out.println("Em construcao (Parte 3)...");
                break;
            case 4:
                System.out.println("Em construcao (Parte 4)...");
                break;
            case 5:
                System.out.println("Em construcao (Parte 5)...");
                break;
            case 6:
                System.out.println("Em construcao (Parte 5)...");
                break;
            case 7:
                System.out.println("Em construcao (Parte 5)...");
                break;
            case 0:
                System.out.println("\n[x] A encerrar o sistema. Boa viagem!");
                desenharAutocarro();
                break;
            default:
                System.out.println("[!] Opcao invalida. Tente novamente.");
        }
    }

    // ==========================================
    // LOGICA DAS OPCOES (METODOS)
    // ==========================================

    private static void criarLinha() {
        if (linha != null) {
            System.out.println("[!] A linha ja foi criada! O sistema nao suporta multiplas linhas.");
            return;
        }

        System.out.println("\n--- [CONFIGURACAO DA LINHA] ---");
        System.out.print("Qual e a capacidade maxima do autocarro desta linha? ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        // Inicializar as nossas estruturas principais
        autocarro = new Autocarro(capacidade);
        linha = new MyLinkedList();

        System.out.println("[OK] Sucesso! Linha criada. O autocarro tem capacidade para " + capacidade + " lugares.");
    }

    private static void gerirParagens() {
        System.out.println("\n--- [GERIR PARAGENS] ---");
        System.out.println("1. [+] Adicionar nova paragem");
        System.out.println("2. [-] Remover paragem existente");
        System.out.println("0. Voltar ao menu anterior");
        System.out.print("[>] Escolha uma opcao: ");
        
        int subOpcao = -1;
        if (scanner.hasNextInt()) {
            subOpcao = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("[!] Erro: Opcao invalida.");
            scanner.nextLine();
            return;
        }

        switch (subOpcao) {
            case 1:
                System.out.print("Nome da nova paragem: ");
                String nome = scanner.nextLine();
                
                System.out.print("Distancia para a proxima paragem (em km, ex: 2.5): ");
                String distStr = scanner.nextLine().replace(',', '.'); // Aceita virgulas e pontos
                
                try {
                    double distancia = Double.parseDouble(distStr);
                    linha.addParagem(nome, distancia);
                    System.out.println("[OK] Paragem '" + nome + "' adicionada ao fim da linha!");
                } catch (NumberFormatException e) {
                    System.out.println("[!] Erro: A distancia deve ser um numero valido.");
                }
                break;
                
            case 2:
                System.out.print("Qual o nome da paragem a remover? ");
                String nomeRemover = scanner.nextLine();
                
                boolean removido = linha.removerParagem(nomeRemover);
                if (removido) {
                    System.out.println("[OK] Paragem '" + nomeRemover + "' removida com sucesso!");
                }
                // Nota: O método removerParagem da MyLinkedList já imprime erro se não encontrar
                break;
                
            case 0:
                System.out.println("A voltar ao Menu Principal...");
                break;
                
            default:
                System.out.println("[!] Opcao invalida.");
        }
    }

    // ==========================================
    // ARTE VISUAL
    // ==========================================
    private static void desenharAutocarro() {
        System.out.println("");
        System.out.println("      _______________________________________");
        System.out.println("    _/_|   ___    ___    ___    ___    ___ | - -");
        System.out.println("   |   |  |   |  |   |  |   |  |   |  |   || - -");
        System.out.println("   |___|__|___|__|___|__|___|__|___|__|___|| - -");
        System.out.println("   |       [ PROJETO AED - DIOGO P. ]      | - -");
        System.out.println("   |=======================================|");
        System.out.println("   '---(O)---------------------------(O)---'");
        System.out.println("");
    }
}