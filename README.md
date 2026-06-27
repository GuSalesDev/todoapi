# 📋 To-Do List API

API REST para gerenciamento de tarefas pessoais com autenticação JWT. Cada usuário acessa apenas suas próprias tarefas.

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

```
src/main/java/com/gustavo/todoapi/
├── config/        # Configuração do Spring Security
├── controller/    # Endpoints REST
├── dto/           # Objetos de requisição e resposta
├── entity/        # Entidades JPA (User, Task)
├── repository/    # Interfaces de acesso ao banco
├── security/      # Filtro e utilitários JWT
└── service/       # Regras de negócio
```

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

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto:

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

## 🔐 Autenticação

A API usa autenticação **stateless via JWT**. Após login ou registro, inclua o token em todas as requisições protegidas:

```
Authorization: Bearer {token}
```

---

## 📌 Endpoints

### Autenticação — rotas públicas

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/auth/register` | Cadastrar novo usuário |
| POST | `/auth/login` | Autenticar usuário |

### Tarefas — requer JWT

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/tasks` | Listar todas as tarefas do usuário |
| POST | `/tasks` | Criar nova tarefa |
| PATCH | `/tasks/{id}/toggle` | Marcar como concluída / pendente |
| DELETE | `/tasks/{id}` | Deletar tarefa |

---

## 💡 Exemplos de uso

### Registrar usuário

```http
POST /auth/register
Content-Type: application/json

{
  "name": "Gustavo",
  "email": "gustavo@email.com",
  "password": "123456"
}
```

**Resposta (201):**
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9..."
}
```

---

### Criar tarefa

```http
POST /tasks
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "Estudar Spring Boot",
  "description": "Focar em Spring Security e JWT"
}
```

**Resposta (201):**
```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "description": "Focar em Spring Security e JWT",
  "completed": false
}
```

---

### Listar tarefas

```http
GET /tasks
Authorization: Bearer {token}
```

**Resposta (200):**
```json
[
  {
    "id": 1,
    "title": "Estudar Spring Boot",
    "description": "Focar em Spring Security e JWT",
    "completed": false
  }
]
```

---

### Marcar como concluída

```http
PATCH /tasks/1/toggle
Authorization: Bearer {token}
```

**Resposta (200):**
```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "description": "Focar em Spring Security e JWT",
  "completed": true
}
```

---

### Deletar tarefa

```http
DELETE /tasks/1
Authorization: Bearer {token}
```

**Resposta: 204 No Content**

---

## 🗄️ Modelo de Dados

```
users                          tasks
─────────────────              ──────────────────────
id          BIGINT PK          id           BIGINT PK
name        VARCHAR            title        VARCHAR
email       VARCHAR UNIQUE     description  VARCHAR
password    VARCHAR            completed    BOOLEAN
                               user_id      BIGINT FK → users.id
```

---

## 👨‍💻 Autor

Feito por **Gustavo Sales** — estudante de Análise e Desenvolvimento de Sistemas com foco em desenvolvimento back-end Java.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Gustavo%20Sales-blue?style=flat&logo=linkedin)](https://linkedin.com/in/seu-perfil)
[![GitHub](https://img.shields.io/badge/GitHub-seu--usuario-black?style=flat&logo=github)](https://github.com/seu-usuario)