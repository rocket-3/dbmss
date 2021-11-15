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

import org.cactoos.Text;
import org.cactoos.text.TextOf;

public class RowOfArray implements Row {

    private final long num;

    private final String[] array;

    public RowOfArray(final long num, final String[] array) {
        this.num = num;
        this.array = array;
    }

    @Override
    public Text textOf(final Column column) {
        return new TextOf(array[column.order().intValue()]);
    }

    @Override
    public long number() {
        return this.num;
    }

}
