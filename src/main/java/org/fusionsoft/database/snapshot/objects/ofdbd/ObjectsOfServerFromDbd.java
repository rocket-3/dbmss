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
package org.fusionsoft.database.snapshot.objects.ofdbd;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Text;
import org.fusionsoft.database.DbdFile;
import org.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdFile;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import org.fusionsoft.database.snapshot.objects.ofdbms.ObjectsFromServer;
import org.fusionsoft.database.snapshot.objects.predicate.ObjectMentionedInDbdFilePredicate;

/**
 * The type of {@link Objects} from database mentioned in {@link DbdFile}'s
 *  that names present in {@link org.fusionsoft.database.mapping.dbd.DbdServersMapping} only.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class ObjectsOfServerFromDbd extends ObjectsFiltered<YamlMapping> {

    /**
     * Instantiates a new Objects from server mentioned in dbd.
     * @param file The DbdFile to be encapsulated.
     * @param server The Text of server name encapsulated.
     */
    public ObjectsOfServerFromDbd(
        final DbdFile file,
        final Text server
    ) {
        super(
            new ObjectsFromServer(
                new DbdServerMappingOfDbdFile(file, server)
            ),
            new ObjectMentionedInDbdFilePredicate(file)
        );
    }

}
