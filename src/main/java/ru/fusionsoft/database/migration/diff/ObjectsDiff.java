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
package ru.fusionsoft.database.migration.diff;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Sticky;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The {@link SmartIterableTemporalDiff} of {@link DbObject}s iterables.
 * @since 0.1
 */
public class ObjectsDiff extends SmartIterableTemporalDiff<DbObject<YamlNode>> {

    /**
     * Instantiates a new Objects diff.
     * @param current The current state iterable of {@link DbObject}s to be encapsulated.
     * @param next The next state iterable of {@link DbObject}s to be encapsulated.
     */
    public ObjectsDiff(
        final Iterable<DbObject<YamlNode>> current,
        final Iterable<DbObject<YamlNode>> next
    ) {
        super(
            DbObject::signature,
            new Sticky<DbObject<YamlNode>>(current),
            new Sticky<DbObject<YamlNode>>(next)
        );
    }

    /**
     * Instantiates a new Objects diff.
     * @param diff The {@link TemporalDiff} of iterable of {@link DbObject}s to be encapsulated.
     */
    public ObjectsDiff(final TemporalDiff<? extends Iterable<DbObject<YamlNode>>> diff) {
        this(diff.current(), diff.next());
    }

    /**
     * Changed objects iterable.
     * @return The iterable.
     */
    public final Iterable<TemporalDiff<DbObject<YamlNode>>> changed() {
        return this.changedBy(object -> object.asYaml().toString().hashCode());
    }

}
