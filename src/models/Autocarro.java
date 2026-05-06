package models;

import structures.MyArrayList;

public class Autocarro {
    // PERGUNTAR SE POSSO COLOCAR AQUI O FINAL ??????
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
    public boolean embarcarPassageiro(Passageiro passageiro) {
        if (estaCheio()) {
            System.out.println("Autocarro cheio! " + passageiro.getNome() + " não pode embarcar.");
            return false;
        }

        passageirosNoAutocarro.add(passageiro);
        System.out.println(passageiro.getNome() + " embarcou. (Destino: " + passageiro.getDestino() + ")");
        return true;
    }

    // Método para desembarcar apenas os passageiros cujo destino coincide com a paragem atual 
    // Verifica se o autocarro não está vazio antes de tentar desembarcar
    public void desembarcarPassageiros(String nomeParagem) {
        if (estaVazio()) {
            System.out.println("Nenhum passageiro a bordo para desembarcar.");
            return;
        }

        System.out.println("A verificar desembarque na paragem: " + nomeParagem);
        // Percorre a lista em sentido inverso para garantir a integridade dos índices após a remoção
        for (int i = passageirosNoAutocarro.size() - 1; i >= 0; i--) {
            Passageiro p = passageirosNoAutocarro.get(i);

            // Compara o destino do passageiro com o nome da paragem atual
            if (p.getDestino().equalsIgnoreCase(nomeParagem)) {
                passageirosNoAutocarro.remove(i);
                System.out.println(p.getNome() + " desembarcou em " + nomeParagem + ".");
            }
        }
    }

    @Override
    public String toString() {
        return "Autocarro [" + passageirosNoAutocarro.size() + "/" + capacidadeMaxima + " lugares]\n"
                + passageirosNoAutocarro.toString();
    }
}
