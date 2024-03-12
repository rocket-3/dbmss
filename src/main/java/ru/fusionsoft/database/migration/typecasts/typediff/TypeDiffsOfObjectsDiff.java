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
package ru.fusionsoft.database.migration.typecasts.typediff;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.database.migration.diff.DbdColumnsDiffOfTablesDiff;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.SimpleTemporalDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;
import ru.fusionsoft.lib.collection.UniqueBy;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

/**
 * Typecast data type names, extracted from given {@link ObjectsDiff}.
 * @since 0.1
 */
public class TypeDiffsOfObjectsDiff extends IterableOfScalarSticky<TemporalDiff<Text>> {

    /**
     * Instantiates a new Type diffs of objects diff.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     */
    public TypeDiffsOfObjectsDiff(final ObjectsDiff diff) {
        this(diff.changed());
    }

    /**
     * Instantiates a new Type diffs of objects diff.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     */
    public TypeDiffsOfObjectsDiff(final Iterable<TemporalDiff<DbObject<YamlNode>>> diff) {
        super(
            () -> {
                final Func<DbdColumnMapping, Text> typeof = col -> new TextOfYamlMappingKeyValue(
                    col,
                    DbdColumnFields.DBMSTYPE
                );
                return new UniqueBy<>(
                    item -> item.current().asString() + item.next().asString(),
                    new Joined<>(
                        new Joined<>(
                            new Mapped<Iterable<TemporalDiff<Text>>>(
                                changed -> new Mapped<>(
                                    change -> new SimpleTemporalDiff<>(
                                        typeof.apply(change.current()),
                                        typeof.apply(change.next())
                                    ),
                                    changed
                                ),
                                new Mapped<>(
                                    table -> new DbdColumnsDiffOfTablesDiff(table).changedBy(
                                        col -> typeof.apply(col).asString().hashCode()
                                    ),
                                    new Filtered<>(
                                        entry -> entry.current().signature().type().equalsTo(
                                            new ObjectTypeTable()
                                        ),
                                        diff
                                    )
                                )
                            )
                        )
                    )
                );
            }
        );
    }

    /**
     * Instantiates a new Type diffs of objects diff constructor args.
     * @param diff The {@link ObjectsDiff} constructor args.
     */
    @SafeVarargs
    public TypeDiffsOfObjectsDiff(final TemporalDiff<DbObject<YamlNode>>... diff) {
        this(new IterableOf<>(diff));
    }

}
