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
import org.cactoos.Text;
import org.cactoos.list.ListOf;
import org.cactoos.text.Split;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfText;
import ru.fusionsoft.database.snapshot.objects.signature.type.SimpleObjectTypeOfText;
import ru.fusionsoft.lib.text.RegexpLiteralEscaped;

/**
 * The basic {@link ObjectSignatureFormat} implementation.
 * @since 0.1
 */
public class SimpleObjectSignatureFormat implements ObjectSignatureFormat {

    @Override
    public final ObjectName name(final Text from) {
        return new SimpleObjectNameOfText(
            new ListOf<>(
                new Split(
                    from,
                    new RegexpLiteralEscaped(this.delimiter())
                )
            ).get(0)
        );
    }

    @Override
    public final ObjectType<?> type(final Text from) {
        return new SimpleObjectTypeOfText(
            new ListOf<Text>(
                new Split(
                    from,
                    new RegexpLiteralEscaped(this.delimiter())
                )
            ).get(1)
        );
    }

    @Override
    public final String string(final ObjectName name, final ObjectType<?> type) {
        return MessageFormat.format(
            "{0}{2}{1}",
            name.asString(),
            type.asString(),
            this.delimiter()
        );
    }

    @Override
    public String delimiter() {
        return " (type) ";
    }

}
