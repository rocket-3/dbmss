package org.fusionsoft.database.description.dbd.ofyaml;

import java.io.IOException;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.description.dbd.Table;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class YamlIUTableDBDTest {
    @Test
    public void constructsFromText() throws IOException {
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
        assertEquals(
            "Глобальный ид объекта",
            vendors.columns().iterator().next().description()
        );
        assertTrue(
            new MapOf<>(
                constraint-> new MapEntry<>(constraint.key(), constraint), 
                vendors.constraints()
            )
            .get("pk_vendors")
            .dbColumn()
            .contains("vendor_id")
        );
    }
}
