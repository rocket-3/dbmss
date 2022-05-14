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
package ru.fusionsoft.database.snapshot.objects.dependencies;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeAndNameMatchesRegexp;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;

/**
 * The objects which should be added, when dbd/add/single procedure is being called,
 *  taken from server of dbd, filtered by type and name regexes.
 * @since 0.1
 */
public class ObjectsOfDbdAddSingle extends ObjectsOfScalar<YamlNode> {

    /**
     * Instantiates a new Objects of add single.
     * @param dbd The {@link DbdReadable} to be encapsulated.
     * @param server The server to find in DBD.
     * @param rgxtype The type name regex.
     * @param rgxname The object name regex.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public ObjectsOfDbdAddSingle(
        final DbdReadable dbd,
        final Text server,
        final Text rgxtype,
        final Text rgxname
    ) {
        super(
            () -> {
                return new ObjectsWithTypeAndNameMatchesRegexp<>(
                    rgxtype,
                    rgxname,
                    new ObjectsOfServer(
                        new DbdServerMappingOfDbdReadable(
                            dbd,
                            server
                        )
                    )
                );
            }
        );
    }

}
