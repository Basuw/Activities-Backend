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

## Docker Setup

### Configuration

#### 1. Fichiers d'environnement

Deux fichiers de configuration ont été créés :

- **template.env** : Template avec les variables d'environnement (à commiter)
- **.env** : Configuration locale avec les valeurs réelles (à ne pas commiter)

#### 2. Variables d'environnement

Les variables suivantes doivent être configurées dans `.env` :

```
# Base de données PostgreSQL
DB_NAME=Activities           # Nom de la base de données
DB_USER=data_user          # Utilisateur PostgreSQL
DB_PASSWORD=data_password       # Mot de passe PostgreSQL
DB_PORT=5432                # Port PostgreSQL

# Application Spring Boot
APP_PORT=8080               # Port d'exposition de l'application

# Configuration JPA/Hibernate
SPRING_JPA_HIBERNATE_DDL_AUTO=update  # Options: create, create-drop, update, validate
```

### Utilisation

#### Démarrer les services

```bash
docker-compose up -d
```

Cela va :
- Construire l'image PostgreSQL avec le schéma d'initialisation
- Construire l'image de l'application Spring Boot
- Démarrer les deux conteneurs en réseau

#### Arrêter les services

```bash
docker-compose down
```

#### Afficher les logs

```bash
# Tous les services
docker-compose logs -f

# PostgreSQL uniquement
docker-compose logs -f postgres

# Application uniquement
docker-compose logs -f backend
```

#### Accéder à l'application

- **API Spring Boot** : http://localhost:8080
- **Documentation Swagger** : http://localhost:8080/swagger-ui.html
- **PostgreSQL** : localhost:5432

#### Accéder à la base de données

```bash
# Directement dans le conteneur
docker-compose exec postgres psql -U api_client -d Activities

# Ou depuis votre machine (si PostgreSQL client est installé)
psql -h localhost -U api_client -d Activities -p 5432
```

### Structure des services

#### Service PostgreSQL (postgres)
- Image : postgres:16-alpine
- Port : 5432 (configurable via `DB_PORT`)
- Données persistantes : Volume `postgres_data`
- Initialisation : Script SQL depuis `data/init.sql`
- Health check : Vérifie l'accessibilité de PostgreSQL

#### Service Backend Spring Boot (backend)
- Image : Image Docker customisée buildée depuis le Dockerfile racine
- Port : 8080 (configurable via `APP_PORT`)
- Dépendance : Attend que PostgreSQL soit prêt
- Réseau : Communication interne via le hostname `postgres`

### Architecture

```
Activities-Backend/
├── Dockerfile                    # Build de l'application Spring Boot
├── docker-compose.yml            # Orchestration des services
├── .env                          # Configuration locale (ne pas commiter)
├── template.env                  # Template de configuration (à commiter)
├── data/
│   ├── Dockerfile                # Build de PostgreSQL
│   └── init.sql                  # Script d'initialisation de la BD
├── pom.xml                       # Dépendances Maven
└── src/                          # Code source
```

### Réseau

Les deux services communiquent via un réseau Docker personnalisé `activities-network` :
- Service PostgreSQL accessible via hostname `postgres` sur le port 5432
- Service Backend exposé sur `localhost:8080`

### Volumes

- `postgres_data` : Stockage persistent des données PostgreSQL

### Troubleshooting

#### Les conteneurs ne démarrent pas

```bash
# Vérifier les erreurs de build
docker-compose build --no-cache

# Vérifier les logs détaillés
docker-compose logs backend
```

#### Problèmes de connexion à la base de données

1. Vérifier que PostgreSQL est en bonne santé :
   ```bash
   docker-compose ps
   ```

2. Vérifier les variables d'environnement :
   ```bash
   docker-compose exec backend env | grep SPRING_DATASOURCE
   ```

3. Vérifier la connectivité réseau :
   ```bash
   docker-compose exec backend ping postgres
   ```

### Notes

- Les images sont buildées automatiquement au premier lancement
- Les volumes sont créés automatiquement
- Le réseau est créé automatiquement
- Les données PostgreSQL persistent entre les redémarrages (volume `postgres_data`)
- Pour reconstruire les images : `docker-compose build --no-cache`

## Authors
- [Bastien JACQUELIN](https://github.com/Basuw)
