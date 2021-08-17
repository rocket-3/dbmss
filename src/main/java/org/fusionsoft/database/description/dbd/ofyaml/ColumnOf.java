package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.description.dbd.SimpleColumn;
import org.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOf;
import org.fusionsoft.lib.yaml.artefacts.StringSetOf;

public class ColumnOf extends SimpleColumn {
    public ColumnOf(StrictYamlMapping mapping) throws Exception {
        super(
            new MaybeEmptyTextOf(mapping, "iuColumn").asString(),
            new MaybeEmptyTextOf(mapping, "iuJsonColumn").asString(),
            new MaybeEmptyTextOf(mapping, "type").asString(),
            ! new MaybeEmptyTextOf(mapping, "dbNullable").asString().equals("false"),
            new MaybeEmptyTextOf(mapping, "description").asString(),
            new StringSetOf(mapping, "iuIncludeProps"),
            new MaybeEmptyTextOf(mapping, "dbName").asString(),
            new MaybeEmptyTextOf(mapping, "dbType").asString(),
            new MaybeEmptyTextOf(mapping, "dbLocalIdMethod").asString()
        );
    }
    
    public ColumnOf(YamlMapping mapping) throws Exception {
        this(new StrictYamlMapping(mapping));
    }

}
