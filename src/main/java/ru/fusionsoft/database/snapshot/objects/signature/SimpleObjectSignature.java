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
package ru.fusionsoft.database.snapshot.objects.signature;

import java.text.MessageFormat;
import java.util.Objects;
import org.cactoos.text.UncheckedText;
import ru.fusionsoft.database.snapshot.ObjectSignature;

/**
 * The base {@link ObjectSignature} implementation.
 * @since 0.1
 */
public class SimpleObjectSignature implements ObjectSignature {

    /**
     * The Text encapsulated.
     */
    private final ObjectName nam;

    /**
     * The ObjectType encapsulated.
     */
    private final ObjectType<?> typ;

    /**
     * Instantiates a new Object signature of.
     * @param name The Text to be encapsulated.
     * @param type The ObjectType to be encapsulated.
     */
    public SimpleObjectSignature(final ObjectName name, final ObjectType<?> type) {
        this.nam = name;
        this.typ = type;
    }

    @Override
    public final ObjectName name() {
        return this.nam;
    }

    @Override
    public final ObjectType<?> type() {
        return this.typ;
    }

    @Override
    public final String asString() {
        return MessageFormat.format(
            "{0}: {1}",
            new UncheckedText(this.name()).asString(),
            new UncheckedText(this.type()).asString()
        );
    }

    @Override
    public final boolean equals(final Object other) {
        boolean equ = false;
        if (other instanceof ObjectSignature) {
            equ = this.equalsTo((ObjectSignature) other);
        }
        return equ;
    }

    @Override
    public final boolean equalsTo(final ObjectSignature other) {
        return this.asString().equals(other.asString());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.asString());
    }

}
