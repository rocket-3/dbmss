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
import org.cactoos.Func;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapOf;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * This is the way to sort or filter iterable of DbObjects TemporalDiffs
 *  using iterable of DbObjects decorator, the original objects are not changed
 *  and the original objects are the result, except different order and some may be not included.
 * @since 0.1
 */
public class ObjectsOfObjectsDiff
    extends IterableOfScalarSticky<TemporalDiff<DbObject<YamlNode>>> {

    /**
     * Instantiates a new Objects diffs decorated as objects.
     * @param which Func to select current or next {@link DbObject}s of TemporalDiff.
     * @param decorate The decorator to apply to iterable of selected objects.
     * @param objectsdiff The TemporalDiff iterable to be encapsulated.
     * @checkstyle LineLengthCheck (100 lines).
     */
    public ObjectsOfObjectsDiff(
        final Func<TemporalDiff<DbObject<YamlNode>>, DbObject<YamlNode>> which,
        final Func<Iterable<DbObject<YamlNode>>, ? extends Iterable<? extends DbObject<? extends YamlNode>>> decorate,
        final Iterable<TemporalDiff<DbObject<YamlNode>>> objectsdiff
    ) {
        super(
            () -> {
                final MapOf<ObjectSignature, TemporalDiff<DbObject<YamlNode>>> map = new MapOf<>(
                    item -> item.current().signature(),
                    item -> item,
                    objectsdiff
                );
                return new Mapped<>(
                    item -> map.get(item.signature()),
                    decorate.apply(
                        new Mapped<>(
                            which,
                            objectsdiff
                        )
                    )
                );
            }
        );
    }

}
