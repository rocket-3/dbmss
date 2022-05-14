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

import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import org.cactoos.Text;

/**
 * The DbmsSignatureName representing DBMS type related behavior.
 * @since 0.1
 */
public interface Dbms {

    /**
     * Text representation of Dbms name in 'DBD'/servers.
     * @return The Text of label.
     */
    Text dbd();

    /**
     * Text representation of Dbms driver in JDBC.
     * @return The Text of label.
     */
    Text driver();

    /**
     * Objects of {@link Connection} function.
     * @param connection The {@link Connection} to take objects from.
     * @return The objects from dbms.
     */
    Iterable<? extends DbObject<YamlNode>> objects(Connection connection);

}
