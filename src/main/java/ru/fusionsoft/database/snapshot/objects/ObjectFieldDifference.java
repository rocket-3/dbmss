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
package ru.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Scalar;
import org.cactoos.Text;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.lib.yaml.YamlRepresentative;

/**
 * Is specific field of {@link YamlRepresentative} has the same value
 *  ahead of given {@link TemporalDiff}.
 * @since 0.1
 */
public class ObjectFieldDifference implements Scalar<Boolean> {

    /**
     * The TextOfObjectFieldMaybeEmpty encapsulated.
     */
    private final TextOfObjectFieldMaybeEmpty current;

    /**
     * The TextOfObjectFieldMaybeEmpty encapsulated.
     */
    private final TextOfObjectFieldMaybeEmpty next;

    /**
     * Instantiates a new Object field difference.
     * @param diff The {@link TemporalDiff} of {@link YamlRepresentative} to be encapsulated.
     * @param field The field name {@link Text} to be encapsulated.
     */
    public ObjectFieldDifference(
        final TemporalDiff<? extends YamlRepresentative<? extends YamlNode>> diff,
        final Text field
    ) {
        this.current = new TextOfObjectFieldMaybeEmpty(diff.current(), field);
        this.next = new TextOfObjectFieldMaybeEmpty(diff.next(), field);
    }

    @Override
    public final Boolean value() {
        return !this.current.asString().equals(this.next.asString());
    }

}
