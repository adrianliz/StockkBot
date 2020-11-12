# StockkBot
REST API to automate buying/selling stocks

`StockkBot` comes up as part of a Web Engineering project for UNIZAR

# Contributors
- Adrián Lizaga
- Borja Rando

# Current State
```diff
+ [Production]
```

# Maven environments
This project use two environments:
- dev: for development setup
- prod: for production setup

Use these variables in each environment:
- STOCKK_BOT_NAME=[STOCKK_BOT_NAME]
- STOCKK_BOT_URI=[STOCKK_BOT_URI]
- STOCKK_MASTER_PWD=[STOCKK_MASTER_PWD]
- SERVICES_DIRECTORY_BASE_URI=[SERVICES_DIRECTORY_BASE_URI]
- IDENTITY_SERVICE_NAME=[IDENTITY_SERVICE_NAME]
- BUFFERED_SERVICE_NAME=[BUFFERED_SERVICE_NAME]
- PORTFOLIO_SERVICE_NAME=[PORTFOLIO_SERVICE_NAME]
- USERS_RULES_DIRECTORY_NAME=[USERS_RULES_DIRECTORY_NAME]
- USERS_RULES_FILE_PATH=[USERS_RULES_FILE_PATH]

# Development Setup
```diff
mvn clean compile war:war -Pdev
```

This will generate `stockk-bot.war` in target folder that you can deploy in 
your development server:
```diff
java -jar payara-micro.jar --deploy stockk-bot.war
```

# Production Setup with Podman
```diff
mvn clean compile war:war -Pprod
podman build -t stockk-bot .
podman run -d --privileged=true -p [MACHINE_PORT]:8080 -v [PERSISTENCE_FOLDER]:/opt/payara/[USERS_RULES_DIRECTORY_NAME] -it --name stockk-bot stockk-bot
```

Example of podman run:
```diff
podman run --privileged=true -p 7040:8080 -v ~/stockk-bot/persistence:/opt/payara/stockkbot -it --name stockk-bot stockk-bot
```

If you want to admin `stockk-bot` execute:
```diff
podman exec -it stockk-bot sh
```
