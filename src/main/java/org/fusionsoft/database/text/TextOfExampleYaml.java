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
package org.fusionsoft.database.text;

import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;

/**
 * The type of Text that is constructed of example YAML from resources.
 * @since 0.1
 */
public class TextOfExampleYaml extends TextEnvelope {

    /**
     * Instantiates a new Text of example yaml from resources.
     */
    public TextOfExampleYaml() {
        super(
            new TextOf(
                new ResourceOf("iuDescriptionExample.yaml")
            )
        );
    }

}
