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
package org.fusionsoft.lib.input;

import java.io.InputStream;
import org.cactoos.Input;
import org.cactoos.Text;

public class InputPrintsToConsole implements Input {

    private final Runnable printingRunnable;

    private final Input origin;

    public InputPrintsToConsole(final Text textToConsole, final Input origin) {
        this.printingRunnable = new ConsolePrinting(textToConsole);
        this.origin = origin;
    }

    @Override
    public InputStream stream() throws Exception {
        printingRunnable.run();
        return origin.stream();
    }

}
