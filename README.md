# Database User provider

### What is this project?
To make keycloak work with a database, we can define a custom provider to retrieve the users
from an external datasource instead of replicating all users in the keycloak service.

### How to use it?
1. Build a jar with gradle
    ````shell
    ./gradlew clean build 
    ````
2. Place the created keycloak-db-federation-plain.jar to the keycloak provider folder
    ````shell
    cp build/libs/keycloak-db-federation-plain.jar __kecloak-instance__/opt/keycloak/providers/ 
    ````

### Local testing
1. Delete the old jar from the mounted volume
2. Build new jar
3. Copy the new jar to the mounted volume
4. Restart the user management service

