package org.fusionsoft.lib.input;

import org.cactoos.Text;
import org.cactoos.proc.RunnableEnvelope;

public class ConsolePrinting extends RunnableEnvelope {

    public ConsolePrinting(Text textToPrint) {
        super(
            new OutputStreamPrinting(
                System.out, 
                textToPrint
            )
        );
    }
}
