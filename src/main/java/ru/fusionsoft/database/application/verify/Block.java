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
package ru.fusionsoft.database.application.verify;

import java.util.List;
import java.util.Set;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.MappedWithIndex;
import org.cactoos.list.ListEnvelope;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.text.FixMark;

/**
 * The separate block of migration sql with one logical command.
 * @since 0.1
 */
public final class Block extends ListEnvelope<String> {

    /**
     * Instantiates a new Block.
     * @param list The lines of migration sql to be encapsulated.
     */
    public Block(final List<String> list) {
        super(list);
    }

    /**
     * Instantiates a new Block.
     */
    public Block() {
        this(new ListOf<String>());
    }

    /**
     * Interventions set.
     * @return The set.
     */
    public Set<Integer> interventions() {
        final String fix = new FixMark().asString();
        return new SetOf<Integer>(
            new Filtered<>(
                idx -> idx >= 0,
                new MappedWithIndex<Integer>(
                    (item, idx) -> new Ternary<>(
                        () -> item.contains(fix),
                        () -> idx,
                        () -> -1
                    ).value(),
                    this
                )
            )
        );
    }

    /**
     * Editable boolean.
     * @return The boolean.
     */
    public boolean editable() {
        return this.interventions().size() > 0;
    }

}
