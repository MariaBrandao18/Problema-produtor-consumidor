# Problema do Produtor-Consumidor

Implementação em Java para a disciplina de Sistemas Operacionais
**Diretório:** `SistemasOperacionaisJAVA`

---

## 🎯 Visão Geral

O objetivo deste projeto é implementar o clássico problema de sincronização **Produtor‐Consumidor** (também conhecido como **Bounded Buffer Problem**) usando Java. Trata‐se de demonstrar como threads podem interagir para produzir e consumir dados em uma estrutura compartilhada com controle de concorrência.

---

## 🧩 Funcionalidades Principais

* Um ou mais **produtores** inserem itens em um **buffer compartilhado**.
* Um ou mais **consumidores** retiram itens desse buffer.
* Mecanismos de sincronização para evitar:

  * condições de corrida (*race conditions*),
  * buffer vazio,
  * buffer cheio.
* A implementação ilustra conceitos de:

  * **exclusão mútua**,
  * **espera/aviso**,
  * **uso de estruturas seguras para thread**.

---

## 📂 Estrutura do Projeto

```text
SistemasOperacionaisJAVA/
  ├─ src/                    ← código-fonte em Java  
  │   ├─ Producer.java       ← classe que produz itens  
  │   ├─ Consumer.java       ← classe que consome itens  
  │   ├─ Buffer.java         ← interface ou classe do buffer compartilhado  
  │   └─ Main.java           ← classe principal para executar o programa  
  ├─ README.md               ← este arquivo  
```
---

## 🛠 Tecnologias e Ambiente

* **Linguagem:** Java
* **Conceitos trabalhados:**

  * Threads
  * Concorrência
  * Sincronização
  * Buffer compartilhado
* **Ambiente de desenvolvimento usado:**

  * Eclipse

* **Versão Java recomendada:**

  * Java 8 ou superior

---

## 🚀 Como Executar

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/MariaBrandao18/Problema-do-produtor-consumidor.git
   cd Problema-do-produtor-consumidor/SistemasOperacionaisJAVA
   ```

2. **Compile o código (exemplo usando `javac`):**

   ```bash
   javac src/*.java
   ```

3. **Execute a classe principal (exemplo):**

   ```bash
   java -cp src Main
   ```

4. Observe a execução das threads produtoras e consumidoras no console, com itens sendo criados e consumidos.

---

## 🔍 Como Funciona

* O buffer compartilhado permite que produtores façam algo como `put(item)` e consumidores façam `get()` (ou método similar).
* Quando o buffer está **cheio**, o produtor deve **aguardar** até que haja espaço disponível.
* Quando o buffer está **vazio**, o consumidor deve **aguardar** até que haja item disponível.
* O mecanismo de sincronização pode usar:

  * `synchronized`,
  * `wait()/notify()/notifyAll()`,
  * ou classes de concorrência como `BlockingQueue`, dependendo da implementação.

---

## 📌 Possíveis Melhorias

* Permitir configuração via parâmetros, por exemplo:

  * número de produtores,
  * número de consumidores,
  * tamanho do buffer.
* Usar `java.util.concurrent.BlockingQueue` para simplificação ou eficiência.
* Registrar **logs** ou **métricas** de produção/consumo (por exemplo, quantos itens produzidos/consumidos).
* Criar **visualização gráfica** ou **interface simples** para demonstrar o comportamento em tempo real.
* Tratar **interrupções** e **encerramento das threads** de forma graciosa.

---

## 💡 Fontes de Inspiração & Referências

Este projeto foi inspirado e auxiliado por diversos materiais, incluindo:

### 🎥 Vídeos
- **Vídeo explicativo sobre o problema do produtor-consumidor**  
  → The Bounded Buffer Problem  
  Link: https://youtu.be/Qx3P2wazwI0?si=I-YDdl8StzWvlRW2

- **Outro vídeo para aprofundamento**  
  → Quick explanation: the Bounded-Buffer problem  
  Link: https://youtu.be/LRiN3DJdskA?si=cC6u8g7dGUwqoGHE

### 💬 Perguntas e Discussões Técnicas
- **Bounded Buffer Problem: producer producing the data, but consumer not consuming** — StackOverflow
  → https://stackoverflow.com/questions/77139307/bounded-buffer-problem-producer-producing-the-data-but-consumer-not-consuming

- **Problemas com semáforos: produtor-consumidor** — StackOverflow  
  → https://pt.stackoverflow.com/questions/245276/problemas-com-sem%C3%A1foros-produtor-consumidor

### 🤖 IA Utilizada
- **ChatGPT**: Auxiliou no diagnóstico de erros, na revisão de lógicas incorretas e na sugestão de melhorias durante o desenvolvimento do projeto.

