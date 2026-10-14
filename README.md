# Forma

Kūrybinės skaitmeninės studijos landing puslapis. Pagrindinis puslapio komponentas yra `frontend/src/Landing.jsx`, jo stiliai – `frontend/src/Landing.css`.

## Komandos

- `cd frontend && npm run dev` – vietinis kūrimo serveris
- `cd frontend && npm run build` – produkcinis paketas
- `cd frontend && npm run lint` – kodo patikra

## API

Spring Boot API yra `backend/` kataloge. Įdiegtas testinis endpointas:

```text
GET /api/health
```

Jis grąžina `status`, `service` ir užklausos laiką. Paleidus konteinerį, patikrinkite:

```bash
curl http://localhost:8080/api/health
```

Frontend ir backend diegiami kaip dvi atskiros Coolify aplikacijos.

## PostgreSQL per Coolify

Prie aplikacijos Coolify aplinkos kintamuosiuose pridėkite prisijungimo duomenis iš esamos PostgreSQL paslaugos:

```text
SPRING_PROFILES_ACTIVE=prod
RAILPACK_JDK_VERSION=21
RAILPACK_START_CMD=java -jar target/forma-api-0.0.1-SNAPSHOT.jar
SPRING_DATASOURCE_URL=jdbc:postgresql://<postgres-host>:5432/<database>
SPRING_DATASOURCE_USERNAME=<username>
SPRING_DATASOURCE_PASSWORD=<password>
FORMA_API_KEY=<ilgas-atsitiktinis-raktas>
```

`backend/.env.dev.example` nenurodo Windows PostgreSQL ir reikalauja atskiro vystymo PostgreSQL hosto. Produkcijai naudokite `backend/.env.prod.example` kaip kintamųjų sąrašą Coolify aplinkoje. Abu tikrieji `.env` failai yra ignoruojami Git.

Coolify backend aplikacijai nustatykite `backend` kaip Base Directory. Railpack aptiks `pom.xml`, o `RAILPACK_START_CMD` paleis Spring Boot per `8080` prievadą. Frontend aplikacijai nustatykite `frontend` kaip Base Directory ir publikuokite Vite `dist/` katalogą.

GitHub Actions naudoja du atskirus Coolify UUID: `COOLIFY_FRONTEND_UUID` ir `COOLIFY_BACKEND_UUID`.

Paleidžiant aplikaciją Flyway automatiškai sukuria `inquiries` lentelę. Duomenų API reikalauja `X-Api-Key` antraštės:

```bash
curl --request POST https://jusu-domenas.lt/api/inquiries \
  --header "X-Api-Key: <FORMA_API_KEY>" \
  --header "Content-Type: application/json" \
  --data '{"name":"Jonas","email":"jonas@example.com","message":"Sveiki"}'

curl https://jusu-domenas.lt/api/inquiries \
  --header "X-Api-Key: <FORMA_API_KEY>"
```
