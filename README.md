<p align ="center">
  <img src="https://notion-emojis.s3-us-west-2.amazonaws.com/prod/svg-twitter/1f5fa-fe0f.svg"
    width="180px" height="180px" >
</p>
<h1 align ="center">
  BoardCampAPI
</h1>
<div align ="center">

  <h3>Built With</h3>

 <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" height="30px"/>
 <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" height="30px"/>
 <img src="https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white" height="30px"/>
 <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" height="30px"/>

</div>

<br/>

# Description

Boardcamp is an api for a management system for a board game rental store
</br>

## Deploy

- https://boardcampjavaapi.onrender.com

#

## Features

Customers
GET /customers: Get a list of all customers.
GET /customers/{id}: Get a specific customer by ID.
POST /customers: Create a new customer.
PUT /customers/{id}: Update an existing customer.
DELETE /customers/{id}: Delete a customer by ID.

Games
GET /games: Get a list of all games.
GET /games/{id}: Get a specific game by ID.
POST /games: Create a new game.
PUT /games/{id}: Update an existing game.
DELETE /games/{id}: Delete a game by ID.

Rentals
GET /rentals: Get a list of all rentals.
GET /rentals/{id}: Get a specific rental by ID.
POST /rentals: Create a new rental.
PUT /rentals/{id}: Update an existing rental.
DELETE /rentals/{id}: Delete a rental by ID.

</br>

## API Reference

#### Insert a customer

```http
POST /customers
```

#### Request:

| Body   | Type     | Description             |
| :----- | :------- | :---------------------- |
| `name` | `string` | **Required**. name      |
| `cpf`  | `string` | **Required**. valid cpf |

</br>

#### Get all customers

```http
GET /customers
```

#### Response:

```json
[
  {
    "id": 1,
    "name": "João Alfredo",
    "cpf": "01234567890"
  },
  {
    "id": 2,
    "name": "Marcos Antonio",
    "cpf": "01234567890"
  }
]
```

</br>

#### Get a customer by identifier

```http
GET /customers/${id}
```

#### Request:

| Params | Type      | Description            |
| :----- | :-------- | :--------------------- |
| `id`   | `integer` | **Required**. valid id |

<br/>

#### Response:

```json
{
  "id": 1,
  "name": "João Alfredo",
  "cpf": "01234567890"
}
```

</br>

#### Update a customer by identifier

```http
PUT /customers/${id}
```

</br>

#### Delete a customer by identifier

```http
DELETE /customers/${id}
```

#### Request:

| Params | Type      | Description            |
| :----- | :-------- | :--------------------- |
| `id`   | `integer` | **Required**. valid id |

<br/>

#

#### Insert a game

```http
POST /games
```

#### Request:

| Body          | Type     | Description        |
| :------------ | :------- | :----------------- |
| `name`        | `string` | **Required**. name |
| `image`       | `string` | **Required**.      |
| `stockTotal`  | `int`    | **Required**.      |
| `pricePerDay` | `double` | **Required**.      |

</br>

#### Get all games

```http
GET /games
```

#### Response:

```json
[
  {
    "id": 1,
    "name": "Banco Imobiliário",
    "image": "http://",
    "stockTotal": 3,
    "pricePerDay": 1500
  },
  {
    "id": 2,
    "name": "Detetive",
    "image": "http://",
    "stockTotal": 1,
    "pricePerDay": 2500
  }
]
```

</br>

#### Get a game by identifier

```http
GET /games/${id}
```

#### Request:

| Params | Type      | Description            |
| :----- | :-------- | :--------------------- |
| `id`   | `integer` | **Required**. valid id |

<br/>

#### Response:

```json
{
  "id": 1,
  "name": "Banco Imobiliário",
  "image": "http://",
  "stockTotal": 3,
  "pricePerDay": 1500
}
```

</br>

#### Update a game by identifier

```http
PUT /games/${id}
```

</br>

#### Delete a game by identifier

```http
DELETE /games/${id}
```

#### Request:

| Params | Type      | Description            |
| :----- | :-------- | :--------------------- |
| `id`   | `integer` | **Required**. valid id |

<br/>

#

#### Insert a rental

```http
POST /rentals
```

#### Request:

| Body         | Type    | Description            |
| :----------- | :------ | :--------------------- |
| `gameId`     | `int  ` | **Required**. valid id |
| `customerId` | `int  ` | **Required**. valid id |
| `daysRented` | `int`   | **Required**.          |

</br>

#### Get all rentals

```http
GET /rentals
```

#### Response:

```json
[{}]
```

</br>

#### Get a rental by identifier

```http
GET /rentals/${id}
```

#### Request:

| Params | Type      | Description            |
| :----- | :-------- | :--------------------- |
| `id`   | `integer` | **Required**. valid id |

<br/>

#### Response:

```json
{}
```

</br>

#### Finish a rental by identifier

```http
PUT /rentals/${id}
```

</br>

#### Delete a rental by identifier

```http
DELETE /rentals/${id}
```

#### Request:

| Params | Type      | Description            |
| :----- | :-------- | :--------------------- |
| `id`   | `integer` | **Required**. valid id |

<br/>

#

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`DB_URL = jdbc:postgresql://localhost:5432/your-database`

`DB_PASSWORD = your-db-password`

`DB_USERNAME = your-db-username`

</br>

## Run Locally

Clone the project

```bash
  git clone https://github.com/leolucasguedes/java-boardcamp_api
```

Go to the project directory

```bash
  cd java-boardcamp_api
```

Install dependencies

```bash
  mvn clean install
```

Create .env

- Use the .env.example variables

Start the server

- Run BoardcampApplication.java

</br>

## Lessons Learned

In this project I learned a lot about how to create and work with a full API server using SpringBoot.

</br>

## Authors

- [@leolucasguedes](https://www.github.com/leolucasguedes)

<br/>

#

<a  href="mailto:contato.leonardo.lucas0611@gmail.com" target="_blank"><img src="https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg"></a>
