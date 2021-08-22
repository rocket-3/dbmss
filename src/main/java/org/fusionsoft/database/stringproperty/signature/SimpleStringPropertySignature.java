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
package org.fusionsoft.database.stringproperty.signature;

import java.text.MessageFormat;
import java.util.Objects;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.StringPropertySignature;
import org.fusionsoft.database.StringPropertyType;

public class SimpleStringPropertySignature implements StringPropertySignature {

    private final StringPropertyType type;

    private final UncheckedText key;

    public SimpleStringPropertySignature(final Text key, final StringPropertyType type) {
        this.type = type;
        this.key = new UncheckedText(key);
    }

    public SimpleStringPropertySignature(final CharSequence key, final StringPropertyType type) {
        this(
            new TextOf(key),
            type
        );
    }

    @Override
    public StringPropertyType type() {
        return this.type;
    }

    @Override
    public String key() {
        return this.key.asString();
    }

    @Override
    public String toString() {
        return MessageFormat.format(
            "{0} : {1}",
            this.key,
            new UncheckedText(this.type).asString()
        );
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (! ( o instanceof SimpleStringPropertySignature )) {
            return false;
        }
        final SimpleStringPropertySignature that = (SimpleStringPropertySignature) o;
        return that.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

}
