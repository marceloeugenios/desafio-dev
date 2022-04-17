# Desafio Dev - ByCoders

### Projeto desenvolvido como forma de avaliação para vaga de Desenvolvedor Java Sênior

A descrição do desafio pode ser encontrada [aqui](https://github.com/ByCodersTec/desafio-dev). Em resumo o projeto tem duas funcionalidades principais:

- Upload de um arquivo CNAB em txt posicional com informações de transações diárias
- Exibir extrato com o saldo das transações importadas por lojas nos uploads de arquivos

Além das telas necessárias para atender os requisitos do projeto, foi implementado também uma tela simples de login.

> Como a vaga é focada em Java, o frontend foi feito apenas para validar o backend. Por isso é simples e não foi pensado em ergonomia, usabilidade e/ou qualquer requisito médio de UI/UX em sua navegabilidade e utilização, além de não ter sido utilizados implementações normalmente utilizadas quando se trata de projetos em react modernos.

#### Subindo a infraestrutura completa

Para rodar o projeto bastar realizar o download ou copiar o conteúdo [docker-compose](https://github.com/marceloeugenios/desafio-dev/blob/main/parser/docker-compose.yml). Certifique-se de que as portas `8088` (backend), `3000` (frontend) e `5432` (postgresql) estejam disponíveis.
Dentro do [docker-compose](https://github.com/marceloeugenios/desafio-dev/blob/main/parser/docker-compose.yml) estão as imagens das aplicações backend e frontend além de uma versão "stateless" do PostgreSQL para validar a solução. Para autenticação e autorização a melhor opção foi subir o KeyCloak em um cloud provider para evitar ter que ficar configurando no startup da infraestrutura.

```sh
 docker-compose up
```

#### Subindo a infraestrutura em desenvolvimento

Para rodar o projeto em desenvolvimento é utilizado o [docker-compose-dev](https://github.com/marceloeugenios/desafio-dev/blob/main/parser/docker-compose-dev.yml) para subir apenas o banco de dados.

```sh
 docker-compose -f docker-compose-dev up
```

O Projeto backend pode ser iniciado pela IDE configurando o profile "dev" em variáveis de ambiente. Com o docker-compose-dev e o projeto iniciados, basta acessar a URL do [swagger](http://localhost:8088/swagger-ui/index.html) para ter acesso aos endpoints.

```sh
spring.profiles.active=dev
```

Para o frontend foi utilizado o VSCode e pode ser iniciado usando o comando baixo:

```sh
npm start
```

> Foi utilizado a versão v16.14.2 do node, mas no docker estou usando a versão 14 compactada, então não deve necessitar ter uma versão específica local caso deixa executá-lo na sua IDE

> IMPORTANTE: Após executar os projetos não esqueça de parar os containers e limpar as imagens locais.

#### Imagens:

- Backend: https://hub.docker.com/repository/docker/marceloeugenios/desafio-dev-be-app - A publicação da imagem do backend no dockerhub é feito por meio de uma task do gradle
- Frontend: https://hub.docker.com/repository/docker/marceloeugenios/desafio-dev-fe-app
- PostgreSQL: https://hub.docker.com/repository/docker/marceloeugenios/desafio-dev-db

#### Acessos:

- APIs backend: http://localhost:8088/swagger-ui/index.html
- Front: http://localhost:3000
- KeyCloak: http://206.189.7.110:8080/auth/

### Autenticação

| Usuário | Senha |
| ------- | ----- |
| desafio | 12345 |

## Ferramentas / Tecnologias utilizadas

##### Geral

- Readme - https://dillinger.io/
- Docker - Docker Compose
- DockerHub
- DigitalOcean (KeyCloak)
- Intellij
- Postman

##### Backend

- Java 11
- Spring Boot
- Gradle + Dockerhub integration
- Jacoco
- Lombok
- PostgreSQL
- H2 Database
- Swagger - OpenAPI
- KeyCloak
- Flyway

##### Frontend

- Reactjs

##### Observações:

- Arquivo `docker-compose` foi deixado dentro do projeto backend pra ficar mais fácil sua localização
- Dockerfile utilizado para gerar a imagem do banco de dados https://github.com/marceloeugenios/desafio-dev/tree/main/db
