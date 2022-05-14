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
package ru.fusionsoft.database.snapshot.objects.ofdbms;

import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The Objects of {@link Connection} of Oracle dbms.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class ObjectsOfOracle extends IterableEnvelope<DbObject<YamlNode>> {

    /**
     * Ctor.
     * @param connection The connection used
     */
    public ObjectsOfOracle(final Connection connection) {
        super(new IterableOf<>());
    }

}
