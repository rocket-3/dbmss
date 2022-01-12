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
package ru.fusionsoft.database.snapshot.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.cactoos.Text;

/**
 * The interface representing how to interchange column data of some type.
 * @since 0.1
 * @todo #106:60min Finish implementing classes of ValueFormat iface.
 */
public interface ValueFormat {

    /**
     * Yaml representation of string.
     * @param text The text to represent.
     * @return The string encoded to yaml scalar.
     */
    String yamlRepresentationOf(String text);

    /**
     * Pass in prepare statement.
     * @param stmt The stmt to pass into.
     * @param ordinal The ordinal of variable.
     * @param text The text to pass from.
     */
    void passInPrepareStatement(PreparedStatement stmt, int ordinal, Text text);

    /**
     * Decode {@link String} from {@link ResultSet}'s variable.
     * @param rset The rset origin of variable.
     * @param ordinal The ordinal of variable.
     * @return The string extracted.
     */
    String ofResultSet(ResultSet rset, int ordinal);

    /**
     * Decode {@link String} from {@link ResultSet}'s variable.
     * @param rset The rset origin of variable.
     * @param key The key of variable.
     * @return The string extracted.
     */
    String ofResultSet(ResultSet rset, String key);

}
