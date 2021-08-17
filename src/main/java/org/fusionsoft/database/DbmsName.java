package org.fusionsoft.database;

import org.cactoos.Text;

public interface DbmsName extends Text {
    DbmsName Postgres = () -> "pg";
    DbmsName Oracle = () -> "ora";
    DbmsName MsSqlServer = () -> "mssql";
    DbmsName MySql = () -> "mysql";
    DbmsName Absent = () -> "absent";
    DbmsName Unknown = () -> "unknown";
}
