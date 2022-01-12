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
package ru.fusionsoft.lib.yaml.artefacts;

import java.util.Iterator;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.TextOf;

/**
 * The type of Text that can be constructed of array of CharSequence, most of
 * which can be empty. You get a first non-empty one.
 * @since 0.1
 * @checkstyle HiddenFieldCheck (100 lines)
 */
public class FirstNotEmptyTextOf implements Text {

    /**
     * The Iterator of String encapsulated.
     */
    private final Unchecked<Iterator<String>> iterator;

    /**
     * Instantiates a new First not empty text of presented variants.
     * @param variants The iterator of Strings to be checked for being non-empty.
     * @implNote The first element from array that is not empty, is returned.
     */
    public FirstNotEmptyTextOf(final Iterable<Text> variants) {
        this.iterator = new Unchecked<>(
            () -> new Filtered<>(
                x -> !x.isEmpty(),
                new Mapped<>(
                    Text::asString,
                    variants
                )
            ).iterator()
        );
    }

    /**
     * Instantiates a new First not empty text of presented variants.
     * @param variants The CharSequence array to be checked for being non-empty.
     * @implNote The first element from array that is not empty, is returned.
     */
    public FirstNotEmptyTextOf(final Text... variants) {
        this(new IterableOf<Text>(variants));
    }

    /**
     * Instantiates a new First not empty text of presented variants.
     * @param variants The CharSequence array to be checked for being non-empty.
     * @implNote The first element from array that is not empty, is returned.
     */
    public FirstNotEmptyTextOf(final CharSequence... variants) {
        this(new Mapped<>(TextOf::new, new IterableOf<CharSequence>(variants)));
    }

    @Override
    public final String asString() throws AllEmptyException {
        final Iterator<String> iterator = this.iterator.value();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            throw new AllEmptyException();
        }
    }

    /**
     * The type of Exception that means all variants in
     * {@link FirstNotEmptyTextOf} are empty.
     * @since 0.1
     */
    public static class AllEmptyException extends Exception {

        /**
         * Instantiates a new All empty exception.
         */
        public AllEmptyException() {
            super(
                "All variants are empty"
            );
        }

    }

}
