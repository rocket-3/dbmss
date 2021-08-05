package org.fusionsoft.database.artefacts.is;

import org.fusionsoft.database.artefacts.yaml.YamlDBDMapping;
import org.fusionsoft.database.artefacts.IS;
import org.fusionsoft.database.artefacts.Migration;

public class PersistentISFromDBD implements IS<YamlDBDMapping> {
    private final Migration migration;

    public PersistentISFromDBD(Migration migration) {
        this.migration = migration;
    }
    
    @Override
    public Migration restore() throws Exception {
        return this.migration ;
    }

    @Override
    public YamlDBDMapping describe() throws Exception {
        return null;
    }
}
