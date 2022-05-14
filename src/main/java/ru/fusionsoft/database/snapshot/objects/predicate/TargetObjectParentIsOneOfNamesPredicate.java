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
import java.util.Set;
import org.cactoos.Func;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOfYamlMapping;

/**
 * The predicate, testing object has 'parent' node pointing at some of given objects name.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class TargetObjectParentIsOneOfNamesPredicate
    implements Func<DbObject<? extends YamlNode>, Boolean> {

    /**
     * The Set of String of signatures encapsulated.
     */
    private final Unchecked<Set<String>> names;

    /**
     * Instantiates a new Objects with names.
     * @param names The Signatures to be encapsulated.
     */
    public TargetObjectParentIsOneOfNamesPredicate(final Iterable<ObjectSignature> names) {
        this.names = new Unchecked<>(
            new Sticky<>(
                () -> new SetOf<String>(
                    new Mapped<>(
                        x -> x.name().asString(),
                        new Filtered<>(
                            x -> x.type().equalsTo(new ObjectTypeTable()),
                            names
                        )
                    )
                )
            )
        );
    }

    @Override
    public final Boolean apply(final DbObject input) {
        final String parent = new MaybeEmptyTextOfYamlMapping(
            input.asYaml().asMapping(),
            DbdTableFields.PARENT
        ).asString();
        return new Unchecked<>(
            new Ternary<>(
                parent::isEmpty,
                () -> false,
                () -> this.names.value().contains(parent)
            )
        ).value();
    }

}
