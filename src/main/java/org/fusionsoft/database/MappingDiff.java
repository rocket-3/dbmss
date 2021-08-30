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
package org.fusionsoft.database;

import java.util.Map;

/**
 * The interface MappingDiff representing difference in two mappings.
 * @since 0.1
 */
public interface MappingDiff {

    /**
     * Persistent present only props map.
     * @return The map.
     */
    Map<String, String> persistentOnlyProps();

    /**
     * Target present only props map.
     * @return The map.
     */
    Map<String, String> targetOnlyProps();

    /**
     * Differing props map.
     * @return The map.
     */
    Map<String, DiffPair<String>> differingProps();

    /**
     * Nested diff mapping diff.
     * @param key By what key to obtain nested difference.
     * @return The mapping diff.
     */
    MappingDiff nestedDiff(String key);

}
