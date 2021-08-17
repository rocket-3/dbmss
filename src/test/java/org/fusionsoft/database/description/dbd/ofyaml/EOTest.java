package org.fusionsoft.database.description.dbd.ofyaml;

import java.io.IOException;
import org.cactoos.text.TextOf;
import org.fusionsoft.lib.input.InputPrintsToConsole;
import org.fusionsoft.lib.input.ManualConsoleInput;

public class EOTest {
    public static void main(String[] args) throws IOException {

        System.out.println(
            new TextOf(
                new InputPrintsToConsole(
                    new TextOf("Type your text and press Enter:"),
                    new ManualConsoleInput(new TextOf("utf-8"))
                )
            )
        );
    }
    
}

