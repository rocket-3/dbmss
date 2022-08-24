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
package ru.fusionsoft.database.migration.condition;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The predicate {@link org.cactoos.Scalar} checks
 *  all current changed objects of {@link TemporalDiff} of Iterable of {@link DbObject}s
 *  satisfy given predicate func.
 * @since 0.1
 */
public class HasCurrentChangingObjectsThatSatisfy extends HasObjectsThatSatisfy {

    /**
     * Instantiates a new Has current changing objects that satisfy.
     * @param diff The {{@link TemporalDiff} of Iterable of {@link DbObject}s to be encapsulated.
     * @param predicate The predicate {@link Func} to be encapsulated.
     */
    public HasCurrentChangingObjectsThatSatisfy(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Func<DbObject<YamlNode>, Boolean> predicate
    ) {
        super(
            new Mapped<>(
                TemporalDiff::current,
                new ObjectsDiff(diff).changed()
            ),
            predicate
        );
    }

    /**
     * Instantiates a new Has current changing objects that satisfy.
     * @param predicate The predicate {@link Func} to be encapsulated.
     * @param diff The {@link TemporalDiff} of Iterable of {@link DbObject}s to be encapsulated.
     */
    public HasCurrentChangingObjectsThatSatisfy(
        final Func<DbObject<YamlNode>, Boolean> predicate,
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff
    ) {
        this(diff, predicate);
    }

}
