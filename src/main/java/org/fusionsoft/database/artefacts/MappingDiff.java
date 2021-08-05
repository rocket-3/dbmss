package org.fusionsoft.database.artefacts;

import java.util.Set;

public interface MappingDiff {
    Set<String> diffKeys();
    Set<String> nestedDiffKeys();
    DiffPair<String> prop(String key);
    MappingDiff nestedDiff(String key);
}
