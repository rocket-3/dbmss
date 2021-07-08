package org.fusionsoft.database.description.infouniverse.ofyaml;

import org.fusionsoft.database.description.infouniverse.IUColumnDBD;
import org.fusionsoft.database.description.infouniverse.IUConstraintDBD;
import org.fusionsoft.database.description.infouniverse.IUSchemaDBD;
import org.fusionsoft.database.description.infouniverse.IUTableDBD;
import org.fusionsoft.database.text.TextOfExampleYaml;
import org.junit.jupiter.api.Test;

class YamlIUDBDTest {
    @Test
    public void constructsFromText() throws Exception {
        final YamlIUDBD tested = new YamlIUDBD(new TextOfExampleYaml().asString());
        System.out.println(
            "tested.serverDescriptions().iterator().next().description() = "
           + tested.serverDescriptions().iterator().next().description()
        );
        for (IUSchemaDBD sch : tested.schemaDescriptions()) {
            System.out.println("sch.key() = " + sch.key());
            System.out.println("sch.owner() = " + sch.owner());
            for (final IUTableDBD td : sch.tableDescriptions()) {
                System.out.println("\ttd.key() = " + td.key());
                for (final IUColumnDBD col : td.columns()) {
                    System.out.println("\t\tcol.name() = " + col.name());
                    System.out.println("\t\t\tcol.dbName() = " + col.dbName());
                    System.out.println("\t\t\tcol.iuColumn() = " + col.iuColumn());
                    System.out.println("\t\t\tcol.iuJsonColumn() = " + col.iuJsonColumn());
                    System.out.println("\t\t\tcol.type() = " + col.type());
                }
                for (final IUConstraintDBD con : td.constraints()) {
                    System.out.println("\t\tcon.key() = " + con.key());
                    System.out.println("\t\t\tcon.dbConstraintType() = " + con.dbConstraintType());
                    System.out.println("\t\t\tcon.dbColumn() = " + con.dbColumn());
                    System.out.println("\t\t\tcon.dbRefColumn() = " + con.dbRefColumn());
                }
            }
        }
    }
}
