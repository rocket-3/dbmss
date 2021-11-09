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
package org.fusionsoft.database.snapshot.objects.resultset;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.func.UncheckedFunc;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The shorthand constructor of {@link SimpleDbObject}.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class ObjectOfEntries<T extends YamlMapping> extends SimpleDbObject<T> {

    /**
     * Instantiates a new simple db object.
     * @param type The ObjectType to be encapsulated.
     * @param ctor The Func of YamlMapping -> T to be encapsulated.
     * @param signature The Iterable of Text to be encapsulated.
     * @param entries The Iterable of Entries of Text, YamlNode to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public ObjectOfEntries(
        final ObjectType type,
        final Func<YamlMapping, T> ctor,
        final Iterable<Text> signature,
        final Iterable<? extends Map.Entry<? extends Text, ? extends YamlNode>> entries
    ) {
        this(
            type,
            ctor,
            new FullObjectName(signature),
            entries
        );
    }
    /**
     * Instantiates a new simple db object.
     * @param type The ObjectType to be encapsulated.
     * @param ctor The Func of YamlMapping -> T to be encapsulated.
     * @param signature The Iterable of Text to be encapsulated.
     * @param entries The Iterable of Entries of Text, YamlNode to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public ObjectOfEntries(
        final ObjectType type,
        final Func<YamlMapping, T> ctor,
        final FullObjectName signature,
        final Iterable<? extends Map.Entry<? extends Text, ? extends YamlNode>> entries
    ) {
        super(
            new UncheckedFunc<>(ctor).apply(
                new MappingWithoutNullScalars(
                    new YamlMappingOfEntries(
                        entries
                    )
                )
            ),
            new SimpleObjectSignature(signature, type)
        );
    }

}
