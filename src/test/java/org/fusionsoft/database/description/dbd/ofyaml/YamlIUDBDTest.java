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
 *
 */
package org.fusionsoft.database.description.dbd.ofyaml;

import org.fusionsoft.database.description.dbd.Column;
import org.fusionsoft.database.description.dbd.Constraint;
import org.fusionsoft.database.description.dbd.DBD;
import org.fusionsoft.database.description.dbd.Schema;
import org.fusionsoft.database.description.dbd.Table;
import org.fusionsoft.database.text.TextOfExampleYaml;
import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.lib.yaml.YamlInputOf;
import org.junit.jupiter.api.Test;

class YamlIUDBDTest {

    @Test
    public void constructsFromText() throws Exception {

        final DBD tested = new DbdOf(
            new DBDYamlInput(
                new YamlInputOf(
                    new TextOfExampleYaml()
                )
            )
        );

        System.out.println(
            "tested.serverDescriptions().iterator().next().description() = "
            + tested.serverDescriptions().iterator().next().description()
        );

        for (final Schema sch : tested.schemaDescriptions()) {
            System.out.println("sch.key() = " + sch.key());
            System.out.println("sch.owner() = " + sch.owner());
            for (final Table td : sch.tableDescriptions()) {
                System.out.println("\ttd.key() = " + td.key());
                for (final Column col : td.columns()) {
                    System.out.println("\t\tcol.name() = " + col.name());
                    System.out.println("\t\t\tcol.dbName() = " + col.dbName());
                    System.out.println("\t\t\tcol.iuColumn() = " + col.iuColumn());
                    System.out.println("\t\t\tcol.iuJsonColumn() = " + col.iuJsonColumn());
                    System.out.println("\t\t\tcol.type() = " + col.type());
                }
                for (final Constraint con : td.constraints()) {
                    System.out.println("\t\tcon.key() = " + con.key());
                    System.out.println("\t\t\tcon.dbConstraintType() = " + con.dbConstraintType());
                    System.out.println("\t\t\tcon.dbColumn() = " + con.dbColumn());
                    System.out.println("\t\t\tcon.dbRefColumn() = " + con.dbRefColumn());
                }
            }
        }
    }

}
