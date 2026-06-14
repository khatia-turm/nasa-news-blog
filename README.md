# NASA Blog

Small Spring Boot project for NASA news posts. You can add posts, upload an image, open post pages, switch language, etc.

## Demo users

admin
username: admin
password: admin123
role: ADMIN

user
username: user
password: user123
role: USER

Admin is needed for creating posts and opening `/info`.

## Endpoints / pages

`/` - home page with all posts, public

`/create` - create news post, ADMIN only

`/news/{id}` - open one news post, public

`/health` - actuator health check, public

`/info` - actuator info, ADMIN only

`/?lang=en` - switch to English

`/?lang=ka` - switch to Georgian

## Requirements

Week 7 - Testing: done
At least 3 unit tests are there. JUnit 5 is used. Mockito is used in `NewsServiceTests` for mocking the repository.
Files: `NewsServiceTests`, `NewsFormValidationTests`

Week 8 - Security: done
Spring Security basic auth is added. `/create` and `/info` need ADMIN. Passwords use BCrypt.
File: `SecurityConfig`

Week 9 - i18n/l10n: done
English and Georgian are added. Templates use `#{...}`. Language can be changed with `lang=en` or `lang=ka`.
Files: `messages.properties`, `messages_ka.properties`, `WebConfig`, templates

Week 10 - External API or caching: done
I used the caching option, not external API. `@Cacheable` and `@CacheEvict` are used.
Files: `CacheConfig`, `NewsService`

Week 11 - Monitoring: done
Actuator is added and `/health` is exposed.
Files: `pom.xml`, `application.properties`

Week 12 - Messaging/Async: done
I used the scheduled task option. It logs news post count automatically.
Files: `SchedulingConfig`, `NewsStatisticsScheduler`

Week 14 - Deployment: done
I used Docker option.
Files: `Dockerfile`, `docker-compose.yml`

## Run

```bash
mvnw.cmd spring-boot:run
```

or with Docker:

```bash
docker compose up --build
```

App runs on:

```text
http://localhost:8080
```

## Tests

```bash
mvnw.cmd test
```

There are service tests with mocked repository and validation tests for the form.
