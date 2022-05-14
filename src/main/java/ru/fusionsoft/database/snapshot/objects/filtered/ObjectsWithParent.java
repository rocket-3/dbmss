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
package ru.fusionsoft.database.snapshot.objects.filtered;

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;

/**
 * The Objects filtered by parent name.
 * @param <T> The type of {@link DbObject} parameter.
 * @since 0.1
 */
public class ObjectsWithParent<T extends YamlNode> extends ObjectsFiltered<T> {

    /**
     * Ctor.
     * @param parent The Text name of parent to be encapsulated.
     * @param objects The {@link Iterable} of {@link DbObject}s to be encapsulated.
     */
    public ObjectsWithParent(final DbObject<?> parent, final Iterable<DbObject<T>> objects) {
        super(
            obj -> obj.signature().name().parent().equalsTo(
                parent.signature().name()
            ),
            objects
        );
    }

    /**
     * Ctor.
     * @param name The {@link SimpleObjectName} of parent to be encapsulated.
     * @param objects The {@link Iterable} of {@link DbObject}s to be encapsulated.
     */
    public ObjectsWithParent(final ObjectName name, final Iterable<DbObject<T>> objects) {
        super(
            obj -> obj.signature().name().parent().equalsTo(name),
            objects
        );
    }

}
