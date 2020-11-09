# Projeto Rotina de transações

Desenvolvi uma solução para registrar as operações de pagamentos e compras para uma conta. Neste serviço estão contemplados as seguintes funcionalidades:

- Registrar uma conta;
- Registrar uma transação;
- Consultar uma conta informando o Id.

## Reflexão sobre o problema

Para construir a aplicação utilizei o framework Spring, pois, por ter maior conhecimento nos seus recursos para criar os testes, configurar o acesso ao banco de dados e desenvolver as apis de forma rápida.

Para este projeto não será há necessidade de instalar as ferramentas para fazer o build e o deploy. Neste caso estou a utilizar o wrapper do maven que está embutido no projeto e docker-compose que monta o container do banco de dados e da aplicação. 

Os banco de dados é o Mysql, por que, é importante destacar que usei o modelo relacional para trabalhar o conceito ACID.

Por entender que o mundo de desenvolvimento esta globalizado, utilizei como idioma o Inglês para escrever o código e as apis.

## Tecnologias utilizadas

* Linguagem Java - Versão 11

``` shell script
openjdk 11.0.5 2019-10-15 LTS
OpenJDK Runtime Environment Zulu11.35+15-CA (build 11.0.5+10-LTS)
OpenJDK 64-Bit Server VM Zulu11.35+15-CA (build 11.0.5+10-LTS, mixed mode)
```

* Maven 3 - Ferramenta de Build

``` shell script
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /Users/andferreira/.m2/wrapper/dists/apache-maven-3.6.3-bin/1iopthnavndlasol9gbrbg6bf2/apache-maven-3.6.3
Java version: 11.0.5, vendor: Azul Systems, Inc., runtime: /Users/andferreira/.sdkman/candidates/java/11.0.5-zulu
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.7", arch: "x86_64", family: "mac"

```

* Repositório e versão de código - Github e Git;

* Migration - Flayway;

* Banco de Dados - H2 (teste) e Mysql (docker);

* Spring Web (MVC) - Framerwork Web para geração das API's (versão 2.3.5) com Tomcat 9;

* Spring Boot - Setup de projeto.


## Documentação através do swagger (versão 2.8)

O projeto possui uma documentação de API através do swagger.

Acesse http://localhost:8080/swagger-ui.html para ver e testar os endpoints.

![Swagger](/images/swagger.png)


## Para realizar o build e os testes do programa

Primeiro passo faça o clone do projeto e depois siga os passos abaixo.

Segue:

Acesse o projeto

```
cd transaction-routine
```

### Executar os testes

Execute na raiz do comando para rodar os testes:

```shell script
./mvnw test
```

ou

```shell script
 docker run --rm -v $PWD:/app -w /app maven:3.6.3-jdk-11 mvn clean test 
```


### Executar o build

Para executar o build:

```shell script
./mvnw clean package
```

ou

```shell script
 docker run --rm -v $PWD:/app -w /app maven:3.6.3-jdk-11 mvn clean package 
```

### Executar o deploy

Primeiro crie o banco de dados:

```shell script
docker-compose up db-service
```

Fazer o deploy da aplicação:

```shell script
docker-compose up --build app-service
```

Se tudo ocorreu bem acesso o ``http://localhost:8080/actuator/health`` para verificar se aplicação esta no ar.

## Gestão do Projeto e técnicas para construção da API

Não precisei usar Kaban para administrar as atividades, mas sempre me foquei na documentação passada.

As etapas foram:

*  Criação do projeto no https://start.spring.io/;
*  Construção das migrations para criação das tabelas;
*  Construção da camada de domínio;
*  Construção da camada de aplicação;
*  Construção da camada de infraestrutura;
*  Construção dos testes integrados;

Comecei a aplicação pensando em TDD para me ajudar a construir e validar alguns cenários para entradas das apis.

Pode acessar a página de PR para histórico da construção. Página: ``https://github.com/ander-f-silva/transaction-routine/pulls?q=is%3Apr+is%3Aclosed`` 
