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

## Endpoints da API
Abaixo estão os principais endpoints REST disponíveis:

###  1. Categorias
#### POST /categorias: Cria uma nova categoria

<br>

![Image](https://github.com/user-attachments/assets/43df988d-249b-4328-9412-e61c1748ed09)
<br>
#### GET /categorias: Retorna todas as categorias

<br>

![Image](https://github.com/user-attachments/assets/aeafa50b-b0a7-41aa-852a-6a681c462554)
<br>

#### GET /categorias/{id}: Retorna uma categoria específica pelo ID
<br>

![Image](https://github.com/user-attachments/assets/4021a91c-e0fa-492c-8e65-8284ff32cdae)
<br>

#### PUT /categorias/{id}: Atualiza uma categoria

<br>

![Image](https://github.com/user-attachments/assets/22332cae-021b-49fd-bb73-a45889a64965)
<br>

#### DELETE /categorias/{id}: Deleta uma categoria

<br>

![Image](https://github.com/user-attachments/assets/ae929e8d-c603-4633-aa55-b970ed4b7f00)
<br>

#### 2. Pessoas
#### POST /pessoas: Cria uma nova pessoa

<br>

![Image](https://github.com/user-attachments/assets/4b3df22a-be97-429d-9cb4-c1d078baab3f)
<br>

#### GET /pessoas: Retorna todas as pessoas

<br>

![Image](https://github.com/user-attachments/assets/5644d5af-e5d0-4b79-b4aa-e95e262dd5c3)
<br>

#### GET /pessoas/{id}: Retorna uma pessoa específica pelo ID

<br>

![Image](https://github.com/user-attachments/assets/e99f6ae6-46da-420f-972f-971084ee5bee)
<br>

#### PUT /pessoas/{id}: Atualiza uma pessoa

<br>

![Image](https://github.com/user-attachments/assets/0da9e47f-acde-447d-8965-5a197f2b9bb3)
<br>
#### DELETE /pessoas/{id}: Deleta uma pessoa

<br>

![Image](https://github.com/user-attachments/assets/eae02f64-0e7e-40a3-be1c-5e8739ca772e)
<br>

### 3. Eventos
#### POST /eventos: Cria um novo evento

<br>

![Image](https://github.com/user-attachments/assets/ae0031d2-c826-49e3-b13a-7cb08b7f64df)
<br>

#### GET /eventos: Retorna todos os eventos

<br>

![Image](https://github.com/user-attachments/assets/29b597be-f595-4fcf-b6cb-cc5beb74e08e)
<br>

#### GET /eventos/{id}: Retorna um evento específico pelo ID

<br>

![Image](https://github.com/user-attachments/assets/613af531-c51f-4936-bc87-00d705ae6ce6)
<br>

#### PUT /eventos/{id}: Atualiza um evento

<br>

![Image](https://github.com/user-attachments/assets/0c376c49-7619-4bf2-a685-f6cc97b90a87)
<br>

#### DELETE /eventos/{id}: Deleta um evento

<br>

![Image](https://github.com/user-attachments/assets/9ab93f29-c92d-4174-b2d2-e980c671f4fb)
<br>

### 4. Tickets
#### POST /tickets: Cria um novo ticket

<br>

![Image](https://github.com/user-attachments/assets/fb8242d3-ccaf-4e53-b6ac-21025d6006e6)
<br>

#### GET /tickets: Retorna todos os tickets

<br>

![Image](https://github.com/user-attachments/assets/2353ea4f-6f31-4ac4-8ba7-c4319022343b)
<br>

#### GET /tickets/{id}: Retorna um ticket específico pelo ID

<br>

![Image](https://github.com/user-attachments/assets/f87a1fe5-d088-466f-9731-3d35cf530708)
<br>

#### DELETE /tickets/{id}: Deleta um ticket

<br>

![Image](https://github.com/user-attachments/assets/5de43bbf-9f41-4186-b2d7-f9d746ab92db)
<br>

### Testes
O projeto vem com um conjunto de testes automatizados usando JUnit e Mockito.

Para rodar os testes, use o comando:

```
mvn test
```
