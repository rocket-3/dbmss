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
package ru.fusionsoft.database.snapshot.objects.signature.type;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.lib.collection.StrictMap;
import ru.fusionsoft.lib.exception.ValueNotFoundException;

/**
 * The {@link ObjectType} is any of {@link SimpleObjectType}s,
 *  which text representation matches given text.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class SimpleObjectTypeOfText extends ObjectTypeOfScalar<YamlNode> {

    /**
     * Instantiates a {@link ObjectType} which would be .
     * @param text The {@link Text} to be encapsulated.
     * @throws ValueNotFoundException The value not found exception.
     */
    public SimpleObjectTypeOfText(final Text text) throws ValueNotFoundException {
        super(
            () -> new StrictMap<>(
                new MapOf<String, ObjectType<? extends YamlNode>>(
                    new Mapped<>(
                        x -> new MapEntry<>(
                            x.asString(),
                            x
                        ),
                        new IterableOf<ObjectType<?>>(
                            new ObjectTypeConstraint(),
                            new ObjectTypeData(),
                            new ObjectTypeDomain(),
                            new ObjectTypeEnum(),
                            new ObjectTypeFunction(),
                            new ObjectTypeIndex(),
                            new ObjectTypeProcedure(),
                            new ObjectTypeRole(),
                            new ObjectTypeSchema(),
                            new ObjectTypeSequence(),
                            new ObjectTypeTable(),
                            new ObjectTypeTablespace(),
                            new ObjectTypeTrigger(),
                            new ObjectTypeTuple(),
                            new ObjectTypeUser(),
                            new ObjectTypeView()
                        )
                    )
                )
            ).get(text.asString())
        );
    }

}
