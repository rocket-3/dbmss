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
package ru.fusionsoft.database.diff.objects;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Sticky;
import ru.fusionsoft.database.diff.SimpleTemporalDiff;
import ru.fusionsoft.database.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsMentionedInObjects;
import ru.fusionsoft.lib.map.MapOfScalar;

public class ChangedObjectsOfDiff<Y extends YamlNode>
    extends MapEnvelope<ObjectSignature, TemporalDiff<DbObject<Y>>> {

    public ChangedObjectsOfDiff(final TemporalDiff<Iterable<DbObject<Y>>> diff) {
        super(
            new MapOfScalar<ObjectSignature, TemporalDiff<DbObject<Y>>>(
                new Sticky<>(
                    () -> {
                        final Map<ObjectSignature, DbObject<Y>> mapcur = new MapOf<>(
                            DbObject::signature,
                            obj -> obj,
                            new org.cactoos.iterable.Sticky<>(
                                new ObjectsMentionedInObjects<>(diff.next(), diff.current())
                            )
                        );
                        final Map<ObjectSignature, DbObject<Y>> mapnext = new MapOf<>(
                            DbObject::signature,
                            obj -> obj,
                            new org.cactoos.iterable.Sticky<>(
                                new ObjectsMentionedInObjects<>(diff.current(), diff.next())
                            )
                        );
                        return new MapOf<>(
                            new Filtered<>(
                                entry -> !entry.getValue().current().asYaml().toString().equals(
                                    entry.getValue().next().asYaml().toString()
                                ),
                                new Mapped<Map.Entry<ObjectSignature, TemporalDiff<DbObject<Y>>>>(
                                    key -> new MapEntry<>(
                                        key,
                                        new SimpleTemporalDiff<>(
                                            mapcur.get(key),
                                            mapnext.get(key)
                                        )
                                    ),
                                    mapcur.keySet()
                                )
                            )
                        );
                    }
                )
            )
        );
    }

}
