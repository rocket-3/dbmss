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
package ru.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * {@link DbObject} with some suffix, added to object name.
 * @since 0.1
 */
public class ObjectWithNameSuffix extends SimpleDbObject<YamlNode> {

    /**
     * Instantiates a new Object with name suffix.
     * @param origin The {@link DbObject} to be cloned.
     * @param suffix The suffix {@link Text} to be encapsulated.
     */
    public ObjectWithNameSuffix(final DbObject<YamlNode> origin, final Text suffix) {
        super(
            origin.asYaml(),
            new SimpleObjectSignature(
                new SimpleObjectName(
                    new TextOfMessageFormat(
                        "{0}{1}",
                        origin.signature().name(),
                        suffix
                    )
                ),
                origin.signature().type()
            )
        );
    }

}
