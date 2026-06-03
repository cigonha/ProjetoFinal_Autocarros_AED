package models;

import structures.MyArrayList;

public class Autocarro {

    private final MyArrayList passageirosNoAutocarro;
    private final int capacidadeMaxima;

    // Construtor
    public Autocarro(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.passageirosNoAutocarro = new MyArrayList(capacidadeMaxima);
    }

    // --- Getters ---
    public MyArrayList getPassageirosNoAutocarro() {
        return passageirosNoAutocarro;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    // Método para verificar se o autocarro está cheio
    public boolean estaCheio() {
        return passageirosNoAutocarro.isFull();
    }

    // Método para verificar se o autocarro está vazio
    public boolean estaVazio() {
        return passageirosNoAutocarro.isEmpty();
    }

    // Método para embarcar um passageiro no autocarro (se não estiver cheio)
    /**
     * Embarca um passageiro no autocarro. Adiciona o passageiro ao MyArrayList
     * se houver lugar disponível.
     *
     * @param p Passageiro a embarcar
     * @return true se embarcou com sucesso, false se o autocarro estiver cheio
     */
    public boolean embarcarPassageiro(Passageiro passageiro) {
        if (estaCheio()) {
            System.out.println("Autocarro cheio! " + passageiro.getNome() + " não pode embarcar.");
            return false;
        }

        passageirosNoAutocarro.add(passageiro);
        System.out.println(passageiro.getNome() + " embarcou no autocarro.");
        return true;
    }

    // Método para desembarcar uma quantidade definida de passageiros do autocarro.
    /**
     * Lógica de segurança: se a quantidade pedida for maior do que os
     * passageiros a bordo, desembarcam apenas os que existem — evita erros de
     * índice inválido.
     *
     * Removemos sempre do fim do array com remove(size - 1) para manter a
     * complexidade O(1) — não há deslocamento de elementos, ao contrário de uma
     * remoção a meio que seria O(n).
     *
     * @param quantidade Número de passageiros que o utilizador quer desembarcar
     * @return Número real de passageiros que desembarcaram
     */
    // Verifica se o autocarro não está vazio antes de tentar desembarcar
    public int desembarcarPassageiros(int quantidade) {
        if (estaVazio()) {
            System.out.println("O autocarro está vazio! Nenhum passageiro para desembarcar.");
            return 0;
        }

        // Se pediu mais do que há a bordo, limita ao número real de passageiros
        int aDesembarcar = Math.min(quantidade, passageirosNoAutocarro.size());
        for (int i = 0; i < aDesembarcar; i++) {
            // Removemos do fim do array
            Passageiro passageiro = passageirosNoAutocarro.remove(passageirosNoAutocarro.size() - 1);
            System.out.println(passageiro.getNome() + " desembarcou do autocarro.");
        }
        return aDesembarcar; // Desvolve quantos desembarcaram de facto
    }

    @Override
    public String toString() {
        return "Autocarro [" + passageirosNoAutocarro.size() + "/" + capacidadeMaxima + " lugares]\n"
                + passageirosNoAutocarro.toString();
    }
}
