package org.fusionsoft.lib.input;

import java.io.InputStream;
import java.io.InputStreamReader;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.io.Stdin;
import org.cactoos.scalar.ScalarOf;

public class ManualConsoleInput implements Input {
    private final Scalar<Input> iptScalar;

    public ManualConsoleInput(Text charsetName) {
        this.iptScalar = new ScalarOf<Input>(
            charset -> new ReaderReadLineInput(
                new InputStreamReader(
                    new Stdin().stream(), 
                    charset.asString()
                )
            ),
            charsetName
        );
    }

    @Override
    public InputStream stream() throws Exception {
        return this.iptScalar.value().stream();
    }
}
