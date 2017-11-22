# Droid Wars

## Introduction
Droid Wars is an online game about AI-controlled spaceship battles from developers for developers. 
The player has to construct algorithms for his droids from prebuild modules or from scratch using Java, Javascript or Groovy.    
The project is under construction and master branch can be unstable. 

You can read a [brief introduction document](https://docs.google.com/document/d/1X9f0qJ-ILzjKWAlbvV0BpR4gu17wsAs-2H2jfm5aGOw/edit?usp=sharing) (in russian) 
or take a look at [working prototype](https://github.com/Stalf/droid-wars-prototype). 


## First start
1. Clone repository to your local machine;
1. Make sure you have [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed;
1. Install [Gradle 4.2+](https://gradle.org/releases/);
1. Install [NPM 5.4.2+](https://www.npmjs.com/);
1.  *Using [Intellij IDEA](https://www.jetbrains.com/idea/) - install [Lombok plugin](https://projectlombok.org/) for [IDEA](https://plugins.jetbrains.com/plugin/6317). In the IDE settings on tab Annotation Processors check flag Enable*;
1. Install [PostgreSQL](https://www.postgresql.org/). Create an empty database with name **droidwars**; Supply you local datasource properties in file `site\web\src\main\resources\application.properties`. 
    All the rest work will be done by [Liquibase](http://www.liquibase.org/) at build-time;  
1. Execute `gradle test` to check that everything works properly. 

The project consists of three parts:
1. Game server (*__:server__ module on SpringBoot*) can be run with `gradle serverRun`. After startup it will execute test battle calculations periodically and save result files to `{projectRoot}/tmp/battles` folder.
1. Game site (*__:site:web__ on SpringBoot and __:site:frontend__ on Angular/NodeJS modules*) can be run with `gradle webRun`. After startup the main page has to be available at [localhost:8080](http://localhost:8080/).
1. Desktop or Android client (*__:client__ module on libGDX*). It's purpose is to run (or visualize) battle replays calculated by game server. **Not ready yet**. 
