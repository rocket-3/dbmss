package org.fusionsoft.database.artefacts;

import org.cactoos.Text;

public interface StringPropertyType extends Text {
    StringPropertyType Text = () -> "text";
    StringPropertyType Boolean = () -> "boolean";
    StringPropertyType Integer = () -> "integer";
    StringPropertyType Float = () -> "float";
}
