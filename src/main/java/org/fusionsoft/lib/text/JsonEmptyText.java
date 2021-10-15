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
package org.fusionsoft.lib.text;

import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;

/**
 * The {@link org.cactoos.Text} that is empty.
 * @since 0.1
 */
public class JsonEmptyText extends TextEnvelope {

    /**
     * Ctor.
     */
    public JsonEmptyText() {
        super(new TextOf("''"));
    }

}
