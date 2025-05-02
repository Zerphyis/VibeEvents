# VibeEvents

VibeEvents é uma aplicação de gerenciamento de eventos, tickets e participantes. O sistema permite a criação e gerenciamento de categorias de eventos, eventos em si, pessoas e tickets associados, proporcionando uma solução completa para o controle de ingressos e eventos de diversas naturezas, como concertos, exposições, teatro e muito mais.

## Tecnologias

- **Spring Boot** 3.4.4
- **Spring Data JPA** 
- **MySQL**
- **JUnit** 
- **Mockito** 

## Funcionalidades

- Gerenciamento de **Categorias** de eventos
- Cadastro de **Eventos** com detalhes como data, local e preço
- Cadastro de **Pessoas** com dados como nome, email e telefone
- Criação e gerenciamento de **Tickets** para eventos com status e tipo
- Exposição de uma **API REST** para integração com o sistema

## Pré-requisitos

Certifique-se de ter o seguinte instalado:

- [JDK 17](https://openjdk.java.net/)
- [Maven](https://maven.apache.org/install.html)
- [MySQL](https://www.mysql.com/) 

## Configuração do Projeto

### 1. Configuração do Banco de Dados

Edite o arquivo `application.properties` ou `application.yml` para configurar a conexão com o banco de dados MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vibe_events_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

### 2. Dependências do Maven
Certifique-se de que o arquivo pom.xml esteja configurado conforme abaixo, para que o projeto tenha todas as dependências necessárias para o Spring Boot e o MySQL.

### 3. Build do Projeto
Para compilar o projeto, use o Maven. No terminal, execute:

```
mvn clean install
```
### 4. Executando a Aplicação
Depois de compilar o projeto, você pode executar a aplicação Spring Boot com o seguinte comando:

```
mvn spring-boot:run
```

A aplicação será executada na porta padrão 8080.
