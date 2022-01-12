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
package ru.fusionsoft.database.snapshot;

import org.cactoos.Text;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * The interface ObjectSignature representing identifier of object in DBMS .
 * @since 0.1
 */
public interface ObjectSignature extends Text {

    /**
     * The name of object in DBMS text.
     * @return The text.
     */
    ObjectName name();

    /**
     * The DBMS type of object text.
     * @return The object type.
     */
    ObjectType<?> type();

    /**
     * Overridden contract of {@link Text} w/0 exception thrown.
     * @return The String representation of the signature.
     */
    String asString();

    /**
     * Equals boolean.
     * @param other The other {@link ObjectSignature}.
     * @return The boolean.
     */
    boolean equalsTo(ObjectSignature other);

}
