# Exchange

Exchange é uma API que tem a finalidade de exibir as taxas de câmbio mais utilizadas no mundo e poder realizar a conversão entre duas moedas.

## **Ferramentas de Desenvolvimento**

Os recursos construídos na API exchange foram desenvolvidos com o uso de diversas tecnologias no envolto da linguagem de programação Java. Para auxiliar e agilizar o desenvolvimento, foram escolhidos algumas ferramentas que serão apresentadas logo adiante.

#### **Spring Framework**
O principal framework utilizado no desenvolvimento das API foi o Spring Framework.

O Spring é um framework open source para a plataforma Java. Tratando-se de um framework não intrusivo, baseado nos padrões de projeto inversão de controle (IoC) e injeção de dependência.

##### **Módulos Utilizados**
##### **Spring Boot**
O Spring Boot é um módulo do Spring que foi criado com o intuito de facilitar a configuração e montagem de ambiente para desenvolvimento de soluções que utilizam o ecossistema fornecido pelo Spring framework.

##### **Spring Data**
Spring Data tem por objetivo fornecer um modelo de programação familiar e consistente, baseado em Spring para acesso a dados, mantendo as características do armazenamento de dados.

##### **Spring MVC**
O Spring MVC é um framework criado para abstrair a camada HTTP no desenvolvimento de aplicações web utilizando o padrão MVC (Model View Controller).

#### **Swagger**
O Swagger é um projeto composto por algumas ferramentas que auxiliam no desenvolvimento de API's REST em algumas tarefas como: modelagem de API, geração de documentação da API etc.

#### **Lombok**
O Lombok é um projeto que visa reduzir a escrita repetitiva de códigos fonte, tornado-o menos verboso.

#### **Hibernate**
O Hibernate é um framework para realizar o mapeamento objeto relacional(ORM) escrito na linguagem java, e o seu principal objetivo é diminuir a complexidade envolvida no desenvolvimento de aplicações que trabalham com banco de dados relacional.

#### **Mockito**
O Mockito é uma biblioteca para auxiliar na criação de mocks para testes em linguagem java.

#### **REST Assured**
O REST Assured é uma biblioteca criada com o intuito de facilitar os testes de serviços REST.

## Running

Primeiro, clone o projeto e compile:

```console
git clone https://github.com/brcesaralves/exchange.git
```

No diretório raiz do projeto, execute o comando abaixo:

```console
mvn spring-boot:run
```
