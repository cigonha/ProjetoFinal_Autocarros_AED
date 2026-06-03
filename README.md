# 🚌 Gestão de Linha de Autocarros — AED

Simulação da gestão de uma linha de autocarros urbana, desenvolvida em Java no âmbito da unidade curricular de **Algoritmos e Estruturas de Dados (AED)**.

O sistema permite criar uma linha composta por várias paragens, gerir a entrada e saída de passageiros em cada paragem, calcular distâncias e ordenar paragens — tudo através de um menu interativo em consola.

Todas as estruturas de dados foram implementadas **manualmente**, sem recurso a `java.util.*`, demonstrando a aplicação prática dos conceitos lecionados.

---

## 👤 Autor

| Campo   | Informação        |
|---------|-------------------|
| **Nome**    | Diogo Miguel Geada Pedro |
| **Número**  | 220000891 |
| **Projeto** | Gestão de Linha de Autocarros — AED |

---

## 📁 Estrutura de Pastas

```
ProjetoFinal_Autocarros_AED/
│
└── src/
    ├── app/            # Ponto de entrada da aplicação (Main.java e menu interativo)
    ├── models/         # Classes do domínio (Autocarro, Paragem, Passageiro)
    ├── sorting/        # Algoritmos de ordenação (BubbleSort, SelectionSort)
    └── structures/     # Estruturas de dados manuais (MyLinkedList, MyQueue, MyArrayList)
```

---

## 🗂️ Estruturas de Dados Utilizadas

### `MyLinkedList` — Linha de Autocarros
Utilizada para representar a **rota linear** do autocarro, onde cada paragem conhece a próxima e a distância até ela.

- **Justificação:** Uma lista ligada simples é a estrutura natural para modelar uma rota sequencial. A inserção dinâmica de novas paragens é feita em **O(1)** (no início ou no fim), sem necessidade de redimensionar arrays. A navegação entre paragens é feita em **O(n)**, o que é adequado para uma rota linear onde o autocarro percorre as paragens em ordem. Esta abordagem é preferível a um grafo para uma rota simples sem cruzamentos ou ligações múltiplas.

---

### `MyQueue` — Fila de Passageiros na Paragem
Utilizada para gerir os passageiros que aguardam em cada paragem.

- **Justificação:** A fila segue a política **FIFO** (*First In, First Out*) — o primeiro passageiro a chegar é o primeiro a embarcar. É implementada com uma lista ligada simples, com operações de `enqueue` e `dequeue` em **O(1)**. Esta estrutura reflete com precisão o comportamento real de uma paragem de autocarro.

---

### `MyArrayList` — Lugares no Autocarro
Utilizada para gerir os passageiros que estão a bordo do autocarro.

- **Justificação:** Um autocarro tem uma **capacidade máxima fixa** (lotação), o que torna o array a estrutura ideal — o tamanho é definido no momento da criação e não pode ser excedido. O acesso por índice é feito em **O(1)**, o que é útil para verificar e remover passageiros em qualquer posição (por exemplo, passageiros que saem a meio da rota). Contrasta intencionalmente com a `MyQueue`, demonstrando a escolha criteriosa de estruturas distintas para problemas distintos.

---

## 🔢 Algoritmos de Ordenação

Implementados no package `sorting`, serão utilizados para ordenar as paragens da linha segundo diferentes critérios:

| Algoritmo | Caso de Uso |
|---|---|
| **Bubble Sort** | Ordenação de paragens por **nome** (ordem alfabética) |
| **Selection Sort** | Ordenação de paragens por **lotação** (número de passageiros em espera) |

Ambos os algoritmos têm complexidade **O(n²)** no pior caso, o que é aceitável dado o número reduzido de paragens numa linha urbana típica.

---

## ⚙️ Funcionalidades

O sistema disponibiliza um **menu interativo em consola** com as seguintes opções:

- **Criar linha** — Inicializar uma nova linha de autocarros com nome e capacidade do autocarro definidos pelo utilizador.
- **Adicionar paragem** — Inserir uma nova paragem na linha, especificando o nome e a distância à paragem anterior.
- **Embarcar passageiros** — Definir via consola quantas pessoas entram na paragem atual; o autocarro embarca passageiros da fila até atingir a lotação máxima.
- **Desembarcar passageiros** — Definir via consola quantas pessoas saem na paragem atual.
- **Listar estado da linha** — Apresentar todas as paragens da rota, com o número de passageiros em fila de espera e a ocupação atual do autocarro.
- **Calcular distância entre paragens** — Percorrer a lista ligada e somar as distâncias entre duas paragens indicadas pelo utilizador.
- **Ordenar paragens** — Ordenar e listar as paragens por nome (Bubble Sort) ou por lotação (Selection Sort).
- **Sair** — Terminar a aplicação.

---

## 🛠️ Como Executar

1. Clonar o repositório:
   ```bash
   git clone https://github.com/cigonha1/ProjetoFinal_Autocarros_AED.git
   ```
2. Abrir o projeto numa IDE (IntelliJ IDEA ou Eclipse recomendado).
3. Compilar e executar a classe `Main.java` localizada em `src/app/`.

---

## 📌 Notas Técnicas

- Linguagem: **Java**
- Proibição explícita do uso de `java.util.*` — todas as estruturas são implementadas de raiz.
- Interação com o utilizador feita exclusivamente via **Scanner** (consola).
- Projeto desenvolvido para fins académicos no âmbito de AED.