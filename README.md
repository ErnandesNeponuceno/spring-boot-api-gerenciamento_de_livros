
# 📚 API de Gestão de Livros

Este projeto é uma API desenvolvida em **Java** com **Spring Boot**, focada na gestão de livros, autores e categorias. A API permite realizar operações básicas de CRUD (Create, Read, Update, Delete) e validações, com suporte para manipulação de exceções.

---

## 🛠️ Funcionalidades

- **Livros**
  - Listar todos os livros.
  - Listar um livro por ID.
  - Adicionar um novo livro.
  - Atualizar as informações de um livro existente.
  - Deletar um livro.
  
- **Autores**
  - Relacionar livros com autores.
  - Validações para garantir que o autor existe antes de criar ou atualizar um livro.

- **Categorias**
  - Relacionar livros com categorias.
  - Validações para garantir que a categoria existe antes de criar ou atualizar um livro.

- **Validações**
  - Validação de dados obrigatórios (título, ISBN, autor, categoria).
  - Verificação de unicidade para título e ISBN.

- **Tratamento de Erros**
  - Erros de validação (400).
  - Entidades não encontradas (404).
  - Erros internos do servidor (500).

---

## 🎯 Estrutura do Projeto

### **Estrutura de Diretórios**
```plaintext
├── src/
│   ├── main/
│   │   ├── java/com/sistema/sgb/
│   │   │   ├── controller/         # Controladores da API (endpoints REST)
│   │   │   ├── dto/                # Objetos de Transferência de Dados (DTOs)
│   │   │   ├── exceptions/         # Tratamento de exceções
│   │   │   ├── model/              # Modelos de entidades (JPA)
│   │   │   ├── repository/         # Repositórios (JPA para persistência)
│   │   │   ├── service/            # Lógica de negócios
│   │   │   └── SgbApplication.java # Classe principal (ponto de entrada)
│   │   └── resources/
│   │       ├── application.properties # Configurações da aplicação
│   └── test/                       # Testes automatizados
├── pom.xml                         # Arquivo de configuração do Maven
├── mvnw, mvnw.cmd                  # Wrapper do Maven
└── target/                         # Arquivos gerados após a compilação
```

---

## 💻 Tecnologias Utilizadas

<div style="display: inline_block">
  <img alt="Java" src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img alt="MySQL" src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
</div>

---

## 🚀 Como Rodar o Projeto?

### **Pré-requisitos**
- Java 11+ instalado.
- Maven configurado.
- Banco de dados MySQL.

### **Passos**
1. Clone o repositório:
   ```bash
   git clone https://github.com/ErnandesNeponuceno/spring-boot-api-gerenciamento_de_livros.git
   ```
2. Configure o banco de dados no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/sgb
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
3. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```
4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
5. Acesse a API na URL:
   ```
   http://localhost:8080
   ```

---

## 🔍 Endpoints Principais

### Livro
- **`GET /livro/listar`**: Retorna a lista de todos os livros.
- **`GET /livro/{id}`**: Retorna o livro com o ID especificado.
- **`POST /livro`**: Adiciona um novo livro.
  - Exemplo de corpo da requisição:
    ```json
    {
      "titulo": "Livro Exemplo",
      "isbn": "123-456-789",
      "autor": "1",
      "categoria": "1"
    }
    ```
- **`PUT /livro/{id}`**: Atualiza as informações de um livro existente.
- **`DELETE /livro/deletar/{id}`**: Remove um livro.

---

## 📄 Documentação Adicional
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Hibernate Documentation](https://hibernate.org/documentation/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

---

## ✨ Créditos
Este projeto foi desenvolvido para aprendizado e demonstração das práticas recomendadas em APIs REST com Spring Boot.
