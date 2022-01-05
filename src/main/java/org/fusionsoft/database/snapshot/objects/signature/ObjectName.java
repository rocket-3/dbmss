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
package org.fusionsoft.database.snapshot.objects.signature;

import org.cactoos.Text;

/**
 * The interface representing name and hierarchy of one of DBMS's objects.
 * @since 0.1
 */
public interface ObjectName extends Text {

    String asString();

    /**
     * Parent object name.
     * @return The object name.
     */
    ObjectName parent();

    /**
     * First name text.
     * @return The text.
     */
    Text first();

    /**
     * Equals boolean.
     * @param other The another {@link ObjectName}.
     * @return The boolean.
     */
    boolean equals(ObjectName other);

}
