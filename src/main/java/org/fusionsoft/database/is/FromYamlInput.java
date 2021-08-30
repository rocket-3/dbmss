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

/**
 * The type of IS, can be constructed from {@link DBDYamlInput}.
 * @since 0.1
 */
public class FromYamlInput implements IS<YamlMapping> {

    /**
     * The Migration encapsulated.
     */
    private final Migration migration;

    /**
     * The DBDYamlInput encapsulated.
     */
    private final DBDYamlInput input;

    /**
     * Instantiates a new Persistent is from yaml dbd input.
     * @param migration The Migration to be encapsulated.
     * @param input The DBDYamlInput to be encapsulated.
     */
    private FromYamlInput(
        final Migration migration,
        final DBDYamlInput input
    ) {
        this.migration = migration;
        this.input = input;
    }

    /**
     * Instantiates a new Persistent is from yaml dbd input.
     * @param input The DBDYamlInput to be encapsulated.
     * @param server The CharSequence to be encapsulated.
     * @param params The RestoreParams to be encapsulated.
     */
    public FromYamlInput(
        final DBDYamlInput input,
        final CharSequence server,
        final RestoreParams params
    ) {
        this(
            new DBDToServerMigration(
                input,
                server,
                params
            ),
            input
        );
    }

    /**
     * Instantiates a new Persistent is from yaml dbd input.
     * @param path The Path to be encapsulated.
     * @param server The CharSequence to be encapsulated.
     * @param params The RestoreParams to be encapsulated.
     */
    public FromYamlInput(
        final Path path,
        final CharSequence server,
        final RestoreParams params
    ) {
        this(
            new DBDYamlInput(new YamlInputOf(path)),
            server,
            params
        );
    }

    @Override
    public final Migration restore() {
        return this.migration;
    }

    @Override
    public final YamlMapping describe() throws IOException {
        return this.input.readYamlMapping();
    }

}
