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
package org.fusionsoft.lib.yaml.artefacts;

import java.util.Iterator;
import org.cactoos.iterator.IteratorOf;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;

public class FirstNotEmptyTextOf extends TextEnvelope {

    public FirstNotEmptyTextOf(final CharSequence... variants) {
        super(
            new TextOfScalar(
                () -> {
                    final Iterator<CharSequence> it = new IteratorOf<>(variants);
                    while (it.hasNext()) {
                        final String var = it.next().toString();
                        if (! var.isEmpty()) {
                            return var;
                        }
                    }
                    throw new Exception("All variants are empty");
                }
            )
        );
    }

}
