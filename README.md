# Spring CRUD Application

Учебное Spring-приложение с CRUD-операциями, сборкой WAR и запуском через Docker + Tomcat.
Основные технологии:
- Java 21 + Spring Framework
- Hibernate / Spring Data JPA
- MySQL 8 через Docker
- Dockerfile + docker-compose
- WAR-деплой на Tomcat

## Для запуска проекта нужно:

1. Клонировать данный репозиторий
2. Собрать проект в Maven: mvn clean install
3. docker compose up --build (должен быть запущен docker)
4. Проверить работоспособность сайта по ссылке: http://localhost:8080/

При запуске docker compose возможна ошибка инициализации БД. Фикс: docker compose down -v -> docker compose up --build
