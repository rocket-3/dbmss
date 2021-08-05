package org.fusionsoft.database;

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.cactoos.Proc;
import org.cactoos.func.FuncOf;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Or;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.Unchecked;
import org.cactoos.set.SetOf;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.ofyaml.KeysFromYamlNode;
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
            + "  \"instanceStatus~ACTIVE\": [0, 1, \"ACTIVE\", \"ACTIVE\",null, null]\n"
            + "  \"instanceStatus~DELETED\": [0, 2, \"DELETED\", \"DELETED\", null, null]";

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
    
    @Test
    public void yamlValues() throws Exception {
        final String pathToInspect = "C:\\Users\\worki\\OneDrive\\dbgit\\target\\itoutput\\05\\.dbgit\\public";
        final Stream<Path> pathStream = Files.find(
            Paths.get(pathToInspect),
            999,
            (p, bfa) -> {
                return bfa.isRegularFile() && new Unchecked<>(
                    new Or(
                        new FuncOf<>((suffix) -> p.toString().endsWith(suffix)),
                        new IterableOf<>(
                            ".ts",
                            ".sch",
                            ".seq",
                            ".tbl",
                            ".pkg",
                            ".trg",
                            ".prc",
                            ".fnc",
                            ".vw",
                            ".blob",
                            ".udt",
                            ".enum",
                            ".domain"
                        )
                    )
                ).value();
            }
        );

        for (final Path path : new IterableOf<>(pathStream.iterator())) {
            final YamlMapping mapping = Yaml.createYamlInput(path.toFile()).readYamlMapping();
            System.out.println(path.getName(path.getNameCount() - 1));
            for (final String key : new KeysFromYamlNode(mapping)) {
                System.out.println("\t" + key);
            }
        }
    }
    
}
