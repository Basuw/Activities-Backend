# Activities-Backend
Backend of [Activities project](https://github.com/Basuw/Activities)

Mobile application that help you to set and follow you objectives.

## Description
With Activities you can track your progress among all ativities you perform on a day. This application help you to reach your objectives and become better each days. You can customize as much as you want. More is coming like a sport tracker and a food tracker.

### Languages / frameworks
- SpringBoot 3 🍃
- React Native 📱
- PostgreSQl 💾
- Docker 📦

## Installation

1. clone the project from GitHub to your local environment:

```bash
git clone [https://github.com/Basuw/Activities-Backend.git](https://github.com/Basuw/Activities-Backend.git)
cd Activities-Backend
```
2. Building the Application
```bash
mvn clean install
```

3. Running the Application
You have two main ways to start the Spring Boot application:

Using the Maven Spring Boot Plugin: This is often the most convenient way to run the application during development.

```Bash
mvn spring-boot:run
```

Running the Executable JAR: After building the application (using mvn clean install), an executable JAR file will be created in the target/ directory (e.g., activities-backend-0.0.1-SNAPSHOT.jar). You can run it directly using the Java command:

```Bash
java -jar target/activities-backend-0.0.1-SNAPSHOT.jar
```
Once the application starts successfully, it will typically be accessible at *http://localhost:8080*. 

## Authors
- [Bastien JACQUELIN](https://github.com/Basuw)
