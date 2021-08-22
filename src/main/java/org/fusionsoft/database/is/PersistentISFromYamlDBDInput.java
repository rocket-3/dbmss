/*
 * Copyright (C) 2018-2021 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.fusionsoft.database.is;

import com.amihaiemil.eoyaml.YamlMapping;
import java.io.IOException;
import java.nio.file.Path;
import org.fusionsoft.database.IS;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.migration.DBDToServerMigration;
import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.lib.yaml.YamlInputOf;

public class PersistentISFromYamlDBDInput implements IS<YamlMapping> {

    private final Migration migration;

    private final DBDYamlInput dbdYamlInput;

    private PersistentISFromYamlDBDInput(final Migration migration, final DBDYamlInput dbdYamlInput) {
        this.migration = migration;
        this.dbdYamlInput = dbdYamlInput;
    }

    public PersistentISFromYamlDBDInput(final DBDYamlInput dbdYamlInput, final CharSequence serverName, final RestoreParams restoreParams) {
        this(
            new DBDToServerMigration(
                dbdYamlInput,
                serverName,
                restoreParams
            ),
            dbdYamlInput
        );
    }

    public PersistentISFromYamlDBDInput(final Path pathToDbd, final CharSequence serverName, final RestoreParams restoreParams) {
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
