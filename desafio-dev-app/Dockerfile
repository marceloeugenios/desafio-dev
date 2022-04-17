FROM node:14-slim as build

WORKDIR /app

COPY package.json ./
COPY package-lock.json ./
COPY ./ ./

RUN npm i

CMD ["npm", "run", "start"]


# docker build -t marceloeugenios/desafio-dev-fe-app .
# docker tag marceloeugenios/desafio-dev-fe-app:latest marceloeugenios/desafio-dev-fe-app:latest & docker push marceloeugenios/desafio-dev-fe-app:latest