# 📋 To-Do List API

API REST para gerenciamento de tarefas pessoais com autenticação JWT. Cada usuário acessa apenas suas próprias tarefas.

## 🌐 URL em Produção

https://todoapi-production-79c3.up.railway.app

## 🚀 Tecnologias

- Java 21
- Spring Boot 3.4.5
- Spring Security 6
- Spring Data JPA + Hibernate
- MySQL 8
- JWT (JJWT 0.12.6)
- Lombok
- Maven

## 📁 Estrutura do Projeto
src/main/java/com/gustavo/todoapi/

├── config/        # Configuração do Spring Security e tratamento de erros

├── controller/    # Endpoints REST

├── dto/           # Objetos de requisição e resposta

├── entity/        # Entidades JPA (User, Task)

├── repository/    # Interfaces de acesso ao banco

├── security/      # Filtro e utilitários JWT

└── service/       # Regras de negócio

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 21
- Maven
- MySQL 8

### 1. Clone o repositório

```bash
git clone https://github.com/GuSalesDev/todoapi.git
cd todoapi
```

### 2. Configure o arquivo .env na raiz do projeto

```env
DB_USERNAME=root
DB_PASSWORD=sua_senha
JWT_SECRET=sua-chave-secreta-minimo-32-caracteres
```

### 3. Rode o projeto

```bash
mvn spring-boot:run
```

O banco `todo_db` e as tabelas são criados automaticamente pelo Hibernate.

---

## 📌 Documentação da API

**Base URL:** `https://todoapi-production-79c3.up.railway.app`

### Autenticação

A API utiliza autenticação via JWT. Após registro ou login, inclua o token em todas as requisições protegidas:
Authorization: Bearer {token}

---

### POST /auth/register
Cadastra um novo usuário e retorna um token JWT.

**Request:**
```json
{
  "name": "Gustavo",
  "email": "gustavo@email.com",
  "password": "123456"
}
```

**Response — 201 Created:**
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9..."
}
```

**Validações:**
- `name` — obrigatório
- `email` — obrigatório, formato válido
- `password` — obrigatório, mínimo 6 caracteres

---

### POST /auth/login
Autentica um usuário existente e retorna um token JWT.

**Request:**
```json
{
  "email": "gustavo@email.com",
  "password": "123456"
}
```

**Response — 200 OK:**
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9..."
}
```

---

### GET /tasks
Lista todas as tarefas do usuário autenticado.

**Headers:** `Authorization: Bearer {token}`

**Response — 200 OK:**
```json
[
  {
    "id": 1,
    "title": "Estudar Spring Boot",
    "description": "Focar em Spring Security",
    "completed": false
  }
]
```

---

### POST /tasks
Cria uma nova tarefa para o usuário autenticado.

**Headers:** `Authorization: Bearer {token}`

**Request:**
```json
{
  "title": "Estudar Spring Boot",
  "description": "Focar em Spring Security"
}
```

**Validações:**
- `title` — obrigatório
- `description` — opcional

**Response — 201 Created:**
```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "description": "Focar em Spring Security",
  "completed": false
}
```

---

### PATCH /tasks/{id}/toggle
Alterna o status de uma tarefa entre concluída e pendente.

**Headers:** `Authorization: Bearer {token}`

**Response — 200 OK:**
```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "description": "Focar em Spring Security",
  "completed": true
}
```

---

### DELETE /tasks/{id}
Deleta uma tarefa do usuário autenticado.

**Headers:** `Authorization: Bearer {token}`

**Response — 204 No Content**

---

### Erros

**Erro de validação — 400 Bad Request:**
```json
{
  "email": "must be a well-formed email address",
  "password": "size must be between 6 and 2147483647"
}
```

**Erro de negócio — 400 Bad Request:**
```json
{
  "error": "Email já cadastrado"
}
```

**Sem autenticação — 403 Forbidden:**
Retornado quando o token está ausente ou inválido.

---

### Observações

- O token JWT expira em **24 horas**
- Um usuário só acessa as próprias tarefas — nunca as de outro usuário
- Senhas são armazenadas criptografadas com BCrypt

---

## 👨‍💻 Autor

Feito por **Gustavo Sales** — estudante de Análise e Desenvolvimento de Sistemas.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Gustavo%20Sales-blue?style=flat&logo=linkedin)](www.linkedin.com/in/gustavo-salesdev)
[![GitHub](https://img.shields.io/badge/GitHub-seu--usuario-black?style=flat&logo=github)](https://github.com/GuSalesDev)