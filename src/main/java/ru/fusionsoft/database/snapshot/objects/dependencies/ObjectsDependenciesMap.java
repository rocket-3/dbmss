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
package ru.fusionsoft.database.snapshot.objects.dependencies;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Set;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.And;
import org.cactoos.set.SetOf;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * Map of {@link DbObject}'s dependencies set.
 * @since 0.1
 */
public class ObjectsDependenciesMap
    extends MapEnvelope<DbObject<YamlNode>, Set<DbObject<YamlNode>>> {

    /**
     * Ctor.
     * @param objects The objects map.
     */
    public ObjectsDependenciesMap(final Iterable<DbObject<YamlNode>> objects) {
        super(
            new MapOf<DbObject<YamlNode>, Set<DbObject<YamlNode>>>(
                new Mapped<>(
                    object -> {
                        final Iterable<Func<DbObject<YamlNode>, Boolean>> checks = new IterableOf<>(
                            new ObjectDependsOnAnotherPredicate<>(object)
                        );
                        return new MapEntry<>(
                            object,
                            new SetOf<>(
                                new Filtered<>(
                                    sub -> new And(sub, checks).value(),
                                    objects
                                )
                            )
                        );
                    },
                    objects
                )
            )
        );
    }

    /**
     * As string string.
     * @return The string.
     */
    public final String asString() {
        return new UncheckedText(
            new Joined(
                new TextOf("\n"),
                new Mapped<Text>(
                    entry -> new TextOfMessageFormat(
                        "{0}: ({1})",
                        entry.getKey().signature().name(),
                        new Joined(
                            ",",
                            new Mapped<>(
                                o -> o.signature().name().asString(),
                                entry.getValue()
                            )
                        )
                    ),
                    new Filtered<>(
                        entry -> entry.getKey().signature().type().equalsTo(new ObjectTypeTable()),
                        this.entrySet()
                    )
                )
            )
        ).asString();
    }

}
