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

package ru.fusionsoft.database.snapshot.objects.ofdbd;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.iterable.IterableEnvelope;
import ru.fusionsoft.database.mapping.dbd.DbdFunctionMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

/**
 * Functions {@link DbObject}'s constructed of given 'functions' mapping,
 *  its key and parent schema object name.
 * @since 0.1
 */
public class ObjectsOfDbdFunctionsMapping extends IterableEnvelope<DbObject<DbdFunctionMapping>> {

    /**
     * Instantiates a new Objects of functions.
     * @param functions The YamlMapping to be encapsulated.
     * @param schema The Text to be encapsulated.
     */
    public ObjectsOfDbdFunctionsMapping(final YamlMapping functions, final ObjectName schema) {
        super(
            new IterableOfClassFromYamlNode<DbObject<DbdFunctionMapping>>(
                (parent, key) -> new ObjectOfDbdFunctionMapping(parent, key, schema),
                functions
            )
        );
    }

}
