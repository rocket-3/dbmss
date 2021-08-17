package org.fusionsoft.database.is;

import com.amihaiemil.eoyaml.YamlMapping;
import java.io.IOException;
import java.nio.file.Path;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.migration.DBDToServerMigration;
import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.database.IS;
import org.fusionsoft.database.Migration;
import org.fusionsoft.lib.yaml.YamlInputOf;

public class PersistentISFromYamlDBDInput implements IS<YamlMapping> {
    private final Migration migration;
    private final DBDYamlInput dbdYamlInput;

    private PersistentISFromYamlDBDInput(Migration migration, DBDYamlInput dbdYamlInput) {
        this.migration = migration;
        this.dbdYamlInput = dbdYamlInput;
    }
    
    public PersistentISFromYamlDBDInput(DBDYamlInput dbdYamlInput, CharSequence serverName, RestoreParams restoreParams) {
        this(
            new DBDToServerMigration(
                dbdYamlInput,
                serverName,     
                restoreParams
            ),
            dbdYamlInput
        );
    }
    
    public PersistentISFromYamlDBDInput(Path pathToDbd, CharSequence serverName, RestoreParams restoreParams) {
        this(
            new DBDYamlInput(new YamlInputOf(pathToDbd)),
            serverName,     
            restoreParams   
        );
    }
    
    @Override
    public Migration restore() {
        return this.migration;
    }

    @Override
    public YamlMapping describe() throws IOException {
        return this.dbdYamlInput.readYamlMapping();
    }
}
