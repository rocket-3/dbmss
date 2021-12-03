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
package org.fusionsoft.database.snapshot.data;

public class RowOfArray implements Row {

    private final String label;

    private final String[] array;

    public RowOfArray(final String label, final String[] array) {
        this.label = label;
        this.array = array;
    }

    @Override
    public String textOf(final Column column) {
        return array[column.order().intValue() - 1];
    }

    @Override
    public String label() {
        return this.label;
    }

}