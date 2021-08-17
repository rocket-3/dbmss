package org.fusionsoft.lib.input;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.io.InputStreamOf;
import org.cactoos.scalar.ScalarOf;

public class ReaderReadLineInput implements Input {
    private final Scalar<InputStream> inputStreamScalar;

    private ReaderReadLineInput(BufferedReader bufferedReader) {
        this.inputStreamScalar = new ScalarOf<InputStream>(
            br -> new InputStreamOf(
                br.readLine()
            ),
            bufferedReader
        );
    }
    
    public ReaderReadLineInput(Reader reader){
        this(
            new BufferedReader(reader)
        );
    }

    @Override
    public InputStream stream() throws Exception {
        return inputStreamScalar.value();
    }
}
