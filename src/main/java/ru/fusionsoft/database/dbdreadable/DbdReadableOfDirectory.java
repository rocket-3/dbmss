/*
 * Copyright (C) 2018-2022 FusionSoft
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
package ru.fusionsoft.database.dbdreadable;

import java.nio.file.Path;
import org.cactoos.Text;
import org.cactoos.text.UncheckedText;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.text.DbdFileName;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.yaml.YamlMappingOf;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The {@link DbdReadable} that is taken from {@link Directory}.
 * @since 0.1
 */
public class DbdReadableOfDirectory extends DbdReadableOfMapping {

    /**
     * Instantiates a new Dbd file of mapping.
     * @param directory The {@link Directory} to be encapsulated.
     * @param name The name of file in directory.
     */
    public DbdReadableOfDirectory(final Directory directory, final Text name) {
        this(
            directory.value().resolve(
                new UncheckedText(name).asString()
            )
        );
    }

    /**
     * Instantiates a new Dbd readable of directory.
     * @param path The {@link Path} to be encapsulated.
     */
    public DbdReadableOfDirectory(final Path path) {
        super(
            new YamlMappingOfScalar(
                () -> new YamlMappingOf(
                    path
                )
            )
        );
    }

    /**
     * Instantiates a new Dbd file of mapping.
     * @param directory The {@link Directory} to be encapsulated.
     */
    public DbdReadableOfDirectory(final Directory directory) {
        this(
            directory,
            new DbdFileName()
        );
    }

}
