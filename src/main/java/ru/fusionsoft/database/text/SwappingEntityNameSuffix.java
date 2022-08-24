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

package ru.fusionsoft.database.text;

import org.cactoos.Text;

/**
 * The name suffix that is added to object's name, which is going to be replaced at the end of
 *  migration script.
 * @since 0.1
 */
public class SwappingEntityNameSuffix implements Text {

    @Override
    public final String asString() {
        return "_merging_old_version";
    }

}
