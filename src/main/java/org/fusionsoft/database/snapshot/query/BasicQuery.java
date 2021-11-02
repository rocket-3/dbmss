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
package org.fusionsoft.database.snapshot.query;

import java.util.Map;
import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.lib.collection.StrictMap;

/**
 * The Text of sql query, which SELECT outcomes
 *  are mapped 1 to 1 as aliases to some prop set.
 * @param <E> The outcome target Text subtype parameter.
 * @since 0.1
 */
@SuppressWarnings("PMD.UnusedFormalParameter")
public abstract class BasicQuery<E extends Text> extends TextEnvelope implements Query<E> {

    /**
     * The Map of String, String encapsulated.
     */
    private final Map<String, String> propmap;

    /**
     * Ctor.
     * @param query The Text to be encapsulated.
     * @param propmap The Map of String to String to be encapsulated.
     * @param javaisshit You know how to struggle with same type erasure error?
     */
    private BasicQuery(
        final Text query,
        final Map<String, String> propmap,
        final boolean javaisshit
    ) {
        super(query);
        this.propmap = propmap;
    }

    /**
     * Ctor.
     * @param query The String to be encapsulated.
     * @param propmap The Map of E extends Text to String to be encapsulated.
     */
    public BasicQuery(final Text query, final Map<E, String> propmap) {
        this(
            query,
            new StrictMap<>(
                new MapOf<>(
                    x -> new MapEntry<>(
                        new UncheckedText(x.getKey()).asString(),
                        x.getValue()
                    ),
                    propmap.entrySet()
                )
            ),
            true
        );
    }

    /**
     * Ctor.
     * @param query The String to be encapsulated.
     * @param propmap The Map of E extends Text to String to be encapsulated.
     */
    public BasicQuery(final String query, final Map<E, String> propmap) {
        this(
            new TextOf(query),
            propmap
        );
    }

    @Override
    public final String outcomeFor(final E prop) {
        return this.propmap.get(new UncheckedText(prop).asString());
    }

}
