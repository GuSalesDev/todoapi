# 📋 To-Do List API

API REST para gerenciamento de tarefas pessoais, desenvolvida com Java e Spring Boot.

## 🚀 Tecnologias

- Java 21
- Spring Boot 3.4.5
- Spring Security 6
- Spring Data JPA
- MySQL 8
- JWT (JJWT 0.12.6)
- Lombok
- Maven

## 📁 Estrutura do Projeto

```
src/main/java/com/gustavo/todoapi/
├── config/        # Configurações de segurança
├── controller/    # Endpoints REST
├── dto/           # Objetos de requisição e resposta
├── entity/        # Entidades JPA
├── repository/    # Interfaces de acesso ao banco
├── security/      # Filtros e utilitários JWT
└── service/       # Regras de negócio
```

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 21
- Maven
- MySQL 8

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/todoapi.git
cd todoapi
```

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto:

```
DB_USERNAME=root
DB_PASSWORD=sua_senha
JWT_SECRET=sua-chave-secreta-minimo-32-caracteres
```

### 3. Rode o projeto

```bash
mvn spring-boot:run
```

O banco `todo_db` será criado automaticamente.

## 🔒 Autenticação

A API utiliza autenticação stateless via JWT. Para acessar as rotas protegidas, inclua o token no header:

```
Authorization: Bearer {token}
```

## 📌 Status do Projeto

🚧 Em desenvolvimento
