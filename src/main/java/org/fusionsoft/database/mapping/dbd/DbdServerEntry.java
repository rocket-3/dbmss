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
package org.fusionsoft.database.mapping.dbd;

import java.util.Map;
import org.cactoos.Text;
import org.fusionsoft.lib.exception.NotImplemented;

/**
 * The {@link Text} -> {@link DbdServerMapping} entry of corresponding values.
 * @since 0.1
 */
public class DbdServerEntry implements Map.Entry<Text, DbdServerMapping> {

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * The DbdServerMapping encapsulated.
     */
    private final DbdServerMapping mapping;

    /**
     * Instantiates a new Dbd server entry.
     * @param text The Text to be encapsulated.
     * @param mapping The DbdServerMapping to be encapsulated.
     */
    public DbdServerEntry(final Text text, final DbdServerMapping mapping) {
        this.text = text;
        this.mapping = mapping;
    }

    @Override
    public final Text getKey() {
        return this.text;
    }

    @Override
    public final DbdServerMapping getValue() {
        return this.mapping;
    }

    @Override
    public final    DbdServerMapping setValue(final DbdServerMapping value) {
        throw new NotImplemented();
    }

}
