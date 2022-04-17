# Desafio Dev - ByCoders

### Projeto desenvolvido como forma de avaliação para vaga de Desenvolvedor Java Sênior

A descrição do desafio pode ser encontrada [aqui](https://github.com/ByCodersTec/desafio-dev). Em resumo o projeto tem duas funcionalidades principais:

- Upload de um arquivo CNAB em txt posicional com informações de transações diárias
- Exibir extrato com o saldo das transações importadas por lojas nos uploads de arquivos

Além das telas necessárias para atender os requisitos do projeto, foi implementado também uma tela simples de login.

> Como a vaga é focada em Java, o frontend foi feito apenas para validar o backend. Por isso é simples e não foi pensado em ergonomia, usabilidade e/ou qualquer requisito médio de UI/UX em sua navegabilidade e utilização, além de não ter sido utilizados implementações normalmente utilizadas quando se trata de projetos em react modernos.

#### Subindo a infraestrutura

Para rodar o projeto bastar realizar o download ou copiar o conteúdo [docker-compose](https://github.com/marceloeugenios/desafio-dev/blob/main/parser/docker-compose.yml). Certifique-se de que as portas `8088` (backend), `3000` (frontend) e `5432` (postgresql) estejam disponíveis.
Dentro do [docker-compose](https://github.com/marceloeugenios/desafio-dev/blob/main/parser/docker-compose.yml) estão as imagens das aplicações backend e frontend além de uma versão "stateless" do PostgreSQL para validar a solução. Para autenticação e autorização a melhor opção foi subir o KeyCloak em um cloud provider para evitar ter que ficar configurando no startup da infraestrutura.

```sh
 docker-compose up
```

#### Imagens:

- Backend: https://hub.docker.com/repository/docker/marceloeugenios/desafio-dev-be-app
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

> O usuário está fixo no login pois não há validação e/ou criação de usuários, então apenas este foi configurado no KeyCloak.

## Ferramentas / Tecnologias utilizadas

##### Geral

- Readme - https://dillinger.io/
- Docker - Docker Compose
- DockerHub
- DigitalOcean (subi o KeyCloak)
- Intellij

##### Backend

- Java 11
- Spring Boot
- Gradle
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
