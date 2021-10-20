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
package org.fusionsoft.database.snapshot.dbms;

import java.sql.Connection;
import org.cactoos.Func;
import org.cactoos.Text;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The DbmsSignatureName representing DBMS type related behavior.
 * @since 0.1
 */
public interface Dbms extends Text {

    /**
     * Text representation of Dbms name.
     * @return The String of name.
     */
    String asString();

    /**
     * Objects of {@link Connection} function.
     * @return The function.
     */
    Func<Connection, Objects> objects();

}