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
package ru.fusionsoft.database.writable;

import org.cactoos.Text;
import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.Folder;
import ru.fusionsoft.database.Writable;
import ru.fusionsoft.database.snapshot.Objects;

/**
 * The type of {@link Writable} that represents db tables data
 *  of given {@link Objects}.
 * @since 0.1
 * @todo #40:60min Implement `TableDataFilesOfObjects` `Writable`
 * @checkstyle (100 lines)
 */
@SuppressWarnings("PMD")
public class OperationalTableDataFiles implements Writable {

    /**
     * Instantiates a new Operational table data files.
     * @param file The DbdFile to take tables from.
     * @param server The Text name of server to be taken data from.
     */
    public OperationalTableDataFiles(final DbdFile file, final Text server) {
    }

    @Override
    public void writeTo(final Folder folder) {
    }

}
