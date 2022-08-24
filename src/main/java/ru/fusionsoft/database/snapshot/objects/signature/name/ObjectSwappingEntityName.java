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
package ru.fusionsoft.database.snapshot.objects.signature.name;

import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;

/**
 * The {@link ObjectName} with {@link SwappingEntityNameSuffix}.
 * @since 0.1
 */
public class ObjectSwappingEntityName extends SimpleObjectNameOfText {

    /**
     * Instantiates a new Object swapping entity name.
     * @param name The {@link ObjectName} to be encapsulated.
     */
    public ObjectSwappingEntityName(final ObjectName name) {
        super(
            new Joined(
                new TextOf(""),
                name,
                new SwappingEntityNameSuffix()
            )
        );
    }

}
