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
package ru.fusionsoft.lib.input;

import org.cactoos.text.TextOf;

/**
 * Test application for {@link InputPrintsToConsoleTest} and
 *  {@link ManualConsoleInput}.
 * @since 0.1
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
@SuppressWarnings("PMD")
final class InputPrintsToConsoleTest {

    /**
     * Test application entry point.
     * @param args The args.
     */
    public static void main(final String[] args) {
        System.out.println(
            new TextOf(
                new InputPrintsToConsole(
                    new TextOf("Type your text and press Enter:"),
                    new ManualConsoleInput(new TextOf("utf-8"))
                )
            )
        );
    }

}
