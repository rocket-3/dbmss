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
package ru.fusionsoft.database.snapshot.objects;

import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import ru.fusionsoft.lib.yaml.YamlRepresentative;

/**
 * Its like {@link TextOfObjectField}, but with empty string placeholder for absent fields values.
 * @since 0.1
 */
public class TextOfObjectFieldMaybeEmpty extends TextOfObjectField {

    /**
     * Instantiates a new Text of object field maybe empty.
     * @param object The {@link YamlRepresentative} to be encapsulated.
     * @param field The {@link Text} to be encapsulated.
     * @param presence Transformation if field present.
     */
    public TextOfObjectFieldMaybeEmpty(
        final YamlRepresentative<?> object,
        final Text field,
        final Func<String, Text> presence
    ) {
        super(object, field, presence, new TextOf(""));
    }

    /**
     * Instantiates a new Text of object field maybe empty.
     * @param object The {@link YamlRepresentative} to be encapsulated.
     * @param field The {@link Text} to be encapsulated.
     */
    public TextOfObjectFieldMaybeEmpty(
        final YamlRepresentative<?> object,
        final Text field
    ) {
        super(object, field, new TextOf(""));
    }

}
