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
package ru.fusionsoft.database.mapping.dbd;

import org.cactoos.Text;

/**
 * The default value scalar node of DBD/$schema.
 * @since 0.1
 */
public class TextOfDbdDefaultJsonSchemaRef implements Text {

    @Override
    public final String asString() {
        return "https://json-schema.org/draft/2019-09/schema";
    }

}
