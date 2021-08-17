package org.fusionsoft.lib.input;

import java.io.InputStream;
import org.cactoos.Input;
import org.cactoos.Text;

public class InputPrintsToConsole implements Input {
    private final Runnable printingRunnable;
    private final Input origin;

    public InputPrintsToConsole(Text textToConsole, Input origin) {
        this.printingRunnable = new ConsolePrinting(textToConsole);
        this.origin = origin;
    }

    @Override
    public InputStream stream() throws Exception {
        printingRunnable.run();
        return origin.stream();
    }
}
