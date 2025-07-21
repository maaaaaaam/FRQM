FROM mcr.microsoft.com/mssql-tools

COPY init.sql /scripts/init.sql
COPY initDb.sh /scripts/initDb.sh

RUN chmod +x /scripts/initDb.sh

ENTRYPOINT ["/scripts/initDb.sh"]