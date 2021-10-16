/*
 * Copyright (C) 2018-2021 FusionSoft
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
package org.fusionsoft.database.snapshot.objects.filtered;

import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectsFiltered;

/**
 * The Objects filtered by parent name.
 * @since 0.1
 */
public class ObjectsWithParent extends ObjectsFiltered {

    /**
     * Ctor.
     * @param parent The Text name of parent to be encapsulated.
     * @param objects The Objects to be encapsulated.
     */
    public ObjectsWithParent(final Text parent, final Objects objects) {
        super(
            obj -> obj.signature().name().parent().asString().equals(parent.asString()),
            objects
        );
    }

    /**
     * Ctor.
     * @param parent The DbObject of parent to be encapsulated.
     * @param objects The Objects to be encapsulated.
     */
    public ObjectsWithParent(final DbObject<?> parent, final Objects objects) {
        this(
            new TextOfScalar(() -> parent.signature().name().first().asString()),
            objects
        );
    }

}
