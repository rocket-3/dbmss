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
package ru.fusionsoft.database.snapshot.objects.predicate;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.iterable.Sticky;
import org.cactoos.scalar.Or;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadable;

/**
 * The predicate of {@link DbObject} which tests it presents in DBD file
 *  or is a partition of one of its table.
 * @param <T> The type of YamlNode parameter.
 * @since 0.1
 */
public class ObjectMentionedInDbdFilePredicate<T extends YamlNode>
    implements Func<DbObject<T>, Boolean> {

    /**
     * The {@link ObjectMentionedInObjectsPredicate} encapsulated.
     */
    private final ObjectMentionedInObjectsPredicate mentioned;

    /**
     * The {@link TargetObjectParentIsOneOfObjectsPredicate} encapsulated.
     */
    private final TargetObjectParentIsOneOfObjectsPredicate parent;

    /**
     * Instantiates a new Objects in dbd predicate.
     * @param filter The {@link Iterable} of {@link DbObject}s to match by, encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ObjectMentionedInDbdFilePredicate(
        final Iterable<DbObject<Y>> filter
    ) {
        this.mentioned = new ObjectMentionedInObjectsPredicate(filter);
        this.parent = new TargetObjectParentIsOneOfObjectsPredicate(filter);
    }

    /**
     * Instantiates a new Objects in dbd predicate.
     * @param file The DbdFile to be encapsulated.
     */
    public ObjectMentionedInDbdFilePredicate(final DbdReadable file) {
        this(
            new Sticky<>(
                new ObjectsOfDbdReadable(file)
            )
        );
    }

    @Override
    public final Boolean apply(final DbObject<T> input) {
        return new Unchecked<>(
            new Or(
                input,
                this.mentioned,
                this.parent
            )
        ).value();
    }

}
