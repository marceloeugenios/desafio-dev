FROM onjin/alpine-postgres:13.0

LABEL "Maintainer"="Desafio Dev - ByCoders"

ADD init.sql /docker-entrypoint-initdb.d/init.sql

ENV POSTGRES_USER desafiodev
ENV POSTGRES_PASSWORD abcd1234

EXPOSE 5432
