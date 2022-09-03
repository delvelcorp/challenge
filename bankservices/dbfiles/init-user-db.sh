#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER usr_develcorp WITH PASSWORD 'develcorppass';
    CREATE DATABASE db_accounts WITH OWNER usr_develcorp;
    CREATE DATABASE db_customers WITH OWNER usr_develcorp;
    CREATE DATABASE db_transactions WITH OWNER usr_develcorp;
EOSQL
