package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.description.dbd.SimpleIUColumnDBD;
import org.fusionsoft.database.ofyaml.MaybeEmptyTextOf;
import org.fusionsoft.database.ofyaml.StringSetOf;

public class YamlIUColumnDBD extends SimpleIUColumnDBD {
    public YamlIUColumnDBD(StrictYamlMapping mapping) throws Exception {
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
    
    public YamlIUColumnDBD(YamlMapping mapping) throws Exception {
        this(new StrictYamlMapping(mapping));
    }

}
