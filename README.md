# Desafio ByCoders

## Parseador de CNAB desenvolvido como forma de avaliação para vaga de Desenvolvedor Java Senior

Para rodar o projeto bastar realizar o download ou copiar o conteúdo [docker-compose](https://github.com/marceloeugenios/desafio-dev/blob/main/parser/docker-compose.yml). Certifique-se de que as portas `8088` (backend), `3000` (frontend) e `5432` (postgresql) estejam disponíveis.
Dentro do [docker-compose](https://github.com/marceloeugenios/desafio-dev/blob/main/parser/docker-compose.yml) estão as imagens das aplicações backend e frontend além de uma versão "stateless" do PostgreSQL para validar a solução. Para autenticação e autorização a melhor opção foi subir o KeyCloak em um cloud provider para evitar ter que ficar configurando no startup da infraestrutura.

#### Subindo a infraestrutura

```sh
 docker-compose -f docker-compose.yml up
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

## Ferramentas / Tecnologias utilizadas

##### Geral

- Readme - https://dillinger.io/
- Docker - Docker Compose
- DockerHub
- DigitalOcean (subi o KeyCloak)
- Intellij

##### Backend

- Java 11
- Spring Boot 2.6.6
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

> Observações:
> Arquivo `docker-compose` foi deixado dentro do projeto backend pra ficar mais fácil sua localização
> Dockerfile utilizado para gerar a imagem do banco de dados https://github.com/marceloeugenios/desafio-dev/tree/main/db
