package org.fusionsoft.lib.input;

import java.io.OutputStream;
import java.io.PrintStream;
import org.cactoos.Text;

public class OutputStreamPrinting implements Runnable {
    private final PrintStream printStream;
    private final Text text;

    public OutputStreamPrinting(PrintStream printStream, Text text) {
        this.printStream = printStream;
        this.text = text;
    }
    
    public OutputStreamPrinting(OutputStream outputStream, Text text) {
        this(new PrintStream(outputStream), text);
    }
    
    @Override
    public void run() {
        this.printStream.println(this.text);
    }
}
