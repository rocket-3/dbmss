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
import ru.fusionsoft.database.migration.order.OrderOfObjectType;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * Comparing {@link DbObject}s by {@link ObjectType} and {@link OrderOfObjectType}.
 * @since 0.1
 */
public class ObjectsComparingByType implements Comparator<DbObject<YamlNode>> {

    @Override
    public final int compare(final DbObject<YamlNode> first, final DbObject<YamlNode> second) {
        return Comparator.comparing(
            (DbObject<?> ot) -> new OrderOfObjectType().apply(ot.signature().type())
        ).compare(first, second);
    }

}
