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
package org.fusionsoft.database.description.dbd.ofyaml;

import java.io.IOException;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.And;
import org.fusionsoft.database.description.dbd.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link TableOf}.
 * @since 0.1
 */
@SuppressWarnings("PMD")
class TableOfTest {

    /**
     * Constructs {@link Table} from text.
     * @throws IOException When can't.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    @Test
    public void constructsFromText() throws Exception {
        final Table vendors = new TableOf(
            "vendors",
            "columns:\n"
            + "  - iuColumn: uid\n"
            + "    dbName: uid\n"
            + "    type: \"string\"\n"
            + "    dbNullable: false\n"
            + "    description: Глобальный ид объекта\n"
            + "  - iuColumn: name\n"
            + "    dbName: brand_name\n"
            + "    type: \"string\"\n"
            + "    description: Имя бренда\n"
            + "  - iuColumn: name\n"
            + "    dbName: name\n"
            + "    type: \"string\"\n"
            + "    description: Наименование объекта (краткое)\n"
            + "  - iuColumn: decr\n"
            + "    dbName: descr\n"
            + "    type: \"string\"\n"
            + "    description: Наименование объекта (полное)\n"
            + "  - iuColumn: comm\n"
            + "    dbName: comm\n"
            + "    type: \"string\"\n"
            + "    description: Пояснение к объекту\n"
            + "  - dbName: vendor_id\n"
            + "    dbLocalIdMethod: GLOBAL_SEQ\n"
            + "    dbNullable: false\n"
            + "    description: Локальный идентификатор производителя\n"
            + "  - iuColumn: vendor_name\n"
            + "    dbName: vendor_name\n"
            + "    type: \"string\"\n"
            + "    dbNullable: false\n"
            + "    description: Имя производителя\n"
            + "constraints:\n"
            + "  pk_vendors:\n"
            + "    dbConstraintType: PK\n"
            + "    dbColumn:\n"
            + "      - vendor_id\n"
            + "indexes:\n"
            + "  pk_vendors:\n"
            + "    dbColumn:\n"
            + "      - vendor_id\n"
            + "    dbUnique: true"
        );
        Assertions.assertTrue(
            new And(
                () -> {
                    return new MapOf<>(
                        constraint -> new MapEntry<>(
                            constraint.key(),
                            constraint
                        ),
                        vendors.constraints()
                    )
                        .get("pk_vendors")
                        .dbColumn()
                        .contains("vendor_id");
                },
                () -> {
                    return "Глобальный ид объекта"
                        .equals(
                            vendors.columns().iterator().next().description()
                        );
                }
            ).value()
        );
    }

}
