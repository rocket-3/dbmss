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

public abstract class Query<E extends Text> extends TextEnvelope {

    private final Map<String, String> propmap;

    /**
     * Ctor.
     */
    private Query(final Text query, final Map<String, String> propmap) {
        super(query);
        this.propmap = propmap;
    }

    /**
     * Ctor.
     */
    public Query(final String query, final Map<E, String> propmap) {
        this(
            new TextOf(query),
            new MapOf<>(
                x -> new MapEntry<>(
                    new UncheckedText(x.getKey()).asString(),
                    x.getValue()
                ),
                propmap.entrySet()
            )
        );
    }

    public final String outcomeFor(final E prop) {
        return this.propmap.get(new UncheckedText(prop).asString());
    }

}
