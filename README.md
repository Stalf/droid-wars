#Droid Wars

## Подготовка к запуску
0. Клонировать репозиторий на локальную машину;
1. Установить [JDK 1.7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html);
2. Установить [Gradle 2.7](http://gradle.org/gradle-download/);
3. Установить [MySQL Server 5.7](http://dev.mysql.com/downloads/mysql/). Создать схему *droidwars*;
4. Опционально: установить [jrebel (free)](https://my.jrebel.com/)
5. Установить [Lombok](https://projectlombok.org/) plugin в [IDEA](https://plugins.jetbrains.com/plugin/6317). В настройках IDE на вкладке Annotation Processors поставить флажок Enable.
6. Установить [Liquibase 3.4](http://www.liquibase.org/download/index.html). После установки нужно прописать в системную переменную PATH путь до *liquibase.bat*;
6. Прописать логин и пароль для подключения к MySQL в файлах: *db/master.cmd* и *droidwars-core\src\main\resources\META-INF\spring\jdbc.properties*;
7. Выполнить *db/master.cmd* для создания таблиц в БД;
8. Для старта web приложения вызвать команду: 
```
gradle jettyRun
```
После чего перейти на [localhost:8080](http://localhost:8080/)
