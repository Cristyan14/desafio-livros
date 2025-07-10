# 📚 Projeto Livros - API Gutendex + Spring Boot

Este é um sistema Java desenvolvido com Spring Boot que permite buscar, registrar e listar livros e autores utilizando a API pública do [Projeto Gutenberg (Gutendex)](https://gutendex.com/), armazenando os dados em um banco de dados relacional com JPA/Hibernate.

---

## 🔧 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- Hibernate
- PostgreSQL
- API externa: Gutendex
- Maven

---

## 🚀 Funcionalidades

### ✅ Buscar livro por título (via API Gutendex)
- Registra automaticamente no banco:
  - Título
  - Idioma
  - Total de downloads
  - Autores vinculados (nome, ano nascimento/falecimento)

### 📖 Listar livros registrados
- Exibe todos os livros e autores salvos no banco.

### 👤 Listar autores registrados
- Permite buscar autores por nome (parcial ou completo).

### 📅 Listar autores vivos em um determinado ano
- Filtra autores que estavam vivos no ano informado.

### 🌐 Listar livros por idioma
- Exibe livros salvos com idioma específico (ex: `pt`, `en`, `fr`...).

---

## 🗃️ Estrutura de Entidades

### `Livro`
- `id`
- `titulo`
- `idioma`
- `totalDownloads`
- `autores` (relacionamento 1:N)

### `Autor`
- `id`
- `nome`
- `anoNascimento` (opcional)
- `anoFalecimento` (opcional)
- `livro` (referência ao livro)

---


