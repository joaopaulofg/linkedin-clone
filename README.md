# LinkedIn Clone - Spring Boot Microservices


Uma aplicação estilo LinkedIn com arquitetura de banco de dados dupla, utilizando Neo4j para dados relacionais e PostgreSQL para dados transacionais, construída com Spring Boot e Java.

-   **core-service** — serviço principal. Gerencia usuários, empresas, vagas, posts e mensagens. Usa PostgreSQL (JPA/Hibernate). Publica eventos no Kafka (user-created, company-created).
    
-   **people-graph-service** — serviço de grafo. Gerencia conexões entre usuários e histórico profissional. Usa Neo4j (Spring Data Neo4j). Consome eventos do Kafka para criar nós e relacionamentos.
    

Comunicação entre serviços via **Apache Kafka**. Orquestração com **Docker Compose**.

----------

## Tecnologias

-   Java 17 + Spring Boot
    
-   Spring Data JPA
    
-   Spring Data Neo4j
    
-   Spring Security + JWT
    
-   Spring Kafka
    
-   Docker / Docker Compose
    
-   Kafka + Zookeeper
    
-   PostgreSQL 16 / Neo4j 5
    

----------

## 🏗️ Estrutura do repositório (resumida)

```
linkedin-clone/
├─ core-service/
│    src/
│    └─ main/
│      ├─ java/com/joaopaulofg/coreservice/
│      │  ├─ auth/       
│      │  ├─ company/          
│      │  ├─ config/      # KafkaProducer configurations
│      │  ├─ infra/       # Kafka & Security
│      │  ├─ job/          
│      │  ├─ user/            
│      │  └─ CoreServiceApplication.java
│      └─ resources/
│         └─ application.yml   # Configurações Spring Boot (DB, Kafka, JWT, etc.)
│         
│    
│
├─ people-graph-service/
│   src/
│    └─ main/
│      ├─ java/com/joaopaulofg/peoplegraphservice/
│      │  ├─ config/
│      │  ├─ domain/
│      │  ├─ kafka/        # KafkaConsumer configurations
│      │  ├─ repository/          
│      │  ├─ service/              
│      │  └─ PeopleGraphServiceApplication.java
│      └─ resources/
│         └─ application.yml   # Configurações Spring Boot (Neo4j, Kafka)
│
├─ init/
│   ├─ neo4j/
│   │   └─ init.cypher          # Script inicial do Neo4j (criação de nós/relacionamentos)
│   └─ postgres/
│       └─ init.sql            # Script inicial do PostgreSQL (criação de tabelas e dados)
├─ docker-compose.yml
└─ README.md
```


----------

## 🚀 Rodando tudo (Docker Compose)

1.  Na raiz do projeto:
    
`docker-compose up -d --build` 

2.  Verifique containers:

`docker ps` 

3.  Dados de inicialização:

-   core-service: init/postgres/init.sql é executado na criação do container (tabelas e dados iniciais).

-   people-graph-service: init/neo4j/init.cypher pode ser executado com o comando no terminal (após o container estar rodando):
`docker exec -it neo4j_db cypher-shell -u neo4j -p password -f /var/lib/neo4j/import/init.cypher`

4.  Logs (exemplo):
    

`docker logs -f core_service_app
docker logs -f people_graph_service_app` 



----------

## Modelagem (resumo das entidades)

**User (Postgres JPA):**

-   id
    
-   email
    
-   password
    
-   firstName
    
-   lastName
    
-   headLine
    
-   createdAt, updatedAt
    
-   company_id
    

**Company:**

-   id 
    
-   name
    
-   description
    
-   relação jobs (1:N)
    
-   relação employees (1:N) |
    

**Job:**

-   id
- title
- description
- postedAt
- company_id
    

**User (node):**

-   :User { id: Long, firstName, lastName, email, headLine }
    
-   relações: `CONNECTED_TO`, `WORKED_AT` (com props `from`/`to`)

**Company (node):**

-   :User { id: Long, name, description }   

----------

## Ports padrão (local)

-   Core Service: `http://localhost:8080`
    
-   People Graph Service: `http://localhost:8081`
    
-   PostgreSQL: `5433` (mapeado para container 5432)
    
-   Neo4j Browser: `http://localhost:7474` (bolt: 7687)
    
-   Kafka broker: `9092`
    

----------

## Tópicos Kafka usados

-   `user-events` (ou `user-created`) — evento criado quando `core-service` cria um usuário.
    
-   `company-events` (ou `company-created`) — evento criado quando `core-service` cria uma company.
    


----------

## Eventos (exemplos)

**UserCreatedEvent** (exemplo JSON enviado pelo core-service):

`{  "id":  1,  "firstName":  "Daniel",  "lastName":  "Abella",  "email":  "danielabella@example.com",  "headLine":  "Gerente de Projetos & Professor",  "company":  null  }` 

**CompanyCreatedEvent** (exemplo JSON):

`{  "id":  1,  "name":  "Tech Solutions",  "industry":  "Tecnologia",  "description":  "Empresa especializada em soluções de software corporativo"  }` 


----------

## 📚 Endpoints principais

### Core Service (`:8080`)

-   `POST /auth/register` — registra usuário (salva no Postgres e publica evento `user-events`).
    
-   `POST /auth/login` — autentica e retorna JWT.

-   `GET /users` — lista usuários
-   `GET /users/{id}` — detalhes do usuário
    
-   `POST /companies` — cria company (salva e publica `company-events`).
-   `GET /companies/{id}` — detalhes da empresa
-   `POST /companies/{companyId}/jobs` — cria vaga vinculada à company.
-   `POST /companies/{companyId}/hire/{userId}` — registra usuário como funcionário (atualiza `company_id` do User).

    

### People Graph Service (`:8081`)

-   `GET /graph` — todos os usuario cadastrados).

-   `POST /graph/connect/{userId}/{targetId}` — cria relacionamento `CONNECTED_TO` entre dois Users.

-   `GET /graph/{userId}/connections` — busca as conexões do usuario.

-   `POST /graph/follow/{followerId}/{followedId}` — cria relacionamento `FOLLOWS` entre dois Users.

-   `GET /graph/{userId}/following` — busca os usuarios que determinado User segue.

-   `POST /graph/worked/{userId}/{companyId}` — cria relacionamento `WORKED_AT`  entre User e Company.
    
-   `GET /graph/suggestions/{userId}` — recomendações (2º grau).
    
-   `GET /graph/shortest-path?from=&to=` — caminho mínimo (Cypher shortestPath).
    
----------

## 🧪 Testing with Postman

Import the provided `collection: postman-collection.json`

Collection Variables:

- core_service_url: `http://localhost:8080/`
- graph_service_url: `http://localhost:8081/`

----------

## Consultas Cypher úteis

Buscar usuário por id (propriedade `id` que corresponde ao id do Postgres):

`MATCH (u:User {id: 1})
RETURN u;` 

Buscar todas as empresas:

`MATCH (c:Company) RETURN c;` 

Buscar conexões de um usuário:

`MATCH (u:User {id:1})-[:CONNECTED_TO]->(v:User)
RETURN u, v;` 

Criar relação `WORKED_AT` via Cypher (alternativa ao endpoint):

`MATCH (u:User {id:1}), (c:Company {id:2})
MERGE (u)-[:WORKED_AT]->(c);` 

    
----------

## Boas práticas e observações

-   Enviar o evento **após** salvar a entidade no Postgres, para garantir que o `id` está preenchido.
    
-   `JsonSerializer.ADD_TYPE_INFO_HEADERS = false` evita problemas de pacote/classe diferente entre serviços.
    
-   No Docker, **use nomes de serviço** (`kafka`, `neo4j`, `db`) no `bootstrap-servers` / `bolt` etc. — não usar `localhost` dentro dos containers.
    
-   Para reaplicar `init.sql`, remova o volume Postgres (`docker-compose down -v`) para que o script execute novamente (atenção: perde dados).
       

----------

## Próximos passos mapeados

-   Documentar endpoints com Swagger/OpenAPI.
    
-   Extrair eventos comuns para um módulo compartilhado (opcional).
    
-   Implementar testes de integração (Testcontainers).
    
-   Regras de autorização mais finas (roles, permissões).
    
----------

## Autor

João — implementador e mantenedor deste estudo.