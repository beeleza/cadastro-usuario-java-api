# Cadastro Usuário API

Uma API REST simples para gerenciamento de usuários, desenvolvida com Spring Boot e JPA/Hibernate.

## 📋 Sobre o Projeto

Este projeto é uma API de CRUD para gerenciamento de usuários, permitindo operações básicas como salvar, buscar, atualizar e deletar usuários através de endpoints REST.

## 🚀 Tecnologias Utilizadas

- **Java** - Linguagem de programação
- **Spring Boot** - Framework principal
- **Spring Data JPA** - Para persistência de dados
- **Spring Web** - Para criação dos endpoints REST
- **Hibernate** - Implementação do JPA
- **Lombok** - Para redução de código boilerplate
- **H2 Database** - Banco de dados em memória

## 🔧 Endpoints da API

| Método | Endpoint | Descrição | Parâmetros |
|--------|----------|-----------|------------|
| POST | `/usuarios` | Salvar novo usuário | Body: JSON com email e nome |
| GET | `/usuarios` | Buscar usuário por email | Query param: `email` |
| PUT | `/usuarios` | Atualizar usuário por ID | Query param: `id`<br>Body: JSON com campos a atualizar |
| DELETE | `/usuarios` | Deletar usuário por email | Query param: `email` |

✒️ Autor

Desenvolvido como parte de um projeto de estudo de API REST com Spring Boot por Higor Beleza.
