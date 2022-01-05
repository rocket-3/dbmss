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
package org.fusionsoft.database.snapshot.data;

/**
 * The {@link Row} iface implementation based on array of column values.
 * @since 0.1
 */
public class RowOfArray implements Row {

    /**
     * The label encapsulated.
     */
    private final String label;

    /**
     * The array encapsulated.
     */
    private final String[] array;

    /**
     * Instantiates a new Row of array.
     * @param label The label {@link String} to be encapsulated.
     * @param array The array of {@link String} to be encapsulated.
     */
    public RowOfArray(final String label, final String[] array) {
        this.label = label;
        this.array = array;
    }

    @Override
    public final String textOf(final Column column) {
        return this.array[column.order().intValue() - 1];
    }

    @Override
    public final String label() {
        return this.label;
    }

}
