# Introduction

Simple boat management application example.

Back-end : spring boot

Front-end : angular js

# Start back-end
```bash
cd boatManagement-back
./mvnw spring-boot:run
```

# Start front-end
```bash
cd boatManagement-front
npm install
ng serve --ssl --port 8081
```

# Open website

Open https://localhost:8081/ on your favorite browser.

Accept security risk (self signed certificate).

# Users

2 default users are created :

- Admin (all credentials) : login = admin / password = passwordA
- User (readonly credentials) : login = user / password = passwordB

To logout : Account > logout

# Boats

2 default boats are created. 

Boat DAO is RAM only : data modifications are lost on application restart.
