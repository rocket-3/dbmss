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
 *
 */
package org.fusionsoft.lib.input;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.io.InputStreamOf;
import org.cactoos.scalar.ScalarOf;

public class ReaderReadLineInput implements Input {

    private final Scalar<InputStream> inputStreamScalar;

    private ReaderReadLineInput(final BufferedReader bufferedReader) {
        this.inputStreamScalar = new ScalarOf<InputStream>(
            br -> new InputStreamOf(
                br.readLine()
            ),
            bufferedReader
        );
    }

    public ReaderReadLineInput(final Reader reader) {
        this(
            new BufferedReader(reader)
        );
    }

    @Override
    public InputStream stream() throws Exception {
        return inputStreamScalar.value();
    }

}
