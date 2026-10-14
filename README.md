# Forma

Kūrybinės skaitmeninės studijos landing puslapis. Pagrindinis puslapio komponentas yra `src/Landing.jsx`, jo stiliai – `src/Landing.css`.

## Komandos

- `npm run dev` – vietinis kūrimo serveris
- `npm run build` – produkcinis paketas
- `npm run lint` – kodo patikra

## API

Spring Boot API yra `backend/` kataloge. Įdiegtas testinis endpointas:

```text
GET /api/health
```

Jis grąžina `status`, `service` ir užklausos laiką. Paleidus konteinerį, patikrinkite:

```bash
curl http://localhost:8080/api/health
```

Konteineris vienu metu pateikia React puslapį ir API per `8080` prievadą. Coolify perskaitys šakninį `Dockerfile`.

## PostgreSQL per Coolify

Prie aplikacijos Coolify aplinkos kintamuosiuose pridėkite prisijungimo duomenis iš esamos PostgreSQL paslaugos:

```text
SPRING_PROFILES_ACTIVE=prod
SPRING_DATASOURCE_URL=jdbc:postgresql://<postgres-host>:5432/<database>
SPRING_DATASOURCE_USERNAME=<username>
SPRING_DATASOURCE_PASSWORD=<password>
FORMA_API_KEY=<ilgas-atsitiktinis-raktas>
```

Vietiniam darbui nukopijuokite `.env.dev.example` į `.env.dev` ir įrašykite savo PostgreSQL duomenis. Produkcijai naudokite `.env.prod.example` kaip kintamųjų sąrašą Coolify aplinkoje. Abu tikrieji `.env` failai yra ignoruojami Git.

Paleidžiant aplikaciją Flyway automatiškai sukuria `inquiries` lentelę. Duomenų API reikalauja `X-Api-Key` antraštės:

```bash
curl --request POST https://jusu-domenas.lt/api/inquiries \
  --header "X-Api-Key: <FORMA_API_KEY>" \
  --header "Content-Type: application/json" \
  --data '{"name":"Jonas","email":"jonas@example.com","message":"Sveiki"}'

curl https://jusu-domenas.lt/api/inquiries \
  --header "X-Api-Key: <FORMA_API_KEY>"
```
