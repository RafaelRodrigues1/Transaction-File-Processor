# Descrição do Desafio

Você foi contratado para desenvolver um sistema de processamento em lote para uma instituição financeira. O objetivo do sistema é processar arquivos CSV contendo informações de transações bancárias e armazenar essas transações em um banco de dados relacional. Além disso, o sistema deve gerar um relatório diário resumindo as transações processadas.

## Requisitos Funcionais

### Processamento de Arquivos CSV:

O sistema deve ser capaz de ler arquivos CSV contendo transações bancárias. Cada linha do arquivo representa uma transação e possui os seguintes campos:
- **transaction_id**: ID da transação
- **account_number**: Número da conta
- **transaction_date**: Data da transação
- **amount**: Valor da transação (Double)
- **transaction_type**: Tipo de transação

### Armazenamento em Banco de Dados:

- As transações devem ser validadas e armazenadas em um banco de dados relacional.
- Se uma transação for inválida (por exemplo, valor negativo para crédito), ela deve ser registrada em uma tabela separada de erros.

### Relatório Diário:

No final do processamento, o sistema deve gerar um relatório diário contendo:
- Número total de transações processadas
- Valor total segregado por tipo de Movimentação
- Valor total segregado por tipo de Operação
- Número de transações inválidas

### Notificação:

- O sistema deve enviar um email com o relatório diário após o processamento.

## Outros Requisitos

### Desempenho:

- O sistema deve ser capaz de processar arquivos grandes (milhares de linhas) de forma eficiente.

### Manutenibilidade:

- O código deve ser modular e fácil de entender.
- Devem ser utilizados testes automatizados para garantir a qualidade do código.

### Escalabilidade:

- O sistema deve ser escalável para suportar um aumento no volume de transações.

## Stack Tecnológico

- Java 21
- Spring Boot 3.2.6
- Spring Batch
- Spring Data JPA
- H2 Database (para desenvolvimento e testes)
- MySQL
- JavaMailSender
