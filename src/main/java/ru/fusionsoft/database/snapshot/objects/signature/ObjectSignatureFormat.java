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
package ru.fusionsoft.database.snapshot.objects.signature;

import org.cactoos.Text;

/**
 * The interface representing how to format and parse
 *  {@link ru.fusionsoft.database.snapshot.ObjectSignature}.
 * @since 0.1
 */
public interface ObjectSignatureFormat {

    /**
     * Name object name.
     * @param from The {@link Text}.
     * @return The object name.
     */
    ObjectName name(Text from);

    /**
     * Type object type.
     * @param from The {@link Text}.
     * @return The object type.
     */
    ObjectType<?> type(Text from);

    /**
     * String format.
     * @param name The {@link ObjectName} to format.
     * @param type The {@link ObjectType} to format.
     * @return The string.
     */
    String string(ObjectName name, ObjectType<?> type);

    /**
     * Delimiter of name's parts.
     * @return The string of name's parts delimiter.
     */
    String delimiter();

}
