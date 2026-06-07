package app;

import java.util.Scanner;
import models.Autocarro;
import models.Paragem;
import models.Passageiro;
import sorting.ParagemSorter;
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
        System.out.println("   === BEM-VINDO AO SISTEMA DE GESTAO DE LINHA DE AUTOCARROS ===");
        System.out.println("                  === AutocarrO(n) Express ===\n");

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
                adicionarPassageiros();
                break;
            case 4:
                simularParagem();
                break;
            case 5:
                ordenarParagens();
                break;
            case 6:
                mostrarEstadoGlobal();
                break;
            case 7:
                calcularDistancia();
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

    private static void adicionarPassageiros() {
        System.out.println("\n--- [ADICIONAR PASSAGEIROS A FILA] ---");
        if (linha.size() == 0) {
            System.out.println("[!] A linha nao tem paragens. Adicione paragens primeiro (Opcao 2).");
            return;
        }

        System.out.print("Nome da paragem onde chegaram os passageiros: ");
        String nomeParagem = scanner.nextLine();
        Paragem paragem = linha.buscarParagem(nomeParagem);

        if (paragem == null) {
            System.out.println("[!] Erro: Paragem '" + nomeParagem + "' nao encontrada.");
            return;
        }

        System.out.print("Quantos passageiros chegaram a paragem? ");
        int quantidade = 0;
        if (scanner.hasNextInt()) {
            quantidade = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("[!] Erro: Por favor insira um numero valido.");
            scanner.nextLine();
            return;
        }

        // Arrays com nomes para gerar passageiros aleatórios
        String[] nomesProprios = {"Ana", "Joao", "Maria", "Pedro", "Catarina", "Tiago", "Beatriz", "Diogo", "Ines", "Rui", "Sofia", "Miguel", "Joana", "Carlos"};
        String[] apelidos = {"Silva", "Santos", "Ferreira", "Pereira", "Oliveira", "Costa", "Rodrigues", "Martins", "Gomes", "Sousa"};

        System.out.println("\n[Passageiros a entrar na fila:]");
        for (int i = 0; i < quantidade; i++) {
            // Escolhe um nome próprio e um apelido à sorte
            String nomeAleatorio = nomesProprios[(int) (Math.random() * nomesProprios.length)] 
                                 + " " + 
                                 apelidos[(int) (Math.random() * apelidos.length)];
            
            Passageiro p = new Passageiro(nomeAleatorio);
            paragem.getFilaPassageiros().enqueue(p);
            
            // Imprime logo o nome de quem acabou de chegar!
            System.out.println("  -> " + nomeAleatorio);
        }
        
        System.out.println("\n[OK] " + quantidade + " passageiros entraram na fila da paragem '" + paragem.getNome() + "'.");
    }

   private static void simularParagem() {
        System.out.println("\n--- [SIMULAR CHEGADA DO AUTOCARRO] ---");
        if (linha.size() == 0) {
            System.out.println("[!] A linha nao tem paragens.");
            return;
        }

        System.out.print("Em que paragem esta o autocarro agora? ");
        String nomeParagem = scanner.nextLine();
        Paragem paragem = linha.buscarParagem(nomeParagem);

        if (paragem == null) {
            System.out.println("[!] Erro: Paragem '" + nomeParagem + "' nao encontrada.");
            return;
        }

        System.out.println("\n[AUTOCARRO CHEGOU A '" + paragem.getNome().toUpperCase() + "']");
        System.out.println("Ocupacao atual: " + autocarro.getPassageirosNoAutocarro().size() + "/" + autocarro.getCapacidadeMaxima());

        // 1. LÓGICA DE DESEMBARQUE
        System.out.print("Quantos passageiros pretendem SAIR nesta paragem? ");
        int aSair = 0;
        if (scanner.hasNextInt()) {
            aSair = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.nextLine();
        }

        if (aSair > 0) {
            int sairamDeFato = autocarro.desembarcarPassageiros(aSair);
            System.out.println("[->] " + sairamDeFato + " passageiros sairam do autocarro.");
        }

        // 2. LÓGICA DE EMBARQUE
        int pessoasNaFila = paragem.getFilaPassageiros().size();
        System.out.println("\nA iniciar embarque (pessoas em espera na fila: " + pessoasNaFila + ")...");
        
        // --- MOSTRAR FILA ANTES ---
        if (pessoasNaFila > 0) {
            System.out.println("[ANTES] " + paragem.getFilaPassageiros().toString());
            System.out.println("--------------------------------------------------");
        }

        int embarcados = 0;

        // Enquanto o autocarro NÃO estiver cheio E a fila NÃO estiver vazia
        while (!autocarro.estaCheio() && !paragem.getFilaPassageiros().isEmpty()) {
            Passageiro p = paragem.getFilaPassageiros().dequeue();
            autocarro.embarcarPassageiro(p); // Nota: O Autocarro.java já faz o print de quem entrou
            embarcados++;
        }

        System.out.println("--------------------------------------------------");

        if (autocarro.estaCheio() && !paragem.getFilaPassageiros().isEmpty()) {
            System.out.println("[!] O autocarro encheu! Ficaram " + paragem.getFilaPassageiros().size() + " pessoas na paragem para o proximo.");
        }

        System.out.println("[<-] Embarcaram " + embarcados + " passageiros.");
        
        // --- MOSTRAR FILA DEPOIS ---
        System.out.println("[DEPOIS] " + paragem.getFilaPassageiros().toString());
        
        System.out.println("Ocupacao atualizada do autocarro: " + autocarro.getPassageirosNoAutocarro().size() + "/" + autocarro.getCapacidadeMaxima());
    }

    private static void ordenarParagens() {
        System.out.println("\n--- [ORDENAR PARAGENS (ESTATISTICAS)] ---");
        if (linha.size() == 0) {
            System.out.println("[!] A linha nao tem paragens.");
            return;
        }

        Paragem[] arrayParagens = ParagemSorter.converterParaArray(linha);

        System.out.println("1. Por Nome (Bubble Sort - Ordem Alfabetica)");
        System.out.println("2. Por Volume de Passageiros na fila (Selection Sort - Ordem Decrescente)");
        System.out.print("[>] Escolha o criterio: ");

        int opcaoOrd = 0;
        if (scanner.hasNextInt()) {
            opcaoOrd = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("[!] Opcao invalida.");
            scanner.nextLine();
            return;
        }

        if (opcaoOrd == 1) {
            ParagemSorter.bubbleSortPorNome(arrayParagens);
            ParagemSorter.imprimirOrdenacao(arrayParagens, "Nome (Ordem Alfabetica)");
        } else if (opcaoOrd == 2) {
            ParagemSorter.selectionSortPorPassageiros(arrayParagens);
            ParagemSorter.imprimirOrdenacao(arrayParagens, "Volume de Passageiros na Fila");
        } else {
            System.out.println("[!] Opcao invalida.");
        }
    }

    private static void mostrarEstadoGlobal() {
        System.out.println("\n--- [ESTADO ATUAL DO SISTEMA] ---");
        System.out.println(autocarro.toString());

        if (linha.size() > 0) {
            linha.imprimirLinha();
            System.out.println("\n[Detalhe das Filas nas Paragens]:");
            Paragem current = linha.getHead();
            while (current != null) {
                System.out.println("- " + current.getNome() + ": " + current.getFilaPassageiros().size() + " pessoa(s) na fila.");
                current = current.getNext();
            }
        } else {
            System.out.println("A linha ainda nao tem paragens.");
        }
    }

    private static void calcularDistancia() {
        System.out.println("\n--- [CALCULAR PERCURSO] ---");
        if (linha.size() < 2) {
            System.out.println("[!] A linha precisa de pelo menos 2 paragens para calcular distancias.");
            return;
        }

        System.out.print("Paragem de Origem: ");
        String origem = scanner.nextLine();

        System.out.print("Paragem de Destino: ");
        String destino = scanner.nextLine();

        double dist = linha.calcularPercurso(origem, destino);
        if (dist >= 0) {
            System.out.println("\n[OK] Distancia total de viagem: " + dist + " km");
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