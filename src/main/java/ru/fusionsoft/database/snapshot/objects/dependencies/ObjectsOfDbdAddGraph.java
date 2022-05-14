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
import org.cactoos.iterable.Sticky;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeAndNameMatchesRegexp;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;

/**
 * The objects which should be added, when dbd/add/single procedure is being called,
 *  taken from server of dbd, filtered by type and name regexes,
 *  also taking all the dependencies of checked objects,
 *  the objects that depends on previous, and the dependencies of the whole set again.
 * @since 0.1
 */
public class ObjectsOfDbdAddGraph extends ObjectsOfScalar<YamlNode> {

    /**
     * Instantiates a new Objects of dbd add graph.
     * @param dbd The {@link DbdReadable} to be encapsulated.
     * @param server The server to find in DBD.
     * @param rgxtype The type name regex.
     * @param rgxname The object name regex.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public ObjectsOfDbdAddGraph(
        final DbdReadable dbd,
        final Text server,
        final Text rgxtype,
        final Text rgxname
    ) {
        super(
            () -> {
                final Iterable<DbObject<YamlNode>> ofserver = new Sticky<>(
                    new ObjectsOfServer(
                        new DbdServerMappingOfDbdReadable(
                            dbd,
                            server
                        )
                    )
                );
                return new ObjectsUpdatedWithTheirRelationsOfServerAndDbd<>(
                    new ObjectsWithTypeAndNameMatchesRegexp<>(
                        rgxtype,
                        rgxname,
                        ofserver
                    ),
                    ofserver,
                    new ObjectsOfDbdReadable(dbd)
                );
            }
        );
    }

}
