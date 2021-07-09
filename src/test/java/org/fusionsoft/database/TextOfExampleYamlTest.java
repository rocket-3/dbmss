package org.fusionsoft.database;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.io.IOException;
import org.cactoos.scalar.ScalarOf;
import org.fusionsoft.database.ofyaml.StringIterableOf;
import org.fusionsoft.database.text.TextOfExampleYaml;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextOfExampleYamlTest {
    @Test
    public void hasContent() throws Exception {
        Assertions.assertTrue(
            new ScalarOf<>(
                text -> {
                    System.out.println("text = " + text);
                    return text.asString();
                },
                new TextOfExampleYaml()
            )
            .value()
            .length() > 0
        );
    }
    
    @Test
    public void canParseArraySequence() throws IOException {
        final String text = 
            "data:\n"
            + "  \"instanceStatus~ACTIVE\": " 
            + "    [0, 1, \"ACTIVE\", \"ACTIVE\",null, null]\n"
            + "  \"instanceStatus~DELETED\": " 
            + "    [0, 2, \"DELETED\", \"DELETED\", null, null]";

        final YamlMapping data = Yaml.createYamlInput(text)
            .readYamlMapping()
            .value("data")
            .asMapping();

        for (final YamlNode key : data.keys()) {
            System.out.println(key);
            for (final String s : new StringIterableOf(data.value(key).asScalar())) {
                System.out.println(s);
            }
        }
    }
}
