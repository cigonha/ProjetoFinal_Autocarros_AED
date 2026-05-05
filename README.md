# 🚌 Sistema de Gestão de Linha de Autocarros
 
> Projeto desenvolvido no âmbito da unidade curricular de **Algoritmos e Estruturas de Dados**
 
---
 
## 📖 Descrição
 
Este projeto consiste num simulador de gestão de uma linha de autocarros urbanos. O sistema foca-se na **implementação manual de estruturas de dados fundamentais** e na **aplicação de algoritmos de ordenação** para otimizar a operação e a navegação da linha.
 
---
 
## 📋 Funcionalidades
 
| Funcionalidade | Estrutura Utilizada | Descrição |
|---|---|---|
| Representação da Linha | `MyLinkedList` | Lista Ligada para gerir as paragens do percurso |
| Gestão de Passageiros | `MyQueue` (FIFO) | Fila em cada paragem para gerir a ordem de embarque |
| Gestão do Autocarro | `MyArrayList` | ArrayList para armazenar passageiros com acesso aleatório |
| Navegação e Percurso | `MyGraph` | Grafo com paragens como nós e ligações como arestas |
 
---
 
## 📊 Algoritmos de Ordenação
 
Foram implementados manualmente dois algoritmos de ordenação, escolhidos pela sua clareza lógica e utilidade pedagógica:
 
### 🔵 Bubble Sort
Utilizado para **ordenar as paragens pelo número de passageiros em espera**.
 
### 🟠 Selection Sort
Utilizado para **organizar as paragens por ordem alfabética** (nome).
 
---
 
## 🏗️ Estrutura do Projeto
 
```
src/
├── models/          # Entidades do sistema
│   ├── Passageiro.java
│   ├── Paragem.java
│   └── Autocarro.java
│
├── structures/      # Implementações manuais das estruturas de dados
│   ├── MyQueue.java
│   ├── MyLinkedList.java
│   ├── MyArrayList.java
│   └── MyGraph.java
│
└── app/             # Lógica principal e menu interativo
    └── Main.java
```
 
---
 
## 🛠️ Tecnologias e Ferramentas
 
- **Linguagem:** Java
- **Ambiente de Desenvolvimento:** VS Code com suporte para Java Projects
- **Controlo de Versões:** Git & GitHub
---
 
## 🚀 Como Executar
 
1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/cigonha1/ProjetoFinal_Autocarros_AED.git
   ```
 
2. **Abrir no VS Code** com a extensão *Extension Pack for Java* instalada.
3. **Compilar e executar** a classe principal em `src/app/Main.java`.
---
 
## 👥 Grupo de Trabalho
 
| Nome |
|------|
| Diogo Miguel Geada Pedro (220000891) |
| Tiago Alexandre Cordeiro Alves (210100420) |