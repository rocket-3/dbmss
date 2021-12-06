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
package org.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;
import org.fusionsoft.database.snapshot.objects.signature.ObjectType;
import org.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import org.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The shorthand constructor of {@link SimpleDbObject}.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class SimpleDbObjectOfEntries<T extends YamlMapping> extends SimpleDbObject<T> {

    /**
     * Instantiates a new simple db object.
     * @param type The {@link ObjectType} to be encapsulated.
     * @param name The {@link ObjectName} to be encapsulated.
     * @param entries The Iterable of Entries of Text, YamlNode to be encapsulated.
     */
    public SimpleDbObjectOfEntries(
        final ObjectType<T> type,
        final ObjectName name,
        final Iterable<? extends Map.Entry<? extends Text, ? extends YamlNode>> entries
    ) {
        super(
            type.node(
                new MappingWithoutNullScalars(
                    new YamlMappingOfEntries(
                        entries
                    )
                )
            ),
            new SimpleObjectSignature(name, type)
        );
    }

    /**
     * Instantiates a new simple db object.
     * @param type The ObjectType to be encapsulated.
     * @param names The Iterable of Text to be encapsulated.
     * @param entries The Iterable of Entries of Text, YamlNode to be encapsulated.
     */
    public SimpleDbObjectOfEntries(
        final ObjectType<T> type,
        final Iterable<Text> names,
        final Iterable<? extends Map.Entry<? extends Text, ? extends YamlNode>> entries
    ) {
        this(
            type,
            new SimpleObjectName(names),
            entries
        );
    }

    /**
     * Instantiates a new simple db object.
     * @param type The {@link ObjectType} to be encapsulated.
     * @param name The {@link ObjectName} to be encapsulated.
     * @param entries The Iterable of Entries of Text, YamlNode to be encapsulated.
     */
    @SafeVarargs
    public SimpleDbObjectOfEntries(
        final ObjectType<T> type,
        final ObjectName name,
        final Map.Entry<? extends Text, ? extends YamlNode>... entries
    ) {
        this(type, name, new IterableOf<>(entries));
    }

}
