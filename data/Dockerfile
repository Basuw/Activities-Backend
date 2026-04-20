# Utilise l'image de base officielle PostgreSQL
FROM postgres:16-alpine

# Les variables d'environnement seront définies par docker-compose
# POSTGRES_DB, POSTGRES_USER, POSTGRES_PASSWORD

# Copie les fichiers d'initialisation SQL dans le conteneur
COPY init.sql /docker-entrypoint-initdb.d/

# Expose le port par défaut de PostgreSQL
EXPOSE 5432
