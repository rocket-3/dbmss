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
package org.fusionsoft.database.mapping.entries;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import java.util.Set;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.set.SetOf;

/**
 * The type of {@link Text} -> {@link YamlNode} {@link Map.Entry}s, filtered by keys.
 * @since 0.1
 */
public class EntriesWithKeys
    extends IterableEnvelope<Map.Entry<? extends Text, ? extends YamlNode>> {

    /**
     * Ctor.
     * @param entries The wrapped entries
     * @param keys The Set of String keys to include.
     */
    public EntriesWithKeys(
        final Iterable<Map.Entry<? extends Text, ? extends YamlNode>> entries,
        final Set<String> keys
    ) {
        super(
            new Filtered<>(
                entry -> keys.contains(entry.getKey().asString()),
                entries
            )
        );
    }

    /**
     * Ctor.
     * @param entries The wrapped entries
     * @param keys The Iterable of Text keys to include.
     */
    public EntriesWithKeys(
        final Iterable<Map.Entry<? extends Text, ? extends YamlNode>> entries,
        final Iterable<Text> keys
    ) {
        this(
            entries,
            new SetOf<>(
                new Mapped<>(
                    Text::asString,
                    keys
                )
            )
        );
    }

    /**
     * Ctor.
     * @param entries The wrapped entries
     * @param keys The Text... keys to include.
     */
    public EntriesWithKeys(
        final Iterable<Map.Entry<? extends Text, ? extends YamlNode>> entries,
        final Text... keys
    ) {
        this(entries, new IterableOf<Text>(keys));
    }

}
