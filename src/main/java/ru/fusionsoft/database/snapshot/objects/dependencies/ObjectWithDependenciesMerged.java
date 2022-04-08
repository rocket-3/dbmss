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

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectUpdated;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.lib.yaml.MappingMerged;
import ru.fusionsoft.lib.yaml.MappingWithoutNullScalarsNested;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;
import ru.fusionsoft.lib.yaml.YamlSequenceOfNodes;

public class ObjectWithDependenciesMerged extends ObjectUpdated<YamlMapping> {

    public ObjectWithDependenciesMerged(
        final Iterable<? extends ObjectName> dependencies,
        final DbObject<?> object
    ) {
        super(
            x -> new MappingMerged(
                x.asMapping(),
                new MappingWithoutNullScalarsNested(
                    new YamlMappingOfEntries(
                        new MapEntry<DbdTableFields, YamlNode>(
                            DbdTableFields.DEPENDENCIES,
                            new YamlSequenceOfNodes(
                                new Mapped<YamlNode>(
                                    text -> Yaml.createYamlScalarBuilder()
                                        .addLine(text.asString())
                                        .buildPlainScalar(),
                                    new SetOf<>(
                                        new Joined<ObjectName>(
                                            new SignaturesOfObjectDependencies(object),
                                            dependencies
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            ),
            object
        );
    }

}
