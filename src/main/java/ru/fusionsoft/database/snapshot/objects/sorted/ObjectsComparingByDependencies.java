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
package ru.fusionsoft.database.snapshot.objects.sorted;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.collection.StrictMap;

/**
 * The independent object is the 'biggest', so close to tail.
 * Can't handle circular dependencies.
 * @since 0.1
 */
public class ObjectsComparingByDependencies implements Comparator<DbObject<YamlNode>> {

    /**
     * The dependencies map.
     */
    private final Map<DbObject<YamlNode>, Set<DbObject<YamlNode>>> dependencies;

    /**
     * Instantiates a new Objects comparing by dependencies.
     * @param dependencies The dependencies map to be encapsulated.
     */
    public ObjectsComparingByDependencies(
        final Map<DbObject<YamlNode>, Set<DbObject<YamlNode>>> dependencies
    ) {
        this.dependencies = new StrictMap<>(dependencies);
    }

    @Override
    public final int compare(final DbObject<YamlNode> first, final DbObject<YamlNode> second) {
        final Set<DbObject<YamlNode>> firston = this.dependencies.get(first);
        final Set<DbObject<YamlNode>> secondon = this.dependencies.get(second);
        final Integer value = new Unchecked<>(
            new Ternary<Integer>(
                () -> firston.contains(second),
                () -> 1,
                new Ternary<>(
                    () -> secondon.contains(first),
                    () -> -1,
                    () -> 0
                )
            )
        ).value();
        return new Unchecked<>(
            new Ternary<Integer>(
                () -> value != 0,
                () -> value,
                () -> Math.round(Math.signum(firston.size() - secondon.size()))
            )
        ).value();
    }

}
