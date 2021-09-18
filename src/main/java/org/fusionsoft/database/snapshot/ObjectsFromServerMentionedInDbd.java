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
package org.fusionsoft.database.snapshot;

import org.fusionsoft.database.DbdFile;

/**
 * The type of {@link DbObjects} from database mentioned in {@link DatabaseInfo}
 *  that names present in {@link DbdFile} only.
 * @since 0.1
 * @todo #15:15min Implement later in other puzzle
 */
@SuppressWarnings("PMD")
public class ObjectsFromServerMentionedInDbd extends DbObjects {

    /**
     * Instantiates a new Objects from server mentioned in dbd.
     * @param database The DatabaseInfo to be encapsulated.
     * @param file The DbdFile to be encapsulated.
     */
    public ObjectsFromServerMentionedInDbd(
        final DatabaseInfo database,
        final DbdFile file
    ) {
        super();
    }

}
