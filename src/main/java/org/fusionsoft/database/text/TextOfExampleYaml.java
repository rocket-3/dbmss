package org.fusionsoft.database.text;

import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;

public class TextOfExampleYaml extends TextEnvelope {

    public TextOfExampleYaml() {
        super(new TextOf(new ResourceOf("iuDescriptionExample.yaml")));
    }
    
}
