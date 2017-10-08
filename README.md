#Droid Wars

## Подготовка к запуску
0. Клонировать репозиторий на локальную машину;
1. Установить [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html);
2. Установить [Gradle 4.2+](https://gradle.org/releases/);
3. Установить [NPM 5.4.2+](https://www.npmjs.com/);
3. Установить [Lombok](https://projectlombok.org/) plugin в [IDEA](https://plugins.jetbrains.com/plugin/6317). В настройках IDE на вкладке Annotation Processors поставить флажок Enable;
4. Установить [PostgreSQL](https://www.postgresql.org/). Создать пустую базу данных c названием droidwars; 
6. Прописать логин и пароль для подключения к PostgreSQL в файлах: *application.properties*;
7. Для старта web приложения вызвать команду из корня проекта: 
```
gradle webRun
```
После чего перейти на [localhost:8080](http://localhost:8080/)
