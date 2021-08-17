package org.fusionsoft.lib.yaml.artefacts;

import java.util.Iterator;
import org.cactoos.iterator.IteratorOf;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;

public class FirstNotEmptyTextOf extends TextEnvelope {
    public FirstNotEmptyTextOf(CharSequence... variants) {
        super(
            new TextOfScalar(
                () -> {
                    final Iterator<CharSequence> it = new IteratorOf<>(variants);
                    while(it.hasNext()){
                        final String var = it.next().toString();
                        if(!var.isEmpty()) return var;
                    }
                    throw new Exception("All variants are empty");
                }
            )
        );
    }
}
