package org.fusionsoft.lib.collection;

import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class StrictMapTest {

    @Test
    void canGetExistingValue() {
        Assertions.assertDoesNotThrow(
            () -> new StrictMap<>(
                new MapOf<>(
                    new MapEntry<>("a", "1"),
                    new MapEntry<>("b", "2")
                )
            ).get("a")
        );
    }
    
    @Test
    void canNotGetAbsentValue() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> new StrictMap<>(
                new MapOf<>(
                    new MapEntry<>("a", "1")
                )
            ).get("b")
        );
    }
}
