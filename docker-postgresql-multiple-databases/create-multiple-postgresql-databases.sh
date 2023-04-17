#!/bin/bash

set -e
set -u

function create_multiple_user_and_db() {
	#local database=$1
	#echo "  Creating user and database '$database'"

	sqls=""
	for db in $(echo $POSTGRES_MULTIPLE_DATABASES | tr ',' ' '); do
	    sqls+="CREATE USER $db; 
	    CREATE DATABASE $db; 
	    GRANT ALL PRIVILEGES ON DATABASE $db TO $db; 
	    ALTER USER $db PASSWORD '$db';
	    "
	done
	#echo "  Run creating DBs script: \n '$sqls'\n"
	psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
		$sqls
EOSQL
}

if [ -n "$POSTGRES_MULTIPLE_DATABASES" ]; then
	echo "Multiple database creation requested: $POSTGRES_MULTIPLE_DATABASES"

	create_multiple_user_and_db
	echo "Multiple databases created"
fi
