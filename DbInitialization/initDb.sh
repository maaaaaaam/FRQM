#!/bin/bash

echo "Waiting for the SQL Server ..."

until /opt/mssql-tools/bin/sqlcmd -S mssql -U sa -P MyStrongPassword123! -Q "SELECT 1" &> /dev/null
do
echo "The SQL Server isn't ready, trying to reconnect ..."
sleep 2
done

echo "Connected to the SQL Server. Initializing the database with the script ..."

/opt/mssql-tools/bin/sqlcmd -S mssql -U sa -P MyStrongPassword123! -i /scripts/init.sql

echo "The database is successfully initialized with the script"