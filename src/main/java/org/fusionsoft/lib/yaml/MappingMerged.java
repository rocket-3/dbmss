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
package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.extensions.MergedYamlMapping;

/**
 * The type of {@link YamlMapping }that can be constructed of two ones, merging
 *  the second into first, rewriting matching nodes.
 * @since 0.1
 */
public class MappingMerged extends YamlMappingOfScalar {

    /**
     * Instantiates a new YamlMapping which is result of merge-and-replace
     *  from changed to the original ones.
     * @param original The merge to YamlMapping to be encapsulated.
     * @param changed The merge from YamlMapping to be encapsulated.
     */
    public MappingMerged(final YamlMapping original, final YamlMapping changed) {
        super(
            () -> new MergedYamlMapping(
                original,
                changed,
                true
            )
        );
    }

}
