package org.fusionsoft.database.description.dbd.ofyaml;

import org.fusionsoft.database.description.dbd.Column;
import org.fusionsoft.database.description.dbd.Constraint;
import org.fusionsoft.database.description.dbd.Schema;
import org.fusionsoft.database.description.dbd.Table;
import org.fusionsoft.database.text.TextOfExampleYaml;
import org.junit.jupiter.api.Test;

class YamlIUDBDTest {
    @Test
    public void constructsFromText() throws Exception {
        final DbdOf tested = new DbdOf(new TextOfExampleYaml().asString());
        System.out.println(
            "tested.serverDescriptions().iterator().next().description() = "
           + tested.serverDescriptions().iterator().next().description()
        );
        for (Schema sch : tested.schemaDescriptions()) {
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
