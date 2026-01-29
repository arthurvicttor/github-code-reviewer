# Code Reviewer – Analisador Estático em Java

## 📌 Sobre o projeto

Este projeto nasceu como um desafio pessoal: **criar um analisador estático de código em Java do zero**, sem frameworks prontos de análise, para entender como ferramentas de qualidade de código realmente funcionam por baixo dos panos.

A ideia foi sair do uso passivo de ferramentas como SonarQube e **entender na prática conceitos como AST, análise estática, Clean Code e design orientado a extensões**.

O resultado é um analisador simples, extensível e totalmente em Java, capaz de identificar *code smells* comuns em projetos reais.

---

## 🎯 Objetivo

Analisar arquivos `.java` e detectar problemas de qualidade de código (*code smells*), como:

* Métodos muito longos
* Classes muito grandes
* Alta complexidade ciclomática

Tudo isso **sem executar o código**, apenas analisando sua estrutura.

---

## 🧠 Conceitos dominados neste projeto

Durante o desenvolvimento, foi necessário dominar e aplicar:

* ☕ Java moderno (Java 17+ / Java 24)
* 🌳 Análise de código usando **AST (Abstract Syntax Tree)**
* 🔍 JavaParser
* 🧩 Interfaces e polimorfismo
* 🏗️ Arquitetura extensível (Open/Closed Principle)
* 🧼 Clean Code
* 🧪 Leitura e análise de arquivos
* 📦 Organização de pacotes

---

## 🏗️ Arquitetura do projeto

O projeto é dividido em camadas bem definidas:

```
com.github.codereviewer
│
├── analysis
│   ├── CodeAnalyzer.java
│   └── detectors
│       ├── CodeSmellDetector.java
│       ├── LongMethodDetector.java
│       ├── LargeClassDetector.java
│       └── CyclomaticComplexityDetector.java
│
├── model
│   └── Issue.java
│
├── report
│   └── TerminalReportPrinter.java
│
└── Main.java
```

### 🔹 CodeSmellDetector

Interface base para qualquer detector de problemas.

### 🔹 Detectores

Cada detector analisa um aspecto específico do código:

* `LongMethodDetector`
* `LargeClassDetector`
* `CyclomaticComplexityDetector`

### 🔹 CodeAnalyzer

Responsável por orquestrar todos os detectores.

### 🔹 TerminalReportPrinter

Imprime o relatório final no terminal de forma legível.

---

## 🚀 Como executar o projeto

### 1️⃣ Pré-requisitos

* Java 17 ou superior (recomendado Java 24)
* Maven ou Gradle (opcional, dependendo do setup)

---

### 2️⃣ Adicione o arquivo Java para análise

Escolha qualquer arquivo `.java` que você queira analisar.

Exemplo:

```
C:\Users\Usuario-nome\Downloads\Exemplo.java
```

> ⚠️ Importante: se o caminho tiver espaços, **use aspas** no terminal.

---

### 3️⃣ Configure o `Main.java`

No arquivo `Main.java`, informe o caminho do arquivo que será analisado:

```java
String filePath = "C:/Users/Usuario-nome/Downloads/Exemplo.java";
```

E registre os detectores desejados:

```java
List<CodeSmellDetector> detectors = List.of(
    new LongMethodDetector(),
    new LargeClassDetector(),
    new CyclomaticComplexityDetector()
);
```

---

### 4️⃣ Execute o projeto

Via terminal:

```bash
javac Main.java
java Main
```

Ou diretamente pela sua IDE.

---

## 📊 Exemplo de saída no terminal

```
Análise concluída!

Arquivo: Exemplo.java
- Método muito longo: processData (45 linhas)
- Classe muito grande: UserService (320 linhas)
```

---

## 🔧 Como adicionar um novo detector

1. Crie uma nova classe em `analysis.detectors`
2. Implemente a interface `CodeSmellDetector`
3. Adicione o detector na lista do `Main`

Isso garante que o projeto continue **aberto para extensão e fechado para modificação**.

---

## 📈 Próximos passos (ideias)

* Exportar relatório em JSON ou HTML
* Analisar múltiplos arquivos
* Criar métricas de severidade
* Criar interface gráfica ou CLI avançada

---

## 🤝 Contribuições

Este projeto é totalmente aberto a melhorias e ideias.

Sinta-se à vontade para abrir issues, sugerir melhorias ou criar pull requests.

---

## 📎 Repositório

👉 Link do GitHub: **[https://github.com/arthurvicttor/github-code-reviewer]**

---

## 🧠 Considerações finais

Este projeto representa não apenas código, mas **entendimento profundo de como ferramentas profissionais de análise estática funcionam internamente**.

Excelente exercício para quem busca evoluir como desenvolvedor Java e entender qualidade de código na prática.
