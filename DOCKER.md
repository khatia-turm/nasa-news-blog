# Docker Deployment

## Prerequisite

Install Docker Desktop and make sure Docker Compose is available.

Stop any copy of the application running from IntelliJ before starting the
container. Both use port `8080` and the same file-based H2 database.

## Build and Run

```powershell
docker compose up --build
```

Open:

- Application: http://localhost:8080
- Health check: http://localhost:8080/health
- Admin information: http://localhost:8080/info

The `/info` endpoint uses HTTP Basic authentication:

```text
username: admin
password: admin123
```

## Stop

```powershell
docker compose down
```

The Compose mounts preserve the H2 database, uploaded images, and logs in the
local `data`, `uploads`, and `logs` directories.
