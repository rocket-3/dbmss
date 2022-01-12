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

import org.cactoos.Text;

/**
 * The interface representing a column in table in some DBMS.
 * @since 0.1
 */
public interface Column {

    /**
     * Name of column.
     * @return The text.
     */
    Text name();

    /**
     * Order of column.
     * @return The number.
     */
    Number order();

    /**
     * {@link ValueFormat} of column.
     * @return The value format.
     */
    ValueFormat format();
}
