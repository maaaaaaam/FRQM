# Fullstack Random Quote Machine
>This is the **Java version** of the machine. There is a **C#** one that is no longer maintained - https://github.com/maaaaaaam/Random-Quote-Machine

A fullstack random quote machine that:

* Fetches quotes from a **Microsoft SQL Server** database table
* Allows quote import via a **`.csv` file**
* Has a quotes table reinitialization feature
>The app is run via **Docker**

## Launching:

```bash
docker compose up --build
```
Wait until the containers are ready, open `localhost:80` in your browser and that's it!

---

Importing quotes via the frontend UI requires a `.csv` file in the format `"Quote text","Author"` without table headers. There is `5quotesToAdd.csv` in the **root folder**. This file exists to test the importing via the frontend UI.

The reinitialization uses `backend\src\main\resources\10InitialQuotesForReinit.csv` to import into the table after **clearing it**.

## Technologies Used:
- HTML, CSS, JS
- Vite, React (version 16), Redux, thunk
- A bit of Bootstrap
- Java 21, Spring Boot, Spring Web, Spring Data JPA
- MS SQL Server
- NginX
- Docker

---
>TO DO: **refactor** the backend