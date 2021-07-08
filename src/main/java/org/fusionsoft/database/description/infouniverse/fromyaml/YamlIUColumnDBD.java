package org.fusionsoft.database.description.infouniverse.fromyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.scalar.ScalarOf;
import org.fusionsoft.database.description.infouniverse.IUColumnDBDEnvelope;
import org.fusionsoft.database.description.infouniverse.SimpleIUColumnDBD;
import org.fusionsoft.database.ofyaml.MaybeEmptyTextOf;
import org.fusionsoft.database.ofyaml.StringSetOf;

public class YamlIUColumnDBD extends IUColumnDBDEnvelope {
    public YamlIUColumnDBD(StrictYamlMapping mapping) {
        super(
            new ScalarOf<>(
                () -> new SimpleIUColumnDBD(
                    new MaybeEmptyTextOf(mapping, "iuColumn").asString(),
                    new MaybeEmptyTextOf(mapping, "iuJsonColumn").asString(),
                    new MaybeEmptyTextOf(mapping, "type").asString(),
                    ! new MaybeEmptyTextOf(mapping, "dbNullable").asString().equals("false"),
                    new MaybeEmptyTextOf(mapping, "description").asString(),
                    new StringSetOf(mapping, "iuIncludeProps"),
                    new MaybeEmptyTextOf(mapping, "dbName").asString(),
                    new MaybeEmptyTextOf(mapping, "dbType").asString(),
                    new MaybeEmptyTextOf(mapping, "dbLocalIdMethod").asString()
                )
            )
        );
    }
    
    public YamlIUColumnDBD(YamlMapping mapping) {
        this(new StrictYamlMapping(mapping));
    }

}
