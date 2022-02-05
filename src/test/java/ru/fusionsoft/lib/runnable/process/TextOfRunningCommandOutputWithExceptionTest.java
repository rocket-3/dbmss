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
package ru.fusionsoft.lib.runnable.process;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.lib.exception.ProcessRunningException;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.text.TextOfRunningCommandOutputWithException;

/**
 * The tests for {@link TextOfRunningCommandOutputWithException}.
 * @since 0.1
 */
class TextOfRunningCommandOutputWithExceptionTest {

    /**
     * Runs nslookup with input.
     */
    @Test
    @SuppressWarnings("PMD.AvoidUsingHardCodedIP")
    void runsNslookupWithInput() {
        final String output = new TextOfRunningCommandOutputWithException(
            new CurrentWorkingDirectory(),
            new ArgsExplicit("nslookup"),
            new ArgsExplicit("2ip.ru", "habr.ru")
        ).asString();
        Assertions.assertTrue(output.contains("195.201.201.32"));
        Assertions.assertTrue(output.contains("82.192.95.175"));
    }

    /**
     * Runs tracert with error.
     */
    @Test
    void runsTracertWithError() {
        Assertions.assertThrows(
            ProcessRunningException.class,
            () -> new TextOfRunningCommandOutputWithException(
                new CurrentWorkingDirectory(),
                new ArgsExplicit("tracert")
            ).asString()
        );
    }

}
